package com.github.InspiredOne.InspiredNations.Economy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import com.github.InspiredOne.InspiredNations.ToolBox.Tools;

public abstract class Account implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7022565910007118461L;
	
	private BigDecimal money = BigDecimal.ZERO;
	private Currency currency = new Currency(BigDecimal.ONE, "Coin");
	private MathContext mcup = new MathContext(100, RoundingMode.UP);
	private MathContext mcdown = new MathContext(100, RoundingMode.DOWN);
	
	public Account() {

	}

	public BigDecimal getMoney() {
		/**Returns the money in the account adjusted for it's value*/
		return Tools.cut(money.multiply(currency.getMoneymultiplyer(), mcup));
	}


	public void setMoney(BigDecimal money) {
		/**Takes the adjusted amount of money, and set's it as the unadjusted balance*/
		this.money = money.divide(currency.getMoneymultiplyer(), mcup);
	}

	
	public void addMoney(BigDecimal money) {
		/**Adds the adjusted amount to the unadjusted balance*/
		this.money = this.money.add(money.divide(currency.getMoneymultiplyer(), mcup));
	}
	
	
	public void removeMoney(BigDecimal money) {
		/**Removes the adjusted amount from the unadjusted balance*/
		this.money = this.money.subtract(money.divide(currency.getMoneymultiplyer(), mcdown));
	}
	
	
	public void transferMoney(BigDecimal money, Account account) {
		/**Takes money adjusted for this account, and puts it in the other account*/
		this.removeMoney(money);
		account.addMoney(money);
	}
	
	
	public void transferRawMoney(BigDecimal money, Account account) {
		/**Transfers the raw amount from this account to the other account*/
		this.removeRawMoney(money);
		account.addRawMoney(money);
	}
	
	
	public BigDecimal getRawMoney() {
		/**Returns unadjusted balance*/
		return money;
	}


	public void setRawMoney(BigDecimal money) {
		/**Sets the unadjusted balance*/
		this.money = money;
	}
	
	
	public void addRawMoney(BigDecimal money) {
		this.money = this.money.add(money);
	}
	
	
	public void removeRawMoney(BigDecimal money) {
		this.money = this.money.subtract(money);
	}

	public Currency getCurrency() {
		return currency;
	}


	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
