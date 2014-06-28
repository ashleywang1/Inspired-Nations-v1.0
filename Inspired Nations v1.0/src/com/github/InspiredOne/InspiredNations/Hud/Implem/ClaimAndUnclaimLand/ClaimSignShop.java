package com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimAndUnclaimLand;

import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Sellable;
import com.github.InspiredOne.InspiredNations.Governments.Implem.SignShop;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;

public class ClaimSignShop extends TabSelectOptionMenu<Sellable> {

	private Menu previous;
	private List<Sellable> sell;
	public SignShop shop = new SignShop();
	public ClaimSignShop(PlayerData PDI, Menu previous, List<Sellable> sell) {
		super(PDI);
		this.previous = previous;
		this.sell = sell;
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
	public void addTabOptions() {
		this.taboptions.addAll(sell);
	}

	@Override
	public void addOptions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getHeader() {
		return "Claim Sign Shop";
	}

}
