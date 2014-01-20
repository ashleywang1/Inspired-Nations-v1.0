package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.ToolBox.Datable;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public class PlayerProfile extends Menu {

	PlayerData PDITarget;
	Datable<PlayerID> data;
	
	public <T extends Datable<PlayerID>> PlayerProfile(PlayerData PDI, T PDITarget) {
		super(PDI);
		this.data = PDITarget;
	}

	@Override
	public String getHeader() {
		return "Profile: " + PDITarget.getName();
	}

	@Override
	public String getFiller() {
		return "";
	}

	@Override
	public void register() {

	}

	@Override
	public Menu getPreviousMenu() {
		return new PlayerDirectory(PDI);
	}

	@Override
	public Menu getNextMenu(String input) {
		return this.getSelf();
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
		this.PDITarget = this.data.getData().getPDI();

	}

}
