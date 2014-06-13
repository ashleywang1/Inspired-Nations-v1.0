package com.github.InspiredOne.InspiredNations.Economy;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.ToolBox.Alert;
import com.github.InspiredOne.InspiredNations.ToolBox.IndexedMap;
import com.github.InspiredOne.InspiredNations.ToolBox.IndexedSet;
import com.github.InspiredOne.InspiredNations.ToolBox.Notifyable;
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;


public class AccountCollection extends IndexedSet<Account> implements Payable, 
Notifyable, Iterable<Account>, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4596555858007834733L;
	private String name;
	
	public AccountCollection(String name) {
		this.name = name;
		this.add(new Account());
	}
	
	public AccountCollection clone() {
		AccountCollection output = new AccountCollection(name);
		for(Account acc:this) {
			output.add(acc);
		}
		return output;	
	}
	
	@Override
	public BigDecimal getTotalMoney(Currency valueType, MathContext round) {
		BigDecimal output = BigDecimal.ZERO;
		for(Account valueCheck:this) {
			output = output.add(valueCheck.getTotalMoney(valueType, round));
		}
		return output;
	}

	public void transferMoney(BigDecimal amount, Currency monType, Payable accountTo) throws BalanceOutOfBoundsException, NegativeMoneyTransferException {
		
		if(this.getTotalMoney(monType, InspiredNations.Exchange.mcdown).compareTo(amount) < 0) {
			throw new BalanceOutOfBoundsException();
		}
		else {
			for(Account handle:this) {
				if(handle.getTotalMoney(monType, InspiredNations.Exchange.mcdown).compareTo(amount) >= 0) {
					handle.transferMoney(amount, monType, accountTo);
					break;
				}
				else {
					amount = amount.subtract(handle.getTotalMoney(monType, InspiredNations.Exchange.mcdown));
					handle.transferMoney(handle.getTotalMoney(monType, InspiredNations.Exchange.mcdown), monType, accountTo);
				}
				
			}
		}
	}

	@Override
	public void addMoney(BigDecimal amount, Currency monType) throws NegativeMoneyTransferException {
		if(this.isEmpty()) {
			this.add(new Account());
		}
		this.get(0).addMoney(amount, monType);
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getDisplayName(PlayerData PDI) {
		
		return this.getName() + " (" + TextColor.VALUE(PDI) + 
				Tools.cut(this.getTotalMoney(PDI.getCurrency(), InspiredNations.Exchange.mcdown)) +TextColor.UNIT(PDI)
				+ " " + PDI.getCurrency() + ")";
	}
	public boolean isLinked() {
		boolean oneFound = false;
		for(PlayerData player:InspiredNations.playerdata.values()) {
			if(player.getAccounts() == this) {
				if(oneFound) {
					return true;
				}
				else {
					oneFound = true;
				}
			}
		}
		for(InspiredGov gov:InspiredNations.regiondata) {
			if(gov.getAccounts() == this) {
				if(oneFound) {
					return true;
				}
				else {
					oneFound = true;
				}
			}
		}
		return false;
	}
	public ArrayList<InspiredGov> getLinkedGovs() {
		ArrayList<InspiredGov> output = new ArrayList<InspiredGov>();
		for(InspiredGov gov:InspiredNations.regiondata) {
			if(gov.getAccounts() == this) {
				output.add(gov);
			}
		}
		return output;
	}
	@Override
	public void sendNotification(Alert msg) {
		for(PlayerData player:InspiredNations.playerdata.values()) {
			if(player.getAccounts().equals(this)) {
				player.sendNotification(msg);
			}
		}
	}
	
	
	public IndexedMap<Class<? extends InspiredGov>, BigDecimal> getTaxes(Currency curren) {
		IndexedMap<Class<? extends InspiredGov>, BigDecimal> output = new IndexedMap<Class<? extends InspiredGov>, BigDecimal>();
		for(InspiredGov gov:InspiredNations.regiondata) {
			if(gov.getAccounts() == (this)) {
				if(output.containsKey(gov.getClass())) {
					output.put(gov.getClass(), output.get(gov.getClass()).add(gov.currentTaxCycleValue(curren)));
				}
				else {
					output.put(gov.getClass(), gov.currentTaxCycleValue(curren));
				}
			}
		}
		return output;
	}
}
