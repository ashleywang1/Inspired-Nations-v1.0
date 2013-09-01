package com.github.InspiredOne.InspiredNations.Economy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NoMoneyOfThatType;
import com.github.InspiredOne.InspiredNations.ToolBox.IndexedMap;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;

public class Account implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7022565910007118461L;
	private static final String typeName = "Money";
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
	/**
	 * Returns the amount of money of a given currency in it's native value
	 * @param curren	the currency that you want to get
	 * @return	the amount of money in that currency type that the player owns
	 */
	public BigDecimal getMoney(Currency curren) {
		if(money.containsKey(curren)) {
			return Tools.cut(money.get(curren));
		}
		else {
			this.money.put(curren, BigDecimal.ZERO);
			return this.getMoney(curren);
		}
	}
	/**
	 * Returns the amount of money of the input currency adjusted to the output currency. 
	 * @param curren	the Currency that you would like to find the value of
	 * @return	the value of all the currency the player owns adjusted for the accounts
	 */
	public BigDecimal getAdjustedMoney(Currency input, Currency output) {
		if(money.containsKey(input)) {
			return Tools.cut(this.getRawMoney(input).multiply(output.getMoneymultiplyer()));
		}
		else {
			this.money.put(input, BigDecimal.ZERO);
			return this.getAdjustedMoney(input, output);
		}
	}
	/**
	 * Returns the total amount of money in this account adjusted to the output currency.
	 * @return	total amount of money adjusted for currency
	 */
	public BigDecimal getTotalAdjustedMoney(Currency output) {
		return this.getTotalRawMoney().multiply(output.getMoneymultiplyer());
	}
	/**
	 * Adds money to the account. If auto exchange is true, the money is put in first currency.
	 * If auto exchange is false, the currency is put with currencies of it's kind.
	 * @param curren	the currency of the money
	 * @param money		the amount of money in that currency to add
	 */
	public void addMoney(Currency curren, BigDecimal money) {
		if(this.AutoExchange) {
			this.addRawMoney(money.divide(curren.getMoneymultiplyer()));
		}
		else {
			if(this.money.containsKey(curren)) {
				this.money.put(curren, this.money.get(curren).add(money));
			}
			else this.money.put(curren, money);
		}
	}
	/**
	 * Removes money from account in the order of the currencies. Throws error if 
	 * account does not have enough value. 
	 * @param money	the amount to remove in this currency type
	 * @throws BalanceOutOfBoundsException
	 * @throws NoMoneyOfThatType
	 */
	public void removeMoney(Currency curren, BigDecimal money) throws BalanceOutOfBoundsException {
		this.removeRawMoney(money.divide(curren.getMoneymultiplyer()));
	}
	/**
	 * Takes money with the given currency and puts it in the other account
	 * @param money		the amount to transfer in this account's currency
	 * @param curren	the currency that the money is in
	 * @param account	the account to transfer to
	 * @throws BalanceOutOfBoundsException 
	 */
	public void transferMoney(BigDecimal money, Currency curren, Account account) throws BalanceOutOfBoundsException {
		this.transferRawMoney(money.divide(curren.getMoneymultiplyer()), account);

	}
	/**
	 * Transfers the raw amount from this account to the other account.
	 * @param money	the unadjusted amount to transfer
	 * @param account	the account to transfer to
	 * @throws BalanceOutOfBoundsException
	 */
	public void transferRawMoney(BigDecimal money, Account account) throws BalanceOutOfBoundsException {
		this.removeRawMoney(money);
		account.addRawMoney(money);
	}
	/**
	 * Gets the total amount of money in this account in Raw form.
	 * @return	total raw money
	 */
	public BigDecimal getTotalRawMoney() {
		BigDecimal output = BigDecimal.ZERO;
		for(Currency currency:money.keySet()) {
			output = output.add(this.getRawMoney(currency));
		}
		return output;
	}
	/**
	 * Returns the raw value of the given currency in this account.
	 * @param cur	the currency to look up
	 * @return		the raw value of the given currency
	 */
	public BigDecimal getRawMoney(Currency cur) {
		if(this.money.containsKey(cur)) {
			return this.money.get(cur).divide(cur.getMoneymultiplyer(), mcdown);
		}
		else {
			this.money.put(cur, BigDecimal.ZERO);
			return this.getRawMoney(cur);
		}
	}
	/**
	 * Adds the raw amount to the first currency in list.
	 * @param money	the raw amount to add to account
	 * @throws NoMoneyOfThatType 
	 */
	public void addRawMoney(BigDecimal money) {
		if(this.money.size() == 0) {
			this.money.put(Currency.DEFAULT, money.multiply(Currency.DEFAULT.getMoneymultiplyer()));
		}
		Currency cur = this.money.getIndex(0);
		BigDecimal amount = money.multiply(cur.getMoneymultiplyer());
		amount = amount.add(this.money.get(cur));
		this.money.put(cur, amount);
	}
	/**
	 * Removes the raw amount from the account in the order that the currencies are listed.
	 * @param money	the raw amount to remove
	 * @throws BalanceOutOfBoundsException
	 */
	public void removeRawMoney(BigDecimal money) throws BalanceOutOfBoundsException {
		if(this.getTotalRawMoney().compareTo(money) < 0) {
			throw new BalanceOutOfBoundsException();
		}
		else {
			boolean done = false;
			int iter = 0;
			while (!done) {
				Currency cur = this.money.getIndex(iter);
				BigDecimal curamount = this.getRawMoney(cur);
				if(curamount.compareTo(money) <= 0) {
					this.money.put(cur, BigDecimal.ZERO);
					money = money.subtract(curamount);
				}
				else {
					this.money.put(cur, curamount.subtract(money));
					done = true;
				}
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
}
