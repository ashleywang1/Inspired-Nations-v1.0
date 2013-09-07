package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;


import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.NoSubjects;
import com.github.InspiredOne.InspiredNations.Hud.DataPassPromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;

public class PickSelfType extends PassByOptionMenu {

	Class<? extends NoSubjects> GovType;
	Menu last;
	
	
	public PickSelfType(PlayerData PDI, Class<? extends NoSubjects> GovType, Menu last) {
		super(PDI);
		this.GovType = GovType;
		this.last = last;
	}

	@Override
	public String getPreOptionText() {
		return "Pick what kind of " + GovFactory.getGovInstance(GovType).getTypeName()
				+ " you are considering.";
	}

	@Override
	public String getHeader() {
		return "Select " + GovFactory.getGovInstance(GovType).getTypeName() + " type.";
	}

	@Override
	public Menu PreviousMenu() {
		return new MainHud(PDI);
	}

	@Override
	public void init() {
		for(Class<? extends InspiredGov> gov:GovFactory.getGovInstance(GovType).getSelfGovs()) {
			GovFactory govf = new GovFactory(gov);
			((NoSubjects) govf.getGov()).getOwners().add(PDI.getName());
			this.options.add(new DataPassPromptOption(this, govf.getGov().getTypeName(), new PickSuperGov(PDI, last), govf));
		}
	}

}
