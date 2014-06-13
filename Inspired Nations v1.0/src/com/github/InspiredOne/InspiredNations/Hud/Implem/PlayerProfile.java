package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TaxTimerManager;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.MenuUpdateManager;
import com.github.InspiredOne.InspiredNations.ToolBox.Datable;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public class PlayerProfile extends OptionMenu {

	
	PlayerData PDITarget;
	//Datable<PlayerID> data;
	
	public <T extends Datable<PlayerID>> PlayerProfile(PlayerData PDI, T PDITarget) {
		super(PDI);
		this.PDITarget = PDITarget.getData().getPDI();
	}

	@Override
	public String getHeader() {
		Debug.print("before profile");
		return "Profile: " + PDITarget.getName();
	}

	@Override
	public Menu getPreviousMenu() {
		Debug.print("poop");
		return new PlayerDirectory(PDI);
	}


	@Override
	public boolean getPassBy() {
		return false;
	}

	@Override
	public Menu getPassTo() {
		return this.getSelfPersist();
	}

	@Override
	public void actionResponse() {
		
	}

	@Override
	public void addActionManagers() {
		
	}
	
	// These methods are overridden by all the super classes. I wish there were a better
	// way I could do this. Until then, ctrl-c and ctrl-v.
	@Override
	public void menuPersistent() {
		Debug.print("m-p-1");
		managers.add(new TaxTimerManager<ActionMenu>(this));
		managers.add(new MenuUpdateManager<ActionMenu>(this));
		Debug.print("mp2");
		this.addActionManagers();
		Debug.print("mp3");
		//this.PDITarget = this.data.getData().getPDI();
	}

	@Override
	public String getPreOptionText() {
		Debug.print("profile");
		return "Here is your profile:";
	}

	@Override
	public void addOptions() {
		// TODO Auto-generated method stub
		if (this.PDITarget.equals(PDI)) {
			this.options.add(new PromptOption(this, "Set Menu Colors", new ColorMenu(PDI)));
		}
		
		//go to set theme and make another menu
		
		
	}

}
