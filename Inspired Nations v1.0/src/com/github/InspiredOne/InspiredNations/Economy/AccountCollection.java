package com.github.InspiredOne.InspiredNations.Economy;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;


public class AccountCollection extends ArrayList<Account> implements Payable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4596555858007834733L;
	
	public AccountCollection() {

	}
	public BigDecimal getTotalMoney(Currency valueType) {
		BigDecimal output = BigDecimal.ZERO;
		for(Account valueCheck:this) {
			output = output.add(valueCheck.getTotalMoney(valueType));
		}
		return output;
	}

	public void transferMoney(BigDecimal amount, Currency monType, Payable accountTo) throws BalanceOutOfBoundsException {
		boolean done = true;
		int iter = 0;
		if(this.getTotalMoney(monType).compareTo(amount) < 0) {
			throw new BalanceOutOfBoundsException();
		}
		else {
			while(!done) {
				Account handle = this.get(iter);
				if(handle.getTotalMoney(monType).compareTo(amount) > 0) {
					handle.transferMoney(amount, monType, accountTo);
					done = true;
				}
				else {
					handle.transferMoney(handle.getTotalMoney(monType), monType, accountTo);
				}
			}
		}
	}
	@Override
	public void addMoney(BigDecimal amount, Currency monType) {
		System.out.println(this.isEmpty());
		if(this.isEmpty()) {
			this.add(new Account());
			this.addMoney(amount, monType);
		}
		System.out.println("Just before add money 1");
		this.get(0).addMoney(amount, monType);
		System.out.println("Just before add money 2");
	}
	
	
}
