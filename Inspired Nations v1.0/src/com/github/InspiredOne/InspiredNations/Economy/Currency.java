package com.github.InspiredOne.InspiredNations.Economy;

import java.io.Serializable;
import java.math.BigDecimal;

public class Currency implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2855995345401677901L;
	private String name = "Coin";
	private BigDecimal moneymultiplyer = BigDecimal.ONE;
	public static final Currency DEFAULT = new Currency(BigDecimal.ONE, "Coin");

	public Currency(BigDecimal moneymultiplyer, String name) {
		moneymultiplyer = BigDecimal.ONE;
	}

	public BigDecimal getMoneymultiplyer() {
		return moneymultiplyer;
	}

	public void setMoneymultiplyer(BigDecimal moneymultiplyer) {
		this.moneymultiplyer = moneymultiplyer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
