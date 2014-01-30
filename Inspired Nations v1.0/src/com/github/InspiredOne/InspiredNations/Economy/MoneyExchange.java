package com.github.InspiredOne.InspiredNations.Economy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.InspiredNations;

public class MoneyExchange implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3674609233229292913L;
	private MathContext mcup = new MathContext(5, RoundingMode.UP);
	private MathContext mcdown = new MathContext(5, RoundingMode.HALF_EVEN);

	private HashMap<Currency, BigDecimal> Exchange = new HashMap<Currency, BigDecimal>();
	
	public void registerCurrency(Currency currency, BigDecimal diamondValue) {
		
		BigDecimal amount = new BigDecimal(InspiredNations.plugin.getConfig().getString("exchange_multiplyer"));
		Debug.print(currency);
		Debug.print(amount.multiply(diamondValue));
		Exchange.put(currency, amount.multiply(diamondValue));
	}
	
	public final BigDecimal getValue(BigDecimal mon, Currency monType, Currency valueType) {
		BigDecimal output;
		//TODO put these lines back into the else statement.
		BigDecimal valueAmount = Exchange.get(valueType);
		BigDecimal monAmount = Exchange.get(monType);
		Debug.print(valueType + " in exchange: " + valueAmount);
		Debug.print(monType + " in exchange: " + monAmount);
			
		//TODO end of the lines I need to put back in the else statement.
		
		//Remove if(monType.equals(valueType) when you figure it out
		
		if(monType.equals(valueType)) {
			output = mon;
		}
		else {

			BigDecimal monSum = monAmount.add(mon);
			BigDecimal division = valueAmount.divide(monSum, mcup);
			
			output = mon.multiply(division);
			Debug.print("Numarrator: " + valueAmount);
			Debug.print("Denominator: " + monSum);

		}
		Debug.print("Output: " + output);
		return output;
	}
	
	public final BigDecimal exchange(BigDecimal mon, Currency monType, Currency valueType) {
		BigDecimal output = this.getValue(mon, monType, valueType);
		Debug.print("Exchange " + monType + " before transaction: " + Exchange.get(monType));
		Debug.print("Exchange " + valueType + " before transaction: " + Exchange.get(valueType));
		Exchange.put(monType, Exchange.get(monType).add(mon));
		Exchange.put(valueType, Exchange.get(valueType).subtract(output));
		
		Debug.print("Exchange " + monType + " after transaction: " + Exchange.get(monType));
		Debug.print("Exchange " + valueType + " after transaction: " + Exchange.get(valueType));
		
		return output;
	}
	
	@SuppressWarnings("unchecked")
	public final HashMap<Currency, BigDecimal> getExchangeMap() {
		return (HashMap<Currency, BigDecimal>) this.Exchange.clone();
	}
	
}
