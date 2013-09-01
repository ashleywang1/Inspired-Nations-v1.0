package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.NoSubjects;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

public class PickSuperGov extends PassByOptionMenu {

	GovFactory Govf;
	
	public PickSuperGov(PlayerData PDI, GovFactory Govf) {
		super(PDI);
		this.Govf = Govf;
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

	@SuppressWarnings("unchecked")
	@Override
	public Menu getPreviousMenu() {
		return new PickSelfType(PDI, (Class<? extends NoSubjects>) Govf.getGov().getClass());
	}

	@Override
	public void init() {
		for(InspiredGov gov:PDI.getCitizenship(Govf.getGov().getSuperGov())) {
			this.options.add(new PromptOption(this, gov.getTypeName(), new WarningAlreadyOwnOne(PDI, Govf.withSuperGov(gov)), OptionUnavail.NOT_UNAVAILABLE));
		}		
	}
}
