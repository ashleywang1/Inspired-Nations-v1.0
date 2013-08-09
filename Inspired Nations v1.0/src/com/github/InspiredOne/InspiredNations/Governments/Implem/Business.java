package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.math.BigDecimal;
import java.util.List;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.Subjects;

public abstract class Business extends Subjects {

	public Business(InspiredNations instance) {
		super(instance);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Class<? extends InspiredGov>> getSubGovs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends InspiredGov> getSuperGov() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void paySuper(BigDecimal amount) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public List<Class<? extends InspiredGov>> getSelfGovs() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Class<? extends InspiredGov> getCommonGov() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends InspiredGov> getCommonEcon() {
		// TODO Auto-generated method stub
		return null;
	}
}
