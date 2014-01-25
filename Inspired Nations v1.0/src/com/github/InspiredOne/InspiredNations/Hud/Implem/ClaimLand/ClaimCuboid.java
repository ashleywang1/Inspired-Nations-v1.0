package com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimLand;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.CuboidNotCompletedException;
import com.github.InspiredOne.InspiredNations.Exceptions.InspiredGovTooStrongException;
import com.github.InspiredOne.InspiredNations.Exceptions.InsufficientRefundAccountBalanceException;
import com.github.InspiredOne.InspiredNations.Exceptions.RegionOutOfEncapsulationBoundsException;
import com.github.InspiredOne.InspiredNations.Governments.GlobalGov;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.InputMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.ClaimCuboidManager;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.MapManager;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class ClaimCuboid extends InputMenu {

	public InspiredGov gov;
	public Menu previous;
	private MapManager<ClaimCuboid> mapmanager;
	private ClaimCuboidManager<ClaimCuboid> manager;
	private int Zoom = 4;
	
	
	public ClaimCuboid(PlayerData PDI, Menu previous, InspiredGov gov) {
		super(PDI);
		this.gov = gov;
		this.previous = previous;
	}

	@Override
	public void actionResponse() {
		if(mapmanager.preTabEntry.equalsIgnoreCase("+") && Zoom > 0) {
			Zoom--;
		}
		else if(mapmanager.preTabEntry.equalsIgnoreCase("-") && Zoom < 8) {
			Zoom++;
		}
	}

	@Override
	public String getHeader() {
		return "Claim Cuboid " + this.manager.getVolume();
	}

	@Override
	public String getFiller() {
		if(gov.getSuperGov().equals(GlobalGov.class)) {
			return Tools.drawMap(PDI, (int) Math.pow(2, Zoom), gov.getClass());
		}
		else {
			return Tools.drawMap(PDI,(int) Math.pow(2, Zoom), gov.getSuperGov());
		}
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
	public void Init() {
		Debug.print("Inside Init() of ClaimCuboid)");
		this.mapmanager = new MapManager<ClaimCuboid>(this);
		this.manager = new ClaimCuboidManager<ClaimCuboid>(this);
		this.managers.add(mapmanager);
		this.managers.add(manager);
	}

	@Override
	public Menu nextMenu() {
		return this;
	}

	@Override
	public String validate(String input) {
		if(input.equalsIgnoreCase("finish")) {
			return MenuError.NO_ERROR();
		}
		else {
			return MenuError.NOT_AN_OPTION();
		}
	}

	@Override
	public void useInput(String input) {
		try {
			gov.setLand(this.manager.getCuboid());
			
		} catch (BalanceOutOfBoundsException e) {
			this.manager.reset();
			this.setError(MenuError.NOT_ENOUGH_MONEY());
		} catch (InspiredGovTooStrongException e) {
			this.manager.reset();
			this.setError(MenuError.GOV_TOO_STRONG(e.gov));
		} catch (RegionOutOfEncapsulationBoundsException e) {
			this.manager.reset();
			this.setError(MenuError.CLAIM_OUT_OF_BOUNDS(e.gov));
		} catch (InsufficientRefundAccountBalanceException e) {
			// TODO figure out what to do here.
		} catch (CuboidNotCompletedException e) {
			this.setError(MenuError.CUBOID_NOT_FULLY_SELECTED());
		}
	}

	@Override
	public List<String> getTabOptions() {
		List<String> output = new ArrayList<String>();
		return output;
	}

	@Override
	public String getInstructions() {
		return "Left Click for one corner of the cuboid and Right Click for the other corner.";
	}
}
