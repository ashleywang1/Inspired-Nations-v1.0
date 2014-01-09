package com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress.PickGovGeneral;
import com.github.InspiredOne.InspiredNations.ToolBox.Datable;

public class PickGovToPay extends PickGovGeneral {

	public PickGovToPay(PlayerData PDI, Menu previous, Menu next) {
		super(PDI, previous, next);
	}
	public PickGovToPay(PlayerData PDI, Menu previous, Menu next, Datable<InspiredGov> superGov) {
		super(PDI, previous, next, superGov);
	}

	@Override
	public boolean check(InspiredGov gov) {
		return true;
	}

	@Override
	public void insertOptions() {
		if(this.getData().getAllSubGovsAndFacilitiesJustBelow().size() > 0) {
			this.options.add(new PromptOption(this, "Search Under", new PickGovToPay(PDI, this, next, this)));
		}

	}

}
