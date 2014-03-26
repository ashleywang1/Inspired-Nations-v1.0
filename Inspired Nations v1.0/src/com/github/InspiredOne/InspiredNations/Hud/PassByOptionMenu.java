package com.github.InspiredOne.InspiredNations.Hud;

import java.util.ArrayList;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;

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
	
	@Override
	public void reset() {
		managers = new ArrayList<ActionManager<?>>();
		managers.add(new TaxTimerManager<ActionMenu>(this));
		this.options = new ArrayList<Option>();
	}
	/**
	 * Returns a new instance of itself. Used for user input errors.
	 * @return	the <code>Menu</code> of itself
	 */
	public abstract PassByOptionMenu getSelf();
}
