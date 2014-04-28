package com.github.InspiredOne.InspiredNations.Economy;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NameAlreadyTakenException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;
import com.github.InspiredOne.InspiredNations.ToolBox.Alert;
import com.github.InspiredOne.InspiredNations.ToolBox.CardboardBox;
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;

public class NPC implements Serializable, Payable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8606492088647654688L;
	public InspiredNations plugin;
	AccountCollection accounts = new AccountCollection("NPC");
	HashMap<CardboardBox,Account> buy = new HashMap<CardboardBox, Account>();

	public NPC() {
		plugin = InspiredNations.plugin;
	}
	
	@Override
	public String getName() {
		return "NPC";
	}

	@Override
	public void setName(String name) throws NameAlreadyTakenException {
		
	}

	@Override
	public String getDisplayName(PlayerData viewer) {
		return this.getName();
	}

	@Override
	public void sendNotification(Alert msg) {
		
	}

	@Override
	public void transferMoney(BigDecimal amount, Currency monType,
			Payable target) throws BalanceOutOfBoundsException,
			NegativeMoneyTransferException {
		if(amount.compareTo(BigDecimal.ZERO) < 0) {
			throw new NegativeMoneyTransferException();
		}
		if(amount.compareTo(accounts.getTotalMoney(monType)) > 0) {
			amount.subtract(accounts.getTotalMoney(monType));
			accounts.transferMoney(accounts.getTotalMoney(monType), monType, target);
			for(Account test:buy.values()) {
				if(amount.compareTo(test.getTotalMoney(monType)) > 0) {
					amount.subtract(test.getTotalMoney(monType));
					test.transferMoney(test.getTotalMoney(monType), monType, target);
				}
				else {
					test.transferMoney(amount, monType, target);
					amount = BigDecimal.ZERO;
					break;
				}
			}
		}
		else {
			throw new BalanceOutOfBoundsException();
		}
	}

	@Override
	public void addMoney(BigDecimal amount, Currency monType)
			throws NegativeMoneyTransferException {
		this.accounts.addMoney(amount, monType);
	}

	@Override
	public BigDecimal getTotalMoney(Currency valueType) {
		BigDecimal output = accounts.getTotalMoney(valueType);
		
		for(Account account:buy.values()) {
			output.add(account.getTotalMoney(valueType));
		}
		return output;
	}


}
