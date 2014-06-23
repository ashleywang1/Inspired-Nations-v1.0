package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;

public class SettingsMenu extends OptionMenu {

	public SettingsMenu(PlayerData PDI) {
		super(PDI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getPreOptionText() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void addOptions() {

		this.options.add(new PromptOption(this, "Set Menu Colors", new ColorMenu(PDI)));
	}

	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Menu getPreviousMenu() {
		// TODO Auto-generated method stub
		return new PlayerDirectory(PDI);
	}

	@Override
	public boolean getPassBy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Menu getPassTo() {
		// TODO Auto-generated method stub
		return this.getSelfPersist();
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return "Change your settings here:";
	}
	

}
