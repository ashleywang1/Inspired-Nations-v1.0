package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;


import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;

public class PickSelfType extends PassByMenu {

	Class<? extends InspiredGov> GovType;
	
	@SuppressWarnings("static-access")
	public PickSelfType(InspiredNations plugin, PlayerData PDI, Class<? extends InspiredGov> GovType) {
		super(plugin, PDI);
		this.GovType = GovType;
		
		for(Class<? extends InspiredGov> gov:MenuTools.getGovInstance(GovType).getSelfGovs()) {
			this.options.add(new PromptOption(this, Tools.getGovInstance(gov).getTypeName(), new PickSuperGov(plugin, PDI, GovType), OptionUnavail.NOT_UNAVAILABLE ));
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

	@Override
	public Menu getPreviousMenu() {
		return new MainHud(plugin, PDI);
	}

}
