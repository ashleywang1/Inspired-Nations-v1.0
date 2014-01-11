package com.github.InspiredOne.InspiredNations.Economy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

import com.github.InspiredOne.InspiredNations.InspiredNations;

public class MoneyExchange implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3674609233229292913L;
	

	private HashMap<Currency, BigDecimal> Exchange = new HashMap<Currency, BigDecimal>();
	
	public void registerCurrency(Currency currency, BigDecimal diamondValue) {
		
		System.out.println("plugin is null " + (InspiredNations.plugin == null));
		System.out.println("config is null " + (InspiredNations.plugin.getConfig() == null));
		System.out.println("Exchange multiplyer = " + InspiredNations.plugin.getConfig().getString("exchange_multiplyer"));
		
		
		BigDecimal amount = new BigDecimal(InspiredNations.plugin.getConfig().getString("exchange_multiplyer"));
		
		Exchange.put(currency, amount);
	}
	
	public final BigDecimal getValue(BigDecimal mon, Currency monType, Currency valueType) {
		BigDecimal output = mon.multiply(Exchange.get(valueType).divide(Exchange.get(monType).add(mon)));
		return output;
	}
	
	public final BigDecimal exchange(BigDecimal mon, Currency monType, Currency valueType) {
		BigDecimal output = this.getValue(mon, monType, valueType);
		Exchange.put(monType, Exchange.get(monType).add(mon));
		Exchange.put(valueType, Exchange.get(valueType).subtract(output));
		return this.getValue(mon, monType, valueType);
	}
	
	@SuppressWarnings("unchecked")
	public final HashMap<Currency, BigDecimal> getExchangeMap() {
		return (HashMap<Currency, BigDecimal>) this.Exchange.clone();
	}
	
}
