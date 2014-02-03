package com.github.InspiredOne.InspiredNations.Governments;

import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.ToolBox.IndexedSet;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public abstract class OwnerSubjectGov extends OwnerGov {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3448350609903588185L;
	private int militarylevel = 0;
	private IndexedSet<PlayerID> subjects = new IndexedSet<PlayerID>();
	private IndexedSet<PlayerID> subjectRequests = new IndexedSet<PlayerID>();
	private IndexedSet<PlayerID> subjectOffers = new IndexedSet<PlayerID>();

	public OwnerSubjectGov() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public BigDecimal getAdditionalCost() {
		//TODO determine the function for the cost of war here.
		return (new BigDecimal(100)).multiply((new BigDecimal(militarylevel)).pow(2));
	}
	

	
	public IndexedSet<PlayerID> getSubjects() {
		subjects.addAll(this.getOwners());
		return subjects;
	}

	public void setSubjects(IndexedSet<PlayerID> subjects) {
		this.subjects = subjects;
	}

	@Override
	public int getMilitaryLevel() {
		return militarylevel;
	}

	public void setMilitaryLevel(int militarylevel) {
		this.militarylevel = militarylevel;
	}

	public IndexedSet<PlayerID> getSubjectRequests() {
		return subjectRequests;
	}

	public void setSubjectRequests(IndexedSet<PlayerID> subjectRequests) {
		this.subjectRequests = subjectRequests;
	}

	public IndexedSet<PlayerID> getSubjectOffers() {
		return subjectOffers;
	}

	public void setSubjectOffers(IndexedSet<PlayerID> subjectOffers) {
		this.subjectOffers = subjectOffers;
	}
	/**
	 * Gets the name of the subjects to be used in the menus
	 * @return
	 */
	public abstract String getSubjectPositionName();

}
