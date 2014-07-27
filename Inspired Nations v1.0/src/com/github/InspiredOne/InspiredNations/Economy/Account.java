package com.github.InspiredOne.InspiredNations.Economy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;
import com.github.InspiredOne.InspiredNations.ToolBox.Messaging.Alert;

public class Account implements Serializable, Nameable, Payable, Cloneable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7022565910007118461L;
	private static final String typeName = "Money";
	private String name = "Money";
	private ArrayList<CurrencyAccount> money = new ArrayList<CurrencyAccount>();
	private boolean AutoExchange = true;

	
	public Account() {
		//TODO remove these for later. figure out how to handle when a player is first joining a server with no
		// currencies already
	}
	public Account(String name) {
		this.setName(name);
	}

	public String getTypeName() {
		return typeName;
	}
	public Account clone() {
		Account output = new Account();
		output.name = this.name;
		output.AutoExchange = this.AutoExchange;
		ArrayList<CurrencyAccount> currenaccount = new ArrayList<CurrencyAccount>();
		for(CurrencyAccount acc:this.money){
			currenaccount.add(acc.clone());
		}
		return output;
	}
	/**
	 * @return	the <code>HashMap</code> with all the money values in it
	 */
	public final ArrayList<CurrencyAccount> getMoney() {
		return money;
	}
	/**
	 * Sets the HashMap of all the money values
	 * @param money	the HashMap to replace the current one
	 */
	public final void setMoney(ArrayList<CurrencyAccount> money) {
		this.money = money;
	}

	public final BigDecimal getMoney(Currency getType, Currency valueType) {
		MoneyExchange exch = InspiredNations.Exchange;
		CurrencyAccount curren = getCurrenAccount(getType);
		boolean contains = curren != null;
		
		if(contains) {
			return exch.getExchangeValue(curren.getTotalMoney(getType, InspiredNations.Exchange.mcdown), getType, valueType);
		}
		else {
			money.add(new CurrencyAccount(getType, BigDecimal.ZERO));
			return this.getMoney(getType, valueType);
		}
	}
	
	public final BigDecimal getTotalMoney(Currency valueType, MathContext round) {
		BigDecimal output = BigDecimal.ZERO;
		for(CurrencyAccount curren:money) {
			output = output.add(curren.getTotalMoney(valueType, round));
		}
		return output;
	}
	
	public final void addMoney(BigDecimal mon, Currency monType) throws NegativeMoneyTransferException {
		if(mon.compareTo(BigDecimal.ZERO) < 0) {
			throw new NegativeMoneyTransferException();
		}


		
		if(this.AutoExchange) {
			if(this.money.isEmpty()) {
				this.money.add(new CurrencyAccount(Currency.DEFAULT, BigDecimal.ZERO));
			}
			money.get(0).addMoney(mon, monType);
		}
		else {
			// looks to see if accountCollection has MonType
			CurrencyAccount account = getCurrenAccount(monType);
			account.addMoney(mon, monType);
		}
	}
	
	public final void transferMoney(BigDecimal mon, Currency monType, Payable accountTo) throws BalanceOutOfBoundsException, NegativeMoneyTransferException {
		if(getTotalMoney(monType, InspiredNations.Exchange.mcdown).compareTo(mon) < 0) {
			throw new BalanceOutOfBoundsException();
		}
		else {
			for(CurrencyAccount curren:money) {
				//BigDecimal amountup = curren.getTotalMoney(monType, InspiredNations.Exchange.mcup);
				BigDecimal amountdown = curren.getTotalMoney(monType, InspiredNations.Exchange.mcdown);
				
				if(mon.compareTo(amountdown) >= 0) {
					money.set(money.indexOf(curren), new CurrencyAccount(curren.getCurrency()));
					curren.transferMoney(amountdown, monType, accountTo);
					mon = mon.subtract(amountdown);
				}
				else {
					curren.transferMoney(mon, monType, accountTo);
					break;
				}
			}
		}
	}
	
	private CurrencyAccount getCurrenAccount(Currency monType) {
		CurrencyAccount account = new CurrencyAccount(monType);
		for(CurrencyAccount curren:this.money) {
			if(curren.getCurrency().equals(monType)) {
				return curren;
			}
		}
		this.money.add(account);
		return account;
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

	public void setName(String name) {
		this.name = name;
	}
	
	private boolean isShared() {
		boolean foundOne = false;
		for(InspiredGov gov:InspiredNations.regiondata) {
			if(gov.getAccounts().contains(this)) {
				if(foundOne) {
					return true;
				}
				else {
					foundOne = true;
				}
			}
		}
		for(PlayerData player:InspiredNations.playerdata.values()) {
			if(player.getAccounts().contains(this)) {
				if(foundOne) {
					return true;
				}
				else {
					foundOne = true;
				}
			}
		}
		return false;
	}
	
	public boolean containsCurrency(Currency curren) {
		for(CurrencyAccount acc:this.money) {
			if(acc.getCurrency().equals(curren)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String getDisplayName(PlayerData PDI) {
		if(isShared()){
			return this.getName() + " (" + Tools.cut(this.getTotalMoney(PDI.getCurrency(), InspiredNations.Exchange.mcdown)) +" " + PDI.getCurrency() + ") Shared";
		}
		else {
			return this.getName() + " (" + Tools.cut(this.getTotalMoney(PDI.getCurrency(), InspiredNations.Exchange.mcdown)) +" " + PDI.getCurrency() + ")";
		}
	}
	
	@Override
	public void sendNotification(Alert msg) {
		for(PlayerData player:InspiredNations.playerdata.values()) {
			for(Account account: player.getAccounts()) {
				if(account.equals(this)) {
					player.sendNotification(msg);
				}
			}
		}
	}
}
