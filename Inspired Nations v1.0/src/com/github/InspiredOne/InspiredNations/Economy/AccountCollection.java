package com.github.InspiredOne.InspiredNations.Economy;

import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.ToolBox.Alert;
import com.github.InspiredOne.InspiredNations.ToolBox.IndexedSet;
import com.github.InspiredOne.InspiredNations.ToolBox.Notifyable;
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;


public class AccountCollection extends IndexedSet<Account> implements Payable, Notifyable, Iterable<Account> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4596555858007834733L;
	private String name;
	
	public AccountCollection(String name) {
		this.name = name;
		this.add(new Account());
	}
	
	public BigDecimal getTotalMoney(Currency valueType) {
		BigDecimal output = BigDecimal.ZERO;
		for(Account valueCheck:this) {
			output = output.add(valueCheck.getTotalMoney(valueType));
		}
		return output;
	}

	public void transferMoney(BigDecimal amount, Currency monType, Payable accountTo) throws BalanceOutOfBoundsException, NegativeMoneyTransferException {
		
		if(this.getTotalMoney(monType).compareTo(amount) < 0) {
			throw new BalanceOutOfBoundsException();
		}
		else {
			for(Account handle:this) {
				if(handle.getTotalMoney(monType).compareTo(amount) >= 0) {
					handle.transferMoney(amount, monType, accountTo);
					break;
				}
				else {
					handle.transferMoney(handle.getTotalMoney(monType), monType, accountTo);
				}
			}
		}
	}

	@Override
	public void addMoney(BigDecimal amount, Currency monType) throws NegativeMoneyTransferException {
		if(this.isEmpty()) {
			this.add(new Account());
		}
		this.get(0).addMoney(amount, monType);
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getDisplayName(PlayerData PDI) {
		return this.getName() + " (" + this.getTotalMoney(PDI.getCurrency()) + " " + PDI.getCurrency() + ")";
	}
	public boolean isLinked() {
		boolean oneFound = false;
		for(PlayerData player:InspiredNations.playerdata.values()) {
			if(player.getAccounts() == this) {
				if(oneFound) {
					return true;
				}
				else {
					oneFound = true;
				}
			}
		}
		for(InspiredGov gov:InspiredNations.regiondata) {
			if(gov.getAccounts() == this) {
				if(oneFound) {
					return true;
				}
				else {
					oneFound = true;
				}
			}
		}
		return false;
	}
	@Override
	public void sendNotification(Alert msg) {
		for(PlayerData player:InspiredNations.playerdata.values()) {
			if(player.getAccounts().equals(this)) {
				player.sendNotification(msg);
			}
		}
	}
	
}
