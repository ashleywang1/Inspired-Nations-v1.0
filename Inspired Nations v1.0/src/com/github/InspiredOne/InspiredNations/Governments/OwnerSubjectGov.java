package com.github.InspiredOne.InspiredNations.Governments;

import java.math.BigDecimal;
import java.util.HashSet;

public abstract class OwnerSubjectGov extends OwnerGov {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3448350609903588185L;
	private int militarylevel = 0;

	public OwnerSubjectGov() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public BigDecimal getAdditionalCost() {
		//TODO determine the function for the cost of war here.
		return (new BigDecimal(100)).multiply((new BigDecimal(militarylevel)).pow(2));
	}
	
	private HashSet<String> subjects = new HashSet<String>();
	
	public HashSet<String> getSubjects() {
		subjects.addAll(this.getOwners());
		return subjects;
	}

	public void setSubjects(HashSet<String> subjects) {
		this.subjects = subjects;
	}

	@Override
	public int getMilitaryLevel() {
		return militarylevel;
	}

	public void setMilitaryLevel(int militarylevel) {
		this.militarylevel = militarylevel;
	}

}
