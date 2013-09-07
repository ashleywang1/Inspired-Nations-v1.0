package com.github.InspiredOne.InspiredNations.Hud.ManageGov;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.NoSubjects;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov.PickSuperGov;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.ContextData;

public class ManageGov extends OptionMenu {

	NoSubjects gov;
	public ManageGov(PlayerData PDI) {
		super(PDI);

	}

	@Override
	public String getPreOptionText() {
		return null;
	}

	@Override
	public Menu PreviousMenu() {
		return new PickSuperGov(PDI, this);
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getPassBy() {
		return false;
	}

	@Override
	public Menu getPassTo() {
		return null;
	}

	@Override
	public void init() {
		this.gov = (NoSubjects) this.getContext().getSessionData(ContextData.PromptData);
	}

}
