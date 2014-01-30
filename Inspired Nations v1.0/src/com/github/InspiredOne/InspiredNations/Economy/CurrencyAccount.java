package com.github.InspiredOne.InspiredNations.Economy;

import java.io.Serializable;
import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;

public class CurrencyAccount implements Payable, Nameable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6553887792904870042L;
	private Currency curren;
	private BigDecimal amount;
	
	public CurrencyAccount(Currency curren) {
		this.curren = curren;
		this.amount = BigDecimal.ZERO;
	}
	public CurrencyAccount(Currency curren, BigDecimal amount) {
		this.curren = curren;
		this.amount = amount;
	}
	
	public Currency getCurrency() {
		return curren;
	}
	
	@Override
	public String getName() {
		return curren.getName();
	}

	@Override
	public void setName(String name) {
		curren.setName(name);
	}

	@Override
	public String getDisplayName(PlayerData viewer) {
		return curren.getName() + " (" + Tools.cut(InspiredNations.Exchange.getValue(amount, curren, viewer.getCurrency()))
				+ " " + viewer.getCurrency() + " : 1.00 " + curren + " ~ " + Tools.cut(InspiredNations.Exchange.getValue(BigDecimal.ONE, curren, viewer.getCurrency()))
				+ " " + viewer.getCurrency() + ")";
	}

	@Override
	public void transferMoney(BigDecimal amountTake, Currency monType,
			Payable target) throws BalanceOutOfBoundsException,
			NegativeMoneyTransferException {
		BigDecimal amountTemp = InspiredNations.Exchange.getValue(amountTake, monType, curren);
		if(amountTemp.compareTo(BigDecimal.ZERO) < 0 ) {
			throw new NegativeMoneyTransferException();
		}
		else if(amount.compareTo(amountTemp) < 0) {
			throw new BalanceOutOfBoundsException();
		}
		InspiredNations.Exchange.exchange(amountTake, monType, curren);
		this.amount = amount.subtract(amountTemp);
		target.addMoney(amountTemp, curren);

	}

	@Override
	public void addMoney(BigDecimal amountGive, Currency monType)
			throws NegativeMoneyTransferException {
		if(amountGive.compareTo(BigDecimal.ZERO) < 0 ) {
			throw new NegativeMoneyTransferException();
		}
		else {
			this.amount = this.amount.add(InspiredNations.Exchange.exchange(amountGive, monType, this.curren));
		}
	}

	@Override
	public BigDecimal getTotalMoney(Currency valueType) {
		return InspiredNations.Exchange.getValue(amount, curren, valueType);
	}
}
