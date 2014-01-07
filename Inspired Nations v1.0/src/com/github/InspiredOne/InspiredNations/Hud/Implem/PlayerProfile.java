package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.ToolBox.IndexedMap;

public class PlayerProfile extends Menu {

	PlayerData PDITarget;
	
	public PlayerProfile(PlayerData PDI, PlayerData PDITarget) {
		super(PDI);
		this.PDITarget = PDITarget;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getHeader() {
		return "Profile: " + PDI.getName();
	}

	@Override
	public String getFiller() {
		IndexedMap<String, String> Citizenship;
		return null;
	}

	@Override
	public void register() {

	}

	@Override
	public Menu getPreviousMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Menu getNextMenu(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getPassBy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Menu getPassTo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

}
