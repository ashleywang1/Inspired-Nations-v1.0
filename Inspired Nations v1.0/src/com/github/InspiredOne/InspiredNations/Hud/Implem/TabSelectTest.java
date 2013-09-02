package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.Implem.House;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;

public class TabSelectTest extends TabSelectOptionMenu {

	public TabSelectTest(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public void Init() {
		for(Nameable name:PDI.getCitizenship(House.class)) {
			this.taboptions.add(name);
		}
	}

	@Override
	public Menu PreviousMenu() {
		return new MainHud(PDI);
	}

	@Override
	public String getHeader() {
		return "Yay";
	}

}
