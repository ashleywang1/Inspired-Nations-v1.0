package com.github.InspiredOne.InspiredNations.Economy;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;


public class AccountCollection extends ArrayList<Account> implements Payable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4596555858007834733L;
	
	public AccountCollection() {
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
		Debug.print("Inside TransferMoney of accountCollection");
		Debug.print(this.getTotalMoney(monType));
		Debug.print(amount);
		
		if(this.getTotalMoney(monType).compareTo(amount) < 0) {
			throw new BalanceOutOfBoundsException();
		}
		else {
			Debug.print("inside the else statement of transferMoney 1");
			for(Account handle:this) {
				Debug.print("inside the else statement of transferMoney 2");
				if(handle.getTotalMoney(monType).compareTo(amount) >= 0) {
					Debug.print("inside the else statement of transferMoney 3");
					handle.transferMoney(amount, monType, accountTo);
					Debug.print("inside the else statement of transferMoney 5");
					break;
				}
				else {
					Debug.print("inside the else statement of transferMoney 4");
					handle.transferMoney(handle.getTotalMoney(monType), monType, accountTo);
				}
			}
		}
		Debug.print("outside of the else statement");
	}

	@Override
	public void addMoney(BigDecimal amount, Currency monType) throws NegativeMoneyTransferException {
		if(this.isEmpty()) {
			this.add(new Account());
		}
		Debug.print("Inside addMoney 1");
		this.get(0).addMoney(amount, monType);
		Debug.print("Inside addMoney 2");
	}
	
	
}
