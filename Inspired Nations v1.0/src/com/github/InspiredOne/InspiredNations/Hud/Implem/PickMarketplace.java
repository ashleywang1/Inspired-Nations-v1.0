package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.MarketPlace;
import com.github.InspiredOne.InspiredNations.Economy.Sellable;
import com.github.InspiredOne.InspiredNations.Hud.HelpMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public class PickMarketplace extends PassByOptionMenu {

	public PickMarketplace(PlayerData PDI) {
		super(PDI);
		help = new HelpMenu(PDI, this).addPage(
				"Welcome to the Marketplace! \n"
				);
	}

	@Override
	public String getPreOptionText() {
		return "";
	}

	@Override
	public String getHeader() {
		return "Pick A Market";
	}

	@Override
	public Menu getPreviousMenu() {
		return new MainHud(PDI);
	}

	@Override
	public void addOptions() {
		for(MarketPlace<?> market:InspiredNations.Markets) {
			this.options.add(new PromptOption(this, market.getName(), new BuyMenu(PDI, market)));
		}
	}

	@Override
	public void addActionManagers() {
		
	}

}
