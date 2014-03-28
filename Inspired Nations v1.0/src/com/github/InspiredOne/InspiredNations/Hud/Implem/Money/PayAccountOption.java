package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuAlert;
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
		String[] args = input.split(" ");
		Debug.print("Inside PayAccountOption.response");
		try {
			BigDecimal amount = new BigDecimal(args[0]);
	
			try {
				accountsFrom.transferMoney(amount, PDI.getCurrency(), accountTo);
				accountTo.sendNotification(MenuAlert.RECEIVED_MONEY(amount, PDI.getCurrency(), PDI));
				accountsFrom.sendNotification(MenuAlert.TRANSFER_SUCCESSFUL(amount, PDI.getCurrency(), PDI, accountTo));
				//accountTo.sendNotification(MenuAlert.RECEIVED_MONEY(amount, PDI.getCurrency(), PDI));
			} catch (BalanceOutOfBoundsException e) {
				e.printStackTrace();
				Debug.print("Inside BalanceOutOfBoundsException in the PayAccountOption Menu");
				menu.setError(MenuError.NOT_ENOUGH_MONEY());
			} catch (NegativeMoneyTransferException e) {
				menu.setError(MenuError.NEGATIVE_AMOUNTS_NOT_ALLOWED(amount));
			}

			return menu.getNewSelf();
		}
		catch (Exception ex) {
			return menu.getNewSelf().setError(MenuError.INVALID_NUMBER_INPUT());
		}
	}
}
