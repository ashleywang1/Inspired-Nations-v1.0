package com.github.InspiredOne.InspiredNations.Economy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.ToolBox.IndexedMap;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;

public class Account implements Serializable, Nameable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7022565910007118461L;
	private static final String typeName = "Money";
	private String name = "Money";
	private IndexedMap<Currency, BigDecimal> money = new IndexedMap<Currency, BigDecimal>();
	private boolean AutoExchange = true;
	private MathContext mcup = new MathContext(100, RoundingMode.UP);
	private MathContext mcdown = new MathContext(100, RoundingMode.DOWN);
	
	public Account() {
	}

	public String getTypeName() {
		return typeName;
	}
	/**
	 * 
	 * @return	the <code>HashMap</code> with all the money values in it
	 */
	public final IndexedMap<Currency, BigDecimal> getMoney() {
		return money;
	}
	/**
	 * Sets the HashMap of all the money values
	 * @param money	the HashMap to replace the current one
	 */
	public final void setMoney(IndexedMap<Currency, BigDecimal> money) {
		this.money = money;
	}

	public final BigDecimal getMoney(Currency getType, Currency valueType) {
		MoneyExchange exch = InspiredNations.plugin.Exchange;
		if(money.containsKey(getType)) {
			return exch.getValue(money.get(getType), getType, valueType);
		}
		else {
			money.put(getType, BigDecimal.ZERO);
			return this.getMoney(getType, valueType);
		}
	}
	
	public final BigDecimal getTotalMoney(Currency valueType) {
		MoneyExchange exch = InspiredNations.plugin.Exchange;
		BigDecimal output = BigDecimal.ZERO;
		for(Currency curren:money) {
			output.add(exch.getValue(money.get(curren), curren, valueType));
		}
		return output;
	}
	
	private final void addMoney(BigDecimal mon, Currency monType) {
		MoneyExchange exch = InspiredNations.plugin.Exchange;
		if(this.AutoExchange) {
			Currency exTo = money.getIndex(0);
			money.put(exTo, money.get(exTo).add(exch.exchange(mon, monType, exTo)));
		}
		else if(money.containsKey(monType)) {
			money.put(monType, money.get(monType).add(mon));
		}
		else {
			money.put(monType, BigDecimal.ZERO);
			this.addMoney(mon, monType);
		}
	}
	
	public final void transferMoney(BigDecimal mon, Currency monType, Account accountTo) throws BalanceOutOfBoundsException {
		MoneyExchange exch = InspiredNations.plugin.Exchange;
		if(getTotalMoney(monType).compareTo(mon) < 0) {
			throw new BalanceOutOfBoundsException();
		}
		else {
			boolean done = false;
			int iter = 0;
			while(!done) {
				Currency curren = money.getIndex(iter);
				BigDecimal amount = money.get(curren);
				if(amount.compareTo(exch.getValue(mon, monType, curren)) < 0) {
					mon.subtract(exch.getValue(amount, curren, monType));
					money.put(curren, BigDecimal.ZERO);
					accountTo.addMoney(amount, curren);
				}
				else {
					money.put(curren, money.get(curren).subtract(exch.getValue(mon, monType, curren)));
					accountTo.addMoney(exch.getValue(mon, monType, curren), curren);
					done = true;
				}
				iter++;
			}
		}
	}
	
	/**
	 * Does this account auto exchange it's currencies when adding more money?
	 * @return
	 */
	public boolean isAutoExchange() {
		return AutoExchange;
	}
	/**
	 * Sets the auto exchange policy
	 * @param autoExchange
	 */
	public void setAutoExchange(boolean autoExchange) {
		AutoExchange = autoExchange;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	} 
}
