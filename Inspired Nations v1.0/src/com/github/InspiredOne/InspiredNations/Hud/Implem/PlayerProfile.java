package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TaxTimerManager;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Player.PlayerID;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Player.SettingsMenu;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.MenuUpdateManager;
import com.github.InspiredOne.InspiredNations.ToolBox.Datable;

public class PlayerProfile extends OptionMenu {
	
	PlayerData PDITarget;
	
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
		this.getActionManager().add(new TaxTimerManager<ActionMenu>(this));
		this.getActionManager().add(new MenuUpdateManager<ActionMenu>(this));
		Debug.print("mp2");
		this.addActionManagers();
		Debug.print("mp3");
		//this.PDITarget = this.data.getData().getPDI();
	}

	@Override
	public String getPreOptionText() {
		Debug.print("profile");
		return "";
	}

	@Override
	public void addOptions() {
		// TODO Auto-generated method stub
		//this.options.add(new AllyList(this, "Allies"));
		//this.options.add(new EnemyList(this, "Enemies"));
		
		if (this.PDITarget.equals(PDI)) {
			this.options.add(new PromptOption(this, "Settings", new SettingsMenu(PDI)));
		}
		
	}

}
