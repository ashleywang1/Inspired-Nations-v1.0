package com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimAndUnclaimLand;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.InputMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.ClaimChestShopManager;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.MapManager;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public class ClaimChestShop extends InputMenu {

	InspiredGov gov;
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
			return MenuError.NO_ERROR();
		}
		else {
			return MenuError.NOT_AN_OPTION();
		}
	}

	@Override
	public void useInput(String input) {
		gov.getRegion().setRegion(manager.region);
	}

	@Override
	public List<String> getTabOptions() {
		List<String> output = new ArrayList<String>();
		return output;
	}

	@Override
	public String getInstructions() {
		String output = mapmanager.drawMap(gov, 4);
		output = MenuTools.addDivider(output);
		output = output.concat(TextColor.INSTRUCTION + "Left click on the chest that you would like to claim.");
		return output;
	}

	@Override
	public void Init() {
		this.managers.add(manager);
		this.managers.add(mapmanager);
	}

	@Override
	public InputMenu getSelf() {
		return new ClaimChestShop(PDI, previous, gov);
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
}
