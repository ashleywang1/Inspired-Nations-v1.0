package com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimLand;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.ClaimChunkoidManager;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;

public class ClaimChunkoid extends ActionMenu {

	Menu previous;
	InspiredGov gov;
	
	public ClaimChunkoid(PlayerData PDI, Menu previous, InspiredGov gov) {
		super(PDI);
		this.previous = previous;
		this.gov = gov;
	}

	@Override
	public Menu NextMenu(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionResponse() {
		// TODO Auto-generated method stub

	}

	@Override
	public Menu PreviousMenu() {
		return previous;
	}

	@Override
	public String getHeader() {
		return "Claim Chunkoid";
	}

	@Override
	public String getFiller() {
		return Tools.drawMap(PDI, 16, gov.getClass());
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
		this.managers.add(new ClaimChunkoidManager(this));

	}

}
