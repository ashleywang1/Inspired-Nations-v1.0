package com.github.InspiredOne.InspiredNations.Governments;

import java.util.HashSet;

public abstract class NoSubjects extends InspiredGov {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2000613785185838007L;

	public NoSubjects() {
		super();
		// TODO Auto-generated constructor stub
	}

	private HashSet<String> owners = new HashSet<String>();

	public HashSet<String> getOwners() {
		return owners;
	}

	public void setOwners(HashSet<String> owners) {
		this.owners = owners;
	}
	
	public HashSet<String> getSubjects() {
		return this.owners;
	}
	/**Returns the gov of citizenship that must be common for both the player and this gov
	 * Returns self if you're only allowed to have one
	 * Regions that do not have owners or subjects are excluded
	 * The (new gov)'s (gov) must be the same as the (current gov)'s (gov)
	 * @return	<code>Class<? extends InspiredGov></code>
	 * 
	 * */	
	public abstract Class<? extends InspiredGov> getCommonGov();

}
