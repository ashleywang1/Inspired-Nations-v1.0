package com.github.InspiredOne.InspiredNations.Governments;

import java.util.HashSet;

public abstract class Subjects extends NoSubjects {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3448350609903588185L;
	private int militarylevel = 0;

	public Subjects() {
		super();
		// TODO Auto-generated constructor stub
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
