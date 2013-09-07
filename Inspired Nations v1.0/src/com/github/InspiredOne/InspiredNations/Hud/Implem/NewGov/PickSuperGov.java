package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.NoSubjects;
import com.github.InspiredOne.InspiredNations.Hud.DataPassPromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.ContextData;

public class PickSuperGov extends PassByOptionMenu {

	Menu last;
	GovFactory Govf;
	
	public PickSuperGov(PlayerData PDI, Menu last) {
		super(PDI);
		this.last = last;
	}

	@Override
	public String getPreOptionText() {
		return "Pick the " + GovFactory.getGovInstance(Govf.getGov().getSuperGov()).getTypeName()
				+ " you would like this " + Govf.getGov().getTypeName() + " to be under.";
	}

	@Override
	public String getHeader() {
		return "Select ";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Menu PreviousMenu() {
		return new PickSelfType(PDI, (Class<? extends NoSubjects>) Govf.getGov().getClass(), last);
	}

	@Override
	public void init() {
		Govf = (GovFactory) this.getContext().getSessionData(ContextData.PromptData);
		for(InspiredGov gov:PDI.getCitizenship(Govf.getGov().getSuperGov())) {
			this.options.add(new DataPassPromptOption(this, gov.getName(), last, Govf.withSuperGov(gov)));
		}
	}
}
