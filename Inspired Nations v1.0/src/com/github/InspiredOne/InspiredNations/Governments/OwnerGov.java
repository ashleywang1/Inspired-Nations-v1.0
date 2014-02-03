package com.github.InspiredOne.InspiredNations.Governments;

import com.github.InspiredOne.InspiredNations.ToolBox.IndexedSet;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public abstract class OwnerGov extends InspiredGov {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2000613785185838007L;
	private IndexedSet<PlayerID> owners = new IndexedSet<PlayerID>();
	private IndexedSet<PlayerID> ownerRequest = new IndexedSet<PlayerID>();
	private IndexedSet<PlayerID> ownerOffers = new IndexedSet<PlayerID>();
	
	public OwnerGov() {
		super();
	}

	public IndexedSet<PlayerID> getOwners() {
		return owners;
	}

	public void setOwners(IndexedSet<PlayerID> owners) {
		this.owners = owners;
	}
	
	public IndexedSet<PlayerID> getSubjects() {
		return this.owners;
	}
	
	public InspiredGov getCommonGovObj() {
		boolean found = false;
		InspiredGov test = this;
		while(!found) {
			if(test.getClass().equals(this.getCommonGov())) {
				return test;
			}
			else test = test.getSuperGovObj();
		}
		return null;
	}
	/**Returns the gov of citizenship that must be common for both the player and this gov
	 * Returns self if you're only allowed to have one
	 * Regions that do not have owners or subjects are excluded
	 * The (new gov)'s (gov) must be the same as the (current gov)'s (gov)
	 * @return	<code>Class<? extends InspiredGov></code>
	 * 
	 * */	
	public abstract Class<? extends InspiredGov> getCommonGov();

	public IndexedSet<PlayerID> getOwnerRequest() {
		return ownerRequest;
	}

	public void setOwnerRequest(IndexedSet<PlayerID> ownerRequest) {
		this.ownerRequest = ownerRequest;
	}

	public IndexedSet<PlayerID> getOwnerOffers() {
		return ownerOffers;
	}

	public void setOwnerOffers(IndexedSet<PlayerID> ownerOffers) {
		this.ownerOffers = ownerOffers;
	}

}
