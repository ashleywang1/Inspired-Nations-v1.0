package com.github.InspiredOne.InspiredNations.ToolBox;

import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;

public interface Payable {
	public void transferMoney(BigDecimal amount, Currency monType, Payable target) throws BalanceOutOfBoundsException;
	public void addMoney(BigDecimal amount, Currency monType);
	public BigDecimal getTotalMoney(Currency valueType);
}
