package com.github.InspiredOne.InspiredNations.Hud;

import java.util.ArrayList;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

/**
 * Used to allow option menus to be bypassed if there is only one option.
 * @author Jedidiah E. Phillips
 *
 */
public abstract class PassByOptionMenu extends OptionMenu{

	public PassByOptionMenu(PlayerData PDI) {
		super(PDI);
	}
	
	@Override
	public boolean getPassBy() {
		this.Initialize();
		if (this.options.size() == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public Menu getPassTo() {
		return this.getNextMenu("1");
	}
	
	// These methods are overridden by all the super classes. I wish there were a better
	// way I could do this. Until then, ctrl-c and ctrl-v.
	@Override
	public void menuPersistent() {
		managers.add(new TaxTimerManager<ActionMenu>(this));

	}

	@Override
	public void nonPersistent() {
		for(ActionManager<?> manager:this.getActionManager()) {
			manager.stopListening();
		}
		for(ActionManager<?> manager:this.getActionManager()) {
			manager.startListening();
		}
		this.addOptions();
	}

	@Override
	public void unloadNonPersist() {
		for(ActionManager<?> manager:this.getActionManager()) {
			manager.stopListening();
		}
		this.options = new ArrayList<Option>();
	}

	@Override
	public void unloadPersist() {
		managers = new ArrayList<ActionManager<?>>();
	}
	
}
