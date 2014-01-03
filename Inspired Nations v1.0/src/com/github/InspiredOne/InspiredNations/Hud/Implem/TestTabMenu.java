package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.Implem.Country;
import com.github.InspiredOne.InspiredNations.Governments.Implem.House;
import com.github.InspiredOne.InspiredNations.Governments.Implem.Town;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.TabTestNameable;

public class TestTabMenu extends TabSelectOptionMenu {

	public TestTabMenu(PlayerData PDI) {
		super(PDI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getHeader() {
		return "Tab Menu Test";
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
	public String postTabListPreOptionsText() {
		return "Text before options";
	}

	@Override
	public void Init() {
		Country country = GovFactory.getGovInstance(Country.class);
		Town town = GovFactory.getGovInstance(Town.class);
		House house = GovFactory.getGovInstance(House.class);
		this.taboptions.add(new TabTestNameable("Option 1"));
		this.taboptions.add(new TabTestNameable("Option 2"));
		this.taboptions.add(new TabTestNameable("Option 3"));
		this.taboptions.add(new TabTestNameable("Option 4"));
		this.taboptions.add(new TabTestNameable("Option 5"));
		this.taboptions.add(new TabTestNameable("Option 6"));
		this.taboptions.add(new TabTestNameable("Option 7"));
		this.taboptions.add(new TabTestNameable("Option 8"));
		this.taboptions.add(new TabTestNameable("Option 9"));
		this.taboptions.add(new TabTestNameable("Option 10"));
		this.taboptions.add(new TabTestNameable("Option 11"));
		this.taboptions.add(new TabTestNameable("Option 12"));
	}

	@Override
	public Menu PreviousMenu() {
		return new MainHud(PDI);
	}

}
