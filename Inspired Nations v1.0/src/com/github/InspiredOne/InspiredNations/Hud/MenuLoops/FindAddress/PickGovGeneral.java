package com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.DataPassPromptOption;
import com.github.InspiredOne.InspiredNations.Hud.DataStorage;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
/**
 * Generalized find address menu system.
 * @author Jedidiah E. Phillips
 *
 */
public class PickGovGeneral extends TabSelectOptionMenu {

	Menu previous;
	Menu next;
	InspiredGov superGov;
	
	/**
	 * 
	 * @param PDI
	 * @param previous	Menu to go back to
	 * @param next		Final menu to end on
	 * @param govTargetType
	 * @param superGov
	 */
	public PickGovGeneral(PlayerData PDI, Menu previous, Menu next, InspiredGov superGov) {
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
	}

	@Override
	public String getHeader() {
		return "Select the option that corrisponds to the address";
	}

	@Override
	public void Init() {

		this.taboptions.clear();
		this.options.clear();
		// Iterate over all the sub-gov types.
		for(Class<? extends OwnerGov> subType:superGov.getSubGovs()) {
			// Iterate over every government of that type
			for(InspiredGov govToTest:InspiredNations.regiondata.get(subType)) {
				// Check if the government is under the particular superGov
				if(govToTest.isSubOf(superGov)) {
					if(check(govToTest)) {
						this.taboptions.add(govToTest);
					//	this.options.add(new DataPassPromptOption(this, govToTest.getName(), next, govToTest));
					}
				}
			}
		}
		
		//Make the options
		this.options.add(new PromptOption(this, "Search Under", new PickGovGeneral(PDI, this, next, (InspiredGov) this.getSelection())));
		
		
	}
	/**
	 * if it returns true, then the gov it checked is added to the tab-options list.
	 * @param gov
	 * @return
	 */
	public boolean check(InspiredGov gov) {
		return true;
	}
	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

}
