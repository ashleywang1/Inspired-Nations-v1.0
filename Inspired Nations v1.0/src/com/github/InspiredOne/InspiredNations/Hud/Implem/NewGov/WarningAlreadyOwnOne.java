package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;

public class WarningAlreadyOwnOne extends OptionMenu {

	GovFactory Govf;
	
	public WarningAlreadyOwnOne(PlayerData PDI, GovFactory Govf) {
		super(PDI);
		this.Govf = Govf;
	}

	@Override
	public String getPreOptionText() {
		return "Warning! Continuing will result in the loss of ownership of your current " + Govf.getGov().getTypeName() + ". " +
				"Are you sure you want to continue?";
	}

	@Override
	public String getHeader() {
		return "Warning!";
	}

	@Override
	public Menu PreviousMenu() {
		return new PickSuperGov(PDI, Govf);
	}

	@Override
	public boolean getPassBy() {
		OwnerGov gov = ((OwnerGov) Govf.getGov());
		if(!PDI.getCitizenship(gov.getCommonGov()).isEmpty()) {
			if(gov.getCommonGovObj() != PDI.getCitizenship(gov.getCommonGov()).get(0)) {
				return false;
			}
			else {
				return true;
			}
		}
		return true;
	}

	@Override
	public Menu getPassTo() {
		return new PickName(PDI, Govf);
	}

	@Override
	public void init() {
		System.out.println("Inside init of 1: " + this.getHeader());
		options.add(new PromptOption(this, "Yes", new PickName(PDI, Govf)));
		options.add(new PromptOption(this, "No", new MainHud(PDI)));
	}

}
