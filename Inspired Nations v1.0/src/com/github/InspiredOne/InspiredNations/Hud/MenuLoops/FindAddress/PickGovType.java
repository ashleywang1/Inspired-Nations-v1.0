package com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;

public class PickGovType extends TabSelectOptionMenu {

	public PickGovType(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public String postTabListPreOptionsText() {
		return null;
	}

	@Override
	public void Init() {
		// TODO Auto-generated method stub

	}

	@Override
	public Menu getPreviousPrompt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

}
