package com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress;

import java.util.TreeSet;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;

public abstract class PickCurrencyGeneral extends TabSelectOptionMenu<Currency> {

	Menu previous;
	public PickCurrencyGeneral(PlayerData PDI, Menu previous) {
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
		TreeSet<Currency> currens = new TreeSet<Currency>(InspiredNations.Exchange.getExchangeMap().keySet());
		for(Currency curren:currens) {
			this.taboptions.add(curren);
		}
		insertOptions();
	}

	@Override
	public String getHeader() {
		return "Select Currency";
	}
	
	public abstract void insertOptions();

}
