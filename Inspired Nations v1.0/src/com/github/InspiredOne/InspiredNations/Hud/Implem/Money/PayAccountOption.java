package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;

public class PayAccountOption extends Option {

	Payable accountsFrom;
	Payable accountTo;
	PlayerData PDI;
	
	public PayAccountOption(PlayerData PDI, OptionMenu menu, String label, Payable accountsFrom, Payable accountTo) {
		super(menu, label);
		this.accountsFrom = accountsFrom;
		this.accountTo = accountTo;
		this.PDI = PDI;
	}

	@Override
	public Menu response(String input) {
		try{
			String[] args = input.split(" ");
			BigDecimal amount = new BigDecimal(args[1]);
			if(amount.compareTo(accountsFrom.getTotalMoney(PDI.getCurrency())) > 0) {
				menu.setError(MenuError.NOT_ENOUGH_MONEY());
			}
			else {
				accountsFrom.transferMoney(amount, PDI.getCurrency(), accountTo);
			}
			
			//TODO Add some message thing here to tell player that transaction was successful.
			return menu.getSelf();
		}
		catch(Exception ex) {
			return menu.getSelf().setError(MenuError.INVALID_NUMBER_INPUT());
		}
	}

}
