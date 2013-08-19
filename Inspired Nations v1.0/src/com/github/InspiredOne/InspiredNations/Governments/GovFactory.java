package com.github.InspiredOne.InspiredNations.Governments;

import java.math.BigDecimal;

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
		return this;
	}
	
	public GovFactory withMoneyname(String name) {
		this.gov.getAccount().getCurrency().setName(name);
		return this;
	}
	
	public GovFactory withMoneyMultiplyer(BigDecimal multiplyer) {
		this.gov.getAccount().getCurrency().setMoneymultiplyer(multiplyer);
		return this;
	}
	
	public InspiredGov getGov() {
		return this.gov;
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
