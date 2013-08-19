package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;


import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

public class PickSelfType extends PassByOptionMenu {

	Class<? extends InspiredGov> GovType;
	
	public PickSelfType(PlayerData PDI, Class<? extends InspiredGov> GovType) {
		super(PDI);
		System.out.println("hello1");
		this.GovType = GovType;
		
		for(Class<? extends InspiredGov> gov:GovFactory.getGovInstance(GovType).getSelfGovs()) {
			this.options.add(new PromptOption(this, GovFactory.getGovInstance(gov).getTypeName(), new PickSuperGov(PDI, new GovFactory(gov)), OptionUnavail.NOT_UNAVAILABLE ));
		}
	}

	@Override
	public String getPreOptionText() {
		return "Pick what kind of " + GovFactory.getGovInstance(GovType).getTypeName()
				+ " you would like to make.";
	}

	@Override
	public String getHeader() {
		return "Select " + GovFactory.getGovInstance(GovType).getTypeName() + " type.";
	}

	@Override
	public Menu getPreviousMenu() {
		return new MainHud(PDI);
	}

}
