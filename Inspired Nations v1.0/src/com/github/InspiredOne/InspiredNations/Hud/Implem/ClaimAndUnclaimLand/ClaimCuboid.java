package com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimAndUnclaimLand;

import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.CuboidNotCompletedException;
import com.github.InspiredOne.InspiredNations.Exceptions.InspiredGovTooStrongException;
import com.github.InspiredOne.InspiredNations.Exceptions.InsufficientRefundAccountBalanceException;
import com.github.InspiredOne.InspiredNations.Exceptions.RegionOutOfEncapsulationBoundsException;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.InputMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.ClaimCuboidManager;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.MapManager;
import com.github.InspiredOne.InspiredNations.Regions.nullRegion;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public class ClaimCuboid extends InputMenu {

	public InspiredGov gov;
	public Menu previous;
	private MapManager<ClaimCuboid> mapmanager;
	private ClaimCuboidManager<ClaimCuboid> manager;
	
	
	public ClaimCuboid(PlayerData PDI, Menu previous, InspiredGov gov) {
		super(PDI);
		this.gov = gov;
		this.previous = previous;
	}

	@Override
	public void actionResponse() {
	}

	@Override
	public String getHeader() {
			return "Claim Cuboid 1:" + (int) Math.pow(2, mapmanager.zoom);
	}

	@Override
	public Menu getPreviousMenu() {
		return previous;
	}

	@Override
	public boolean getPassBy() {
		return false;
	}

	@Override
	public Menu getPassTo() {
		return null;
	}

	@Override
	public Menu nextMenu() {
		this.manager.reset();
		return previous;
	}

	@Override
	public String validate(String input) {
		if(input.equalsIgnoreCase("finish")) {
			try {
				gov.setLand(this.manager.getCuboid());
				this.manager.reset();
				return MenuError.NO_ERROR();
				
			} catch (BalanceOutOfBoundsException e) {
				this.manager.reset();
				return MenuError.NOT_ENOUGH_MONEY();
			} catch (InspiredGovTooStrongException e) {
				this.manager.reset();
				return MenuError.GOV_TOO_STRONG(e.gov);
			} catch (RegionOutOfEncapsulationBoundsException e) {
				this.manager.reset();
				return MenuError.CLAIM_OUT_OF_BOUNDS(e.gov);
			} catch (InsufficientRefundAccountBalanceException e) {
				// TODO figure out what to do here.
				return MenuError.MONEY_NAME_ALREADY_TAKEN();
			} catch (CuboidNotCompletedException e) {
				return MenuError.CUBOID_NOT_FULLY_SELECTED();
			}
		}
		else {
			return MenuError.NOT_AN_OPTION();
		}
	}

	@Override
	public void useInput(String input) {

	}

	@Override
	public String getInstructions() {
		
		// Tax paid that is independent of chunks
		BigDecimal zero = gov.taxValue(new nullRegion(), InspiredNations.taxTimer.getFractionLeft(), gov.getProtectionlevel(), PDI.getCurrency());
		// Total tax from selection
		BigDecimal totalcost = Tools.cut(BigDecimal.ZERO);
		try {
			totalcost = Tools.cut(gov.taxValue(this.manager.getCuboid(), 1, gov.getProtectionlevel(), PDI.getCurrency()).subtract(zero));
		} catch (CuboidNotCompletedException e1) {
		}
		// Current cost of selection at the current moment
		BigDecimal currentcost = Tools.cut(BigDecimal.ZERO);
		try {
			currentcost = Tools.cut(gov.taxValue(this.manager.getCuboid(), InspiredNations.taxTimer.getFractionLeft()
					, gov.getProtectionlevel(), PDI.getCurrency()).subtract(gov.taxValue(new nullRegion(), InspiredNations.taxTimer.getFractionLeft()
							, gov.getProtectionlevel(), PDI.getCurrency())));
		} catch (CuboidNotCompletedException e1) {
		}
		
		String output = this.mapmanager.drawMap(gov, 3);
		output = MenuTools.addDivider(output);
		output = output.concat(TextColor.INSTRUCTION + "Left Click for one corner of the cuboid and Right Click for the other corner.\n");
		output = MenuTools.oneLineWallet(output, PDI, gov.getAccounts());
		try {
			output = output.concat(TextColor.VALUEDESCRI + "Low Point: " + TextColor.VALUE + manager.getCuboid().getPointMin() + "\n");
		} catch (CuboidNotCompletedException e) {
			try {
				output = output.concat(TextColor.VALUEDESCRI + "Low Point: " + TextColor.VALUE + manager.point1.toString() + "\n");
			}
			catch (Exception ex) {
				output = output.concat(TextColor.VALUEDESCRI + "Low Point: " + TextColor.VALUE + "Not Selected\n");
			}
		}
		try {
			output = output.concat(TextColor.VALUEDESCRI + "High Point: " + TextColor.VALUE + manager.getCuboid().getPointMax() + "\n");
		} catch (CuboidNotCompletedException e) {
			try {
				output = output.concat(TextColor.VALUEDESCRI + "High Point: " + TextColor.VALUE + manager.point2.toString() + "\n");
			}
			catch (Exception ex) {
				output = output.concat(TextColor.VALUEDESCRI + "High Point: " + TextColor.VALUE + "Not Selected\n");
			}
		}
		output = output.concat(TextColor.VALUEDESCRI + "Volume: " + TextColor.VALUE + manager.getVolume() + TextColor.UNIT + " Cubic Meters\n");
		output = output.concat(TextColor.VALUEDESCRI + "Total/Current: " + TextColor.VALUE + totalcost + "/" + currentcost + " " + TextColor.UNIT +
				PDI.getCurrency());
		return output;
	}

	@Override
	public void addTabOptions() {
		
	}

	@Override
	public void addActionManagers() {
		this.mapmanager = new MapManager<ClaimCuboid>(this);
		this.manager = new ClaimCuboidManager<ClaimCuboid>(this);
		this.managers.add(mapmanager);
		this.managers.add(manager);
		
	}
}
