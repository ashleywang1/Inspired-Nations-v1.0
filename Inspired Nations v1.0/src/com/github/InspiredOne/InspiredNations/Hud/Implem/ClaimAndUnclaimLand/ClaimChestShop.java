package com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimAndUnclaimLand;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.InspiredGovTooStrongException;
import com.github.InspiredOne.InspiredNations.Exceptions.InsufficientRefundAccountBalanceException;
import com.github.InspiredOne.InspiredNations.Exceptions.RegionOutOfEncapsulationBoundsException;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.InputMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.ClaimChestShopManager;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.MapManager;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public class ClaimChestShop extends InputMenu {

	public InspiredGov gov;
	Menu previous;
	private ClaimChestShopManager manager;
	private MapManager<ClaimChestShop> mapmanager;

	public ClaimChestShop(PlayerData PDI, Menu previous, InspiredGov gov) {
		super(PDI);
		this.gov = gov;
		this.previous = previous;
		manager = new ClaimChestShopManager(this);
		mapmanager = new MapManager<ClaimChestShop>(this);
	}

	@Override
	public Menu nextMenu() {
		return previous;
	}

	@Override
	public String validate(String input) {
		if(input.equalsIgnoreCase("finish")) {
			try {
				gov.setLand(manager.region);
				return MenuError.NO_ERROR();
			} catch (BalanceOutOfBoundsException e) {
				return MenuError.NOT_ENOUGH_MONEY();
			} catch (InspiredGovTooStrongException e) {
				return MenuError.GOV_TOO_STRONG(e.gov);
			} catch (RegionOutOfEncapsulationBoundsException e) {
				return MenuError.CLAIM_OUT_OF_BOUNDS(e.gov);
			} catch (InsufficientRefundAccountBalanceException e) {
				e.printStackTrace();
			}
			return MenuError.NO_ERROR();
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
		String output = mapmanager.drawMap(gov, 4);
		output = MenuTools.addDivider(output);
		output = output.concat(TextColor.INSTRUCTION + "Left click on the chest that you would like to claim. Type '"
				+ TextColor.VALUE + "finish" + TextColor.INSTRUCTION +"' when you are done.\n");
		output = output.concat(TextColor.LABEL + "Selected: " + TextColor.VALUE);
		if(manager.region.volume() == 0) {
			output = output.concat("No Chest Selected");
		}
		else if(manager.region.volume() == 1) {
			output = output.concat("Single Chest Selected");
		}
		else {
			output = output.concat("Double Chest Selected");
		}
		output = output.concat("\n");
		return output;
	}

	@Override
	public String getHeader() {
		return "Claim Chest Shop";
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
	public void addTabOptions() {
		
	}

	@Override
	public void addActionManagers() {
		this.managers.add(manager);
		this.managers.add(mapmanager);
		
	}
}
