package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

public class PickSuperGov extends PassByOptionMenu {

	GovFactory Govf;
	
	public PickSuperGov(PlayerData PDI, GovFactory Govf) {
		super(PDI);
		this.Govf = Govf;
		System.out.println("Hello2)");
		for(InspiredGov gov:PDI.getCitizenship(plugin, Govf.getGov().getSuperGov())) {
			this.options.add(new PromptOption(this, gov.getTypeName(), new PickName(PDI, Govf.withSuperGov(gov)), OptionUnavail.NOT_UNAVAILABLE));
		}
	}

	@Override
	public String getPreOptionText() {
		return "Pick the " + GovFactory.getGovInstance(Govf.getGov().getSuperGov()).getTypeName()
				+ " you would like to make this " + Govf.getGov().getTypeName() + " under.";
	}

	@Override
	public String getHeader() {
		return "Select ";
	}

	@Override
	public Menu getPreviousMenu() {
		// TODO Auto-generated method stub
		return new PickSelfType(PDI, Govf.getGov().getClass());
	}

}
