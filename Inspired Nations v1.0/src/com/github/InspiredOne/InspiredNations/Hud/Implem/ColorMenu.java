package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TaxTimerManager;
import com.github.InspiredOne.InspiredNations.ToolBox.Theme;

public class ColorMenu extends OptionMenu {

	public ColorMenu(PlayerData PDI) {
		
		super(PDI);
		
		Debug.print("after super(PDI)");
		
	}

	@Override
	public Menu getPreviousMenu() {
		Debug.print("previous menu");
		return new PlayerDirectory(PDI);
		
	}

	@Override
	public boolean getPassBy() {
		// TODO Auto-generated method stub
		Debug.print("gPB");
		return false;
	}

	@Override
	public Menu getPassTo() {
		return this.getSelfPersist();
	}

	@Override
	public String getHeader() {
		Debug.print("get Header");
		return "Choose your theme here!";
	}

	@Override
	public String getPreOptionText() {
		// TODO somehow record Custom or Default Theme
		return "";
	}

	@Override
	public void addOptions() {
		Debug.print("options for Color Menu");
		//this.options.add(new ThemeOption("Default"));
		this.options.add(new PromptOption(this, "Custom Theme", new ColorOptions(PDI)));
	}
	
	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		Debug.print("aAM");
		
	}

}
