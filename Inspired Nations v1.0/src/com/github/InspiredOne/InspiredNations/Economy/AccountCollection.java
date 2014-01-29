package com.github.InspiredOne.InspiredNations.Economy;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;


public class AccountCollection extends ArrayList<Account> implements Payable {
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
	
}
