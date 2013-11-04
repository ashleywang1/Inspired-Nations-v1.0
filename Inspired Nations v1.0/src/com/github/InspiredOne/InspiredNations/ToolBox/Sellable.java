package com.github.InspiredOne.InspiredNations.ToolBox;

import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.Economy.Currency;

public interface Sellable {

	public void buy();
	public String getDescription();
	public Currency getCurrency();
	public BigDecimal getPrice();
	
}
