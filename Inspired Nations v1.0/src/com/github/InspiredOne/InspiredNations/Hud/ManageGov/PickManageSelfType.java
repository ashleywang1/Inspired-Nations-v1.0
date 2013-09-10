package com.github.InspiredOne.InspiredNations.Hud.ManageGov;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.NoSubjects;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;

public class PickManageSelfType extends PassByOptionMenu {

	Class<? extends NoSubjects> GovType;
	
	public PickManageSelfType(PlayerData PDI, Class<? extends NoSubjects> GovType) {
		super(PDI);
		this.GovType = GovType;
	}

	@Override
	public String getPreOptionText() {
		return "Pick the type of " + GovFactory.getGovInstance(GovType).getTypeName() + ".";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Menu PreviousMenu() {
		GovFactory govf = new GovFactory(GovType);
		if(govf.getGov().getGeneralGovType().equals(GovType)) {
			return new MainHud(PDI);
		}
		else {
			return new PickManageSelfType(PDI, (Class<? extends NoSubjects>) ((NoSubjects) govf.getGov()).getGeneralGovType());
		}
	}

	@Override
	public String getHeader() {
		return "Select " + GovFactory.getGovInstance(GovType).getTypeName() + " type.";
	}
	

	@Override
	public void init() {
		for(Class<? extends InspiredGov> gov:GovFactory.getGovInstance(GovType).getSelfGovs()) {
			GovFactory govf = new GovFactory(gov);
			((NoSubjects) govf.getGov()).getOwners().add(PDI.getName());
			if(govf.getGov().getSelfGovs().size() == 1) {
				if(govf.getGov().getSelfGovs().get(0).equals(govf.getGov().getClass())) {
					this.options.add(new PromptOption(this, govf.getGov().getTypeName(), new PickManageSuperGov(PDI, govf.getGov().getClass(), InspiredNations.plugin.global)));
				}
				else {
					this.options.add(new PromptOption(this, govf.getGov().getTypeName(), new PickManageSelfType(PDI, ((NoSubjects) govf.getGov()).getClass())));
				}
			}
			else {
				this.options.add(new PromptOption(this, govf.getGov().getTypeName(), new PickManageSelfType(PDI, ((NoSubjects) govf.getGov()).getClass())));
			}
		}
	}
}
