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
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
/**
 * Generalized find address menu system.
 * @author Jedidiah E. Phillips
 *
 */
public abstract class PickGovOfType extends TabSelectOptionMenu {

	Menu previous;
	Menu next;
	InspiredGov superGov;
	Class<? extends InspiredGov> govTargetType;
	/**
	 * 
	 * @param PDI
	 * @param previous	Menu to go back to
	 * @param next		Final menu to end on
	 * @param govTargetType
	 * @param superGov
	 */
	public PickGovOfType(PlayerData PDI, Menu previous, Menu next, Class<? extends InspiredGov> govTargetType, InspiredGov superGov) {
		super(PDI);
		this.previous = previous;
		this.next = next;
		this.superGov = superGov;
		this.govTargetType = govTargetType;
	}
	public PickGovOfType(PlayerData PDI, Menu previous, Menu next, Class<? extends InspiredGov> govTargetType) {
		super(PDI);
		this.previous = previous;
		this.next = next;
		this.superGov = InspiredNations.global;
		this.govTargetType = govTargetType;
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

		InspiredGov targetobj = GovFactory.getGovInstance(govTargetType);

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
		
	}
	/**
	 * if it returns true, then the gov it checked is added to the options list.
	 * @param gov
	 * @return
	 */
	public abstract boolean check(InspiredGov gov);

}
