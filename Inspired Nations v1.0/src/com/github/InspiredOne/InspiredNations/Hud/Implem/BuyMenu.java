package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.MarketPlace;
import com.github.InspiredOne.InspiredNations.Economy.Sellable;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;

public class BuyMenu extends TabSelectOptionMenu<Sellable> {

	MarketPlace<?> market;
	
	public BuyMenu(PlayerData PDI, MarketPlace<?> market) {
		super(PDI);
		this.market = market;
	}

	@Override
	public Menu getPreviousPrompt() {
		return new PickMarketplace(PDI);
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public String getHeader() {
		return market.getName();
	}

	@Override
	public void addTabOptions() {
		for(Sellable item:market.getSales(this.PDI)) {
			this.taboptions.add(item);
		}
	}

	@Override
	public void addOptions() {
		if(this.taboptions.size() > 0) {
			this.options.add(new MakePurchaseOption(this, "Buy", this.getData()));
		}
	}

	@Override
	public void addActionManagers() {
	}

}
