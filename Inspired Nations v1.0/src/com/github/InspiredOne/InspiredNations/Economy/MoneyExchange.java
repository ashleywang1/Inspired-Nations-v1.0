package com.github.InspiredOne.InspiredNations.Economy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;

import com.github.InspiredOne.InspiredNations.InspiredNations;

public class MoneyExchange implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3674609233229292913L;
	private MathContext mcup = new MathContext(50, RoundingMode.UP);
	private MathContext mcdown = new MathContext(50, RoundingMode.DOWN);
//	private MathContext mcoutput = new MathContext(25, RoundingMode.DOWN);

	private HashMap<Currency, BigDecimal> Exchange = new HashMap<Currency, BigDecimal>();
	
	public void registerCurrency(Currency currency, BigDecimal diamondValue) {
		
		BigDecimal amount = new BigDecimal(InspiredNations.plugin.getConfig().getString("exchange_multiplyer"));
		if(!this.Exchange.containsKey(currency)) {
			Exchange.put(currency, amount.multiply(diamondValue));
		}
	}
	
	/**
	 * Finds the value of money required in valueType that would yeild mon in montype if exchanged.
	 * The reason getExchangeValue does not work in a transfer context is that getExchangeValue
	 * assumes that monType depreciates when exchanged. Because in a transfer there is no exchange
	 * happening, the monType does not depreciate and therefore more valueType is required than
	 * what is found using getExchangeValue.
	 * @param mon		the amount of mon you're trying to get
	 * @param monType	the type of mon
	 * @param valueType	the type of currency you're using to get mon
	 * @return
	 */
	public final BigDecimal getTransferValue(BigDecimal mon, Currency monType, Currency valueType) {
		BigDecimal output;
		BigDecimal valueAmount = Exchange.get(valueType);
		BigDecimal monAmount = Exchange.get(monType);
		if(monType.equals(valueType)) {
			return mon;
		}
		BigDecimal difference = monAmount.subtract(mon);
		output = mon.multiply(valueAmount).divide(difference, mcup);
		
		return output;
	}
	/**
	 * Gets the total amount of valueType you would recieve if you exchanged mon amount of monType
	 * @param mon
	 * @param monType
	 * @param valueType
	 * @return
	 */
	public final BigDecimal getExchangeValue(BigDecimal mon, Currency monType, Currency valueType) {
		BigDecimal output;
		//TODO put these lines back into the else statement.
		BigDecimal valueAmount = Exchange.get(valueType);
		BigDecimal monAmount = Exchange.get(monType);

			
		//TODO end of the lines I need to put back in the else statement.
		
		//Remove if(monType.equals(valueType) when you figure it out
		
		if(monType.equals(valueType)) {
			output = mon;
		}
		else {

			BigDecimal monSum = monAmount.add(mon);
			BigDecimal division = valueAmount.divide(monSum, mcdown);
			
			output = mon.multiply(division);

		}
		return output;
	}
	
	public final BigDecimal exchange(BigDecimal mon, Currency monType, Currency valueType) {
		BigDecimal output = this.getExchangeValue(mon, monType, valueType);
		Exchange.put(monType, Exchange.get(monType).add(mon));
		Exchange.put(valueType, Exchange.get(valueType).subtract(output));
		
		return output;
	}
	
	@SuppressWarnings("unchecked")
	public final HashMap<Currency, BigDecimal> getExchangeMap() {
		return (HashMap<Currency, BigDecimal>) this.Exchange.clone();
	}
	
}
