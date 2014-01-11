package com.github.InspiredOne.InspiredNations.Hud.Implem;

import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;

public class getMoneyOption extends Option {

	PlayerData PDI;
	
	public getMoneyOption(OptionMenu menu, String label, PlayerData PDI) {
		super(menu, label);
		this.PDI = PDI;
	}

	@Override
	public Menu response(String input) {
		System.out.println("Inside GetMoneyOption repsonse");
		System.out.println(PDI.getAccounts());
		PDI.getAccounts().addMoney(new BigDecimal(1000), Currency.DEFAULT);
		System.out.println("Inside GetMoneyOption repsonse 2");
		return menu;
	}

}
