package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimAndUnclaimLand.ClaimSignShop;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;

public class ClaimSignShopManager extends ActionManager<ClaimSignShop> {

	public ClaimSignShopManager(ClaimSignShop menu) {
		super(menu);
		Debug.print("Sign Shop Manager has been created");
		this.listeners.add(new ClaimSignShopListener(this));
	}

}