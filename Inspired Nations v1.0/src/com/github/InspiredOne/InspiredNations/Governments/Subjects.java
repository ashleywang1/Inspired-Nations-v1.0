package com.github.InspiredOne.InspiredNations.Governments;

import java.util.HashSet;

import com.github.InspiredOne.InspiredNations.InspiredNations;

public abstract class Subjects extends NoSubjects {

	public Subjects(InspiredNations instance) {
		super(instance);
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

}
