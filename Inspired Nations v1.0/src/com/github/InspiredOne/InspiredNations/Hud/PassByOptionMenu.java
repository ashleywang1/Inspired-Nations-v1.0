package com.github.InspiredOne.InspiredNations.Hud;

import com.github.InspiredOne.InspiredNations.PlayerData;

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
			System.out.println("In getPassBy() of: " + this.getHeader());
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
}
