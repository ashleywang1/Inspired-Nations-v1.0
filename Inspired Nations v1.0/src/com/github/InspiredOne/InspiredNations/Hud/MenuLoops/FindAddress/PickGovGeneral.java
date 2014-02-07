package com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.Datable;
/**
 * Generalized find address menu system.
 * @author Jedidiah E. Phillips
 *
 */
public abstract class PickGovGeneral extends TabSelectOptionMenu<InspiredGov> {

	public Menu previous;
	public Menu next;
	Datable<InspiredGov> superGov;
	
	/**
	 * 
	 * @param PDI
	 * @param previous	Menu to go back to
	 * @param next		Final menu to end on
	 * @param govTargetType
	 * @param superGov
	 */
	public PickGovGeneral(PlayerData PDI, Menu previous, Menu next, Datable<InspiredGov> superGov) {
		super(PDI);
		this.previous = previous;
		this.next = next;
		this.superGov = superGov;
	}
	public PickGovGeneral(PlayerData PDI, Menu previous, Menu next) {
		super(PDI);
		this.previous = previous;
		this.next = next;
		this.superGov = InspiredNations.global;
	}

	@Override
	public Menu getPreviousPrompt() {
		return previous;
		/*
		 * Not sure if I want it to work this way
		 * if(superGov != InspiredNations.global) {
			return new PickGovGeneral(PDI, previous, next, superGov);
		}
		else {
			return previous;
		}
		*/
	}

	@Override
	public final void Init() {

		this.taboptions.clear();
		this.options.clear();
		// Iterate over all the sub-gov types.
		for(InspiredGov govToTest: superGov.getData().getAllSubGovsAndFacilitiesJustBelow()) {
			if(check(govToTest)) {
				this.taboptions.add(govToTest);
			}
		}

		//Make the options
		insertOptions();

		
	}
	/**
	 * if it returns true, then the gov it checked is added to the tab-options list.
	 * @param gov
	 * @return
	 */
	public abstract boolean check(InspiredGov gov);
	/**
	 * Use to insert options
	 */
	public abstract void insertOptions();
}
