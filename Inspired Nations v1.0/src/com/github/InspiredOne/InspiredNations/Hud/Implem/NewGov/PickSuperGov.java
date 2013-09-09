package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.NoSubjects;
import com.github.InspiredOne.InspiredNations.Hud.DataPassPromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.ContextData;

public class PickSuperGov extends PassByOptionMenu {

	GovFactory Govf;
	
	public PickSuperGov(PlayerData PDI, GovFactory Govf) {
		super(PDI);
		this.Govf = Govf;
	}

	@Override
	public String getPreOptionText() {
		return "Pick the " + GovFactory.getGovInstance(Govf.getGov().getSuperGov()).getTypeName()
				+ " that contains this " + Govf.getGov().getTypeName() + ".";
	}

	@Override
	public String getHeader() {
		return "Select Super Gov";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Menu PreviousMenu() {
		return new PickSelfType(PDI, (Class<? extends NoSubjects>) Govf.getGov().getGeneralGovType());
	}

	@Override
	public void init() {
		System.out.println("In init() of 1: " + this.getHeader());
		System.out.println("In init() of 2: " + this.getHeader());
		System.out.println("size of citizenship: " + PDI.getCitizenship(Govf.getGov().getSuperGov()).size() + "in "+ this.getHeader());
		for(InspiredGov gov:PDI.getCitizenship(Govf.getGov().getSuperGov())) {
			System.out.println("In init() of 3: " + this.getHeader());
			this.options.add(new PromptOption(this, gov.getName(), new WarningAlreadyOwnOne(PDI, Govf.withSuperGov(gov))));
			System.out.println("In init() of4: " + this.getHeader());
		}
	}
}
