package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.DarkTheme;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.ToolBox.Theme;

public class ColorMenu extends OptionMenu {

	public ColorMenu(PlayerData PDI) {
		
		super(PDI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void unloadNonPersist() {
		// TODO Auto-generated method stub

	}

	@Override
	public void unloadPersist() {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuPersistent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void nonPersistent() {
		// TODO Auto-generated method stub

	}

	@Override
	public Menu getPreviousMenu() {
		return new PlayerDirectory(PDI);
	}

	@Override
	public boolean getPassBy() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Menu getPassTo() {
		return this.getSelfPersist();
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return "Choose your theme here!";
	}

	@Override
	public String getPreOptionText() {
		// TODO Auto-generated method stub
		
		return "Your current theme is: " + this.PDI.theme.getName();
	}

	@Override
	public void addOptions() {
		//this.options.add(new PromptOption(this, "Citizenship Requests and Offers", new SubjectOffers(PDI)));
		this.options.add(new ColorOption(this, "Dark Theme", new DarkTheme("dark")));
		
	}

	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		
	}

}
