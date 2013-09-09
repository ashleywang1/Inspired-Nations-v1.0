package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.NoSubjects;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.ContextData;

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
		System.out.println("Inside PassBy of 1: " + this.getHeader());
		NoSubjects gov = ((NoSubjects) Govf.getGov());
		System.out.println("Inside PassBy of 2: " + this.getHeader());
		if(!PDI.getCitizenship(gov.getCommonGov()).isEmpty()) {
			System.out.println("Inside PassBy of 3: " + this.getHeader());
			System.out.println(PDI.getCitizenship(gov.getCommonGov()).get(0).getName());
			System.out.println(gov.getCommonGovObj() == null);
			if(gov.getCommonGovObj() != PDI.getCitizenship(gov.getCommonGov()).get(0)) {
				System.out.println("Inside PassBy of 4: " + this.getHeader());
				return false;
			}
			else {
				System.out.println("Inside PassBy of 6: " + this.getHeader());
				return true;
			}
		}
		System.out.println("Inside PassBy of 5: " + this.getHeader());
		return true;
	}

	@Override
	public Menu getPassTo() {
		System.out.println("Inside PassTo of 1: " + this.getHeader());
		return new PickName(PDI, Govf);
	}

	@Override
	public void init() {
		System.out.println("Inside init of 1: " + this.getHeader());
		options.add(new PromptOption(this, "Yes", new PickName(PDI, Govf)));
		options.add(new PromptOption(this, "No", new MainHud(PDI)));
	}

}
