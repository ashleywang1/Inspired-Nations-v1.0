package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.PassByMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

public class PickSuperGov extends PassByMenu {

	Class<? extends InspiredGov> GovType;
	
	public PickSuperGov(InspiredNations plugin, PlayerData PDI, Class<? extends InspiredGov> GovType) {
		super(plugin, PDI);
		this.GovType = GovType;
		for(InspiredGov gov:PDI.getCitizenship(plugin, MenuTools.getGovInstance(GovType).getSuperGov())) {
			this.options.add(new PromptOption(this, gov.getName(), new PickName(plugin, PDI, GovType, gov), OptionUnavail.NOT_UNAVAILABLE));
		}
	}

	@Override
	public String getPreOptionText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

}
