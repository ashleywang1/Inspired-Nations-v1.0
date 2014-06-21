package com.github.InspiredOne.InspiredNations.Economy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NameAlreadyTakenException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;
import com.github.InspiredOne.InspiredNations.ToolBox.Alert;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;

public class CurrencyAccount implements Payable, Nameable, Serializable, Cloneable {

	/**
	 * Currency Discipline
	 * 
	 * 1. Every money value must be paired with a money type.
	 * 2. Negative transfers are never allowed.
	 * 3. Any money you receive is rounded up. Any money you give is rounded down.
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
	
	public CurrencyAccount clone() {
		CurrencyAccount output = new CurrencyAccount(this.curren, this.amount);
		return output;
	}
	
	public Currency getCurrency() {
		return curren;
	}
	public void setCurrency(Currency curren) { 
		this.curren = curren;
	}
	
	@Override
	public String getName() {
		return curren.getName();
	}

	@Override
	public void setName(String name) throws NameAlreadyTakenException {
		curren.setName(name);
	}

	@Override
	public String getDisplayName(PlayerData viewer) {
		return curren.getName() + " (" + Tools.cut(this.getTotalMoney(this.curren, InspiredNations.Exchange.mcdown)) + "~"
				+ Tools.cut(InspiredNations.Exchange.getExchangeValue(amount, curren, viewer.getCurrency()))
				+ " " + viewer.getCurrency() + ":1.00 " + "~" + Tools.cut(InspiredNations.Exchange.getExchangeValue(BigDecimal.ONE, curren, viewer.getCurrency()))
				+ " " + viewer.getCurrency() + ")";
	}

	@Override
	public void transferMoney(BigDecimal amountTake, Currency monType,
			Payable target) throws BalanceOutOfBoundsException,
			NegativeMoneyTransferException {
		
		BigDecimal amountTempup = InspiredNations.Exchange.getTransferValue(amountTake, monType, curren, InspiredNations.Exchange.mcup);
		BigDecimal amountTempdown = InspiredNations.Exchange.getTransferValue(amountTake, monType, curren, InspiredNations.Exchange.mcdown);
		if(amountTempdown.compareTo(BigDecimal.ZERO) < 0 ) {
			throw new NegativeMoneyTransferException();
		}
		else if(amount.compareTo(amountTempdown) < 0) {
			throw new BalanceOutOfBoundsException();
		}
		else {
			this.amount = amount.subtract(amountTempdown);
			target.addMoney(amountTempup, curren);
		}
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
	public BigDecimal getTotalMoney(Currency valueType, MathContext round) {
		return InspiredNations.Exchange.getExchangeValue(amount, curren, valueType, InspiredNations.Exchange.mcdown);
	}
	
	@Override
	public void sendNotification(Alert msg) {
		for(PlayerData player:InspiredNations.playerdata.values()) {
			for(Account account:player.getAccounts()) {
				if(account.getMoney().contains(this)) {
					account.sendNotification(msg);
					break;
				}
			}
		}
	}
}
