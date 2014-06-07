package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Hud.DarkTheme;
import com.github.InspiredOne.InspiredNations.Hud.LightTheme;
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
	public void unloadNonPersist() {
		Debug.print("uNP");
		// TODO Auto-generated method stub

	}

	@Override
	public void unloadPersist() {
		// TODO Auto-generated method stub
		Debug.print("uP");
	}

	@Override
	public void menuPersistent() {
		// TODO Auto-generated method stub
		Debug.print("cm1");
		managers.add(new TaxTimerManager<ActionMenu>(this));
		Debug.print("cmp2");
		this.addActionManagers();
		Debug.print("cmp3");
		//this.PDITarget = this.data.getData().getPDI();
	}

	@Override
	public void nonPersistent() {
		// TODO Auto-generated method stub
		Debug.print("nP");
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
		// TODO Auto-generated method stub
		//Debug.print("this.PDI.theme");
		Debug.print(this.PDI.theme.getName());
		return "Your current menu is Light Theme";
		//this.PDI.theme.getName();
	}

	@Override
	public void addOptions() {
		Debug.print("options for Color Menu");
		//this.options.add(new ColorOption(this, "Dark Theme", new DarkTheme("dark")));
		//this.options.add(new ColorOption(this, "Light Theme", new LightTheme("light")));
		
	}

	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		Debug.print("aAM");
		
	}

}
