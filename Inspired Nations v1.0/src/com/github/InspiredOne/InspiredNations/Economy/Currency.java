package com.github.InspiredOne.InspiredNations.Economy;

import java.io.Serializable;

import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;

public class Currency implements Serializable, Nameable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2855995345401677901L;
	private String name = "Coin";
	public static final Currency DEFAULT = new Currency("Coin");

	public Currency(String name) {

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
