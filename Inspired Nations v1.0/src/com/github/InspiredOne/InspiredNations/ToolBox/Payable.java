package com.github.InspiredOne.InspiredNations.ToolBox;

import java.math.BigDecimal;
import java.math.MathContext;

import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;

public interface Payable extends Nameable, Notifyable{
	
	public void transferMoney(BigDecimal amount, Currency monType, Payable target) throws BalanceOutOfBoundsException, NegativeMoneyTransferException;
	public void addMoney(BigDecimal amount, Currency monType) throws NegativeMoneyTransferException;
	public BigDecimal getTotalMoney(Currency valueType, MathContext round);
}
