package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;

public class SelectNewCurrency extends TabSelectOptionMenu<Currency> {

	Menu previous;
	
	public SelectNewCurrency(PlayerData PDI, Menu previous) {
		super(PDI);
		this.previous = previous;
	}

	@Override
	public Menu getPreviousPrompt() {
		return previous;
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public void Init() {
		for(Currency curren:InspiredNations.Exchange.getExchangeMap().keySet()) {
			this.taboptions.add(curren);
		}
	}

	@Override
	public String getHeader() {
		return "Pick Currency";
	}

	@Override
	public TabSelectOptionMenu<?> getSelf() {
		return new SelectNewCurrency(PDI, previous);
	}
}
