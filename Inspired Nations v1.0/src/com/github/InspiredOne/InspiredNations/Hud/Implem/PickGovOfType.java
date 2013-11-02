package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.DataPassPromptOption;
import com.github.InspiredOne.InspiredNations.Hud.DataStorage;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
/**
 * Generalized find address menu system.
 * @author Jedidiah E. Phillips
 *
 */
public abstract class PickGovOfType extends PassByOptionMenu {

	Menu previous;
	Menu next;
	InspiredGov superGov;
	Class<? extends InspiredGov> govTargetType;
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
	public String getPreOptionText() {
		return "";
	}

	@Override
	public Menu PreviousMenu() {
		return previous;
	}

	@Override
	public String getHeader() {
		return "Select the option that corrisponds to the address";
	}

	@Override
	public void init() {

		InspiredGov targetobj = GovFactory.getGovInstance(govTargetType);
		
		for(Class<? extends OwnerGov> subType:superGov.getSubGovs()) {
			for(InspiredGov govToTest:InspiredNations.regiondata.get(subType)) {
				if(govToTest.isSubOf(superGov)) {
					if(check(govToTest)) {
						this.options.add(new DataPassPromptOption(this, govToTest.getName(), next, govToTest));
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
