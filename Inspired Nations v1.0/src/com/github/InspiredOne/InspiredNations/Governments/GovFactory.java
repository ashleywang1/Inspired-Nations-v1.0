package com.github.InspiredOne.InspiredNations.Governments;

import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.InspiredNations;

public class GovFactory {

	InspiredGov gov;
	public GovFactory(Class<? extends InspiredGov> gov) {
		this.gov = GovFactory.getGovInstance(gov);
	}
	
	public GovFactory withName(String name) {
		this.gov.setName(name);
		return this;
	}
	
	public GovFactory withSuperGov(InspiredGov gov) {
		this.gov.setSuperGovObj(gov);
		if(!this.gov.getCommonEcon().equals(this.gov.getClass())) {
			this.gov.setCurrency(gov.getCurrency());
		}
		return this;
	}
	
	public GovFactory withMoneyname(String name) {
		this.gov.getCurrency().setName(name);
		return this;
	}
	
	public GovFactory withMoneyMultiplyer(BigDecimal multiplyer) {
		this.gov.getCurrency().setMoneymultiplyer(multiplyer);
		return this;
	}
	
	public InspiredGov getGov() {
		return this.gov;
	}

	public void registerGov() {
		InspiredNations.plugin.regiondata.put(gov.getClass(), gov);
		InspiredNations.plugin.Exchange.put(gov.getCurrency(), BigDecimal.ONE);
		//TODO change BigDecimal.ONE to whatever you're going to use for default values for exchanger
	}
	
	public static InspiredGov getGovInstance(Class<? extends InspiredGov> gov) {
		try {
			return gov.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
