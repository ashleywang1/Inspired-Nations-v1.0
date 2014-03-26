package com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimAndUnclaimLand;

import java.util.ArrayList;
import java.util.List;

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
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

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
			return "Claim Cuboid 1:" + (int) Math.pow(2, mapmanager.zoom)+ ",   " + manager.getVolume();
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
	public void init() {
		this.mapmanager = new MapManager<ClaimCuboid>(this);
		this.manager = new ClaimCuboidManager<ClaimCuboid>(this);
		this.managers.add(mapmanager);
		this.managers.add(manager);
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
	public List<String> getTabOptions() {
		List<String> output = new ArrayList<String>();
		return output;
	}

	@Override
	public String getInstructions() {
		String output = this.mapmanager.drawMap(gov, 4);
		output = output.concat("Left Click for one corner of the cuboid and Right Click for the other corner.\n");
		return output;
	}

	@Override
	public InputMenu getSelf() {
		return new ClaimCuboid(PDI, previous, gov);
	}
}
