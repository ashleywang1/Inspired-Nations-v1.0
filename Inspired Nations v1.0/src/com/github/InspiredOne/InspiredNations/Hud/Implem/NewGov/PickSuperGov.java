package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerSubjectGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;

public class PickSuperGov extends PassByOptionMenu {

	GovFactory Govf;
	Class<? extends OwnerGov> superGov;
	
	public PickSuperGov(PlayerData PDI, GovFactory Govf, Class<? extends OwnerGov> superGov) {
		super(PDI);
		this.Govf = Govf;
		this.superGov = superGov;
	}
	
	public PickSuperGov(PlayerData PDI, GovFactory Govf) {
		super(PDI);
		this.Govf = Govf;
		this.superGov = InspiredNations.global.getClass();
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
		return new PickSelfType(PDI, (Class<? extends OwnerGov>) Govf.getGov().getGeneralGovType());
	}

	@Override
	public void init() {
		OwnerGov superGovObj = GovFactory.getGovInstance(superGov);
		
		for(Class<? extends OwnerGov> govCheck:superGovObj.getSubGovs()) {
			if(Govf.getGov().isSubOfClass(govCheck)) {
				for(OwnerSubjectGov gov:PDI.getCitizenship(govCheck)) {
					if(govCheck.equals(Govf.getGov().getSuperGov())) {
						this.options.add(new PromptOption(this, gov.getName(), new WarningAlreadyOwnOne(PDI, Govf.withSuperGov(gov))));
					}
					else {
						this.options.add(new PromptOption(this, gov.getName(), new PickSuperGov(PDI, Govf, superGov)));
					}
				}
			}
		}
		
		
		for(InspiredGov gov:PDI.getCitizenship(Govf.getGov().getSuperGov())) {
			this.options.add(new PromptOption(this, gov.getName(), new WarningAlreadyOwnOne(PDI, Govf.withSuperGov(gov))));
		}
	}
}
