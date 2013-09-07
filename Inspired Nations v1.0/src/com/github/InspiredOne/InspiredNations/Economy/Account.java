package com.github.InspiredOne.InspiredNations.Economy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NoMoneyOfThatType;
import com.github.InspiredOne.InspiredNations.ToolBox.IndexedMap;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;

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
	public IndexedMap<Currency, BigDecimal> getMoney() {
		return money;
	}
	/**
	 * Sets the HashMap of all the money values
	 * @param money	the HashMap to replace the current one
	 */
	public void setMoney(IndexedMap<Currency, BigDecimal> money) {
		this.money = money;
	}

	public BigDecimal getMoney(Currency getType, Currency valueType) {
		if(money.containsKey(getType)) {
			return getValue(money.get(getType), getType, valueType);
		}
		else {
			money.put(getType, BigDecimal.ZERO);
			return this.getMoney(getType, valueType);
		}
	}
	
	public BigDecimal getTotalMoney(Currency valueType) {
		BigDecimal output = BigDecimal.ZERO;
		for(Currency curren:money) {
			output.add(this.getValue(money.get(curren), curren, valueType));
		}
		return output;
	}
	
	private void addMoney(BigDecimal mon, Currency monType) {
		if(this.AutoExchange) {
			Currency exTo = money.getIndex(0);
			money.put(exTo, money.get(exTo).add(this.exchange(mon, monType, exTo)));
		}
		else if(money.containsKey(monType)) {
			money.put(monType, money.get(monType).add(mon));
		}
		else {
			money.put(monType, BigDecimal.ZERO);
			this.addMoney(mon, monType);
		}
	}
	
	public void transferMoney(BigDecimal mon, Currency monType, Account accountTo) throws BalanceOutOfBoundsException {
		if(getTotalMoney(monType).compareTo(mon) < 0) {
			throw new BalanceOutOfBoundsException();
		}
		else {
			boolean done = false;
			int iter = 0;
			while(!done) {
				Currency curren = money.getIndex(iter);
				BigDecimal amount = money.get(curren);
				if(amount.compareTo(this.getValue(mon, monType, curren)) < 0) {
					mon.subtract(this.getValue(amount, curren, monType));
					money.put(curren, BigDecimal.ZERO);
					accountTo.addMoney(amount, curren);
				}
				else {
					money.put(curren, money.get(curren).subtract(this.getValue(mon, monType, curren)));
					accountTo.addMoney(getValue(mon, monType, curren), curren);
					done = true;
				}
				iter++;
			}
		}
	}
	
	public BigDecimal getValue(BigDecimal  mon, Currency monType, Currency valueType ) {
		return mon.divide(monType.getMoneymultiplyer()).multiply(valueType.getMoneymultiplyer());
	}
	
	public BigDecimal exchange(BigDecimal mon, Currency exFromType, Currency exToType) {
		//TODO make this so it works with exchange in main class
		return this.getValue(mon, exFromType, exToType);
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
