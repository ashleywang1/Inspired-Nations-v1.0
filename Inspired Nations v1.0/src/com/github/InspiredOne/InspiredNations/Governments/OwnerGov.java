package com.github.InspiredOne.InspiredNations.Governments;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.ToolBox.IndexedSet;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public abstract class OwnerGov extends InspiredGov {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2000613785185838007L;
	protected IndexedSet<PlayerID> owners = new IndexedSet<PlayerID>();
	private IndexedSet<PlayerID> ownerRequest = new IndexedSet<PlayerID>();
	private IndexedSet<PlayerID> ownerOffers = new IndexedSet<PlayerID>();
	
	public OwnerGov() {
		super();
	}
	
	protected IndexedSet<PlayerID> getOwners() {
		return this.owners;
	}
	
	public void addOwner(PlayerID player) {
		if(!this.canAddWithoutConsequence(player.getPDI())) {
			for(InspiredGov gov:player.getPDI().getCitizenship(this.getClass())) {
				if(!gov.equals(this)) {
					((OwnerGov) gov).removeOwner(player);
					if(gov instanceof OwnerSubjectGov) {
						((OwnerSubjectGov) gov).removeSubject(player);
					}
				}
			}
		}
		this.owners.add(player);
		if(this instanceof OwnerSubjectGov) {
			((OwnerSubjectGov) this).addSubject(player);
		}
	}
	
	public void removeOwner(PlayerID player) {
		this.owners.remove(player);
	}
	
	public boolean isOwner(PlayerID player) {
		return this.owners.contains(player);
	}
	
	@Override
	protected IndexedSet<PlayerID> getSubjects() {
		return this.owners;
	}
	
	@Override
	public void updateTaxRate() {
		this.getSuperGovObj().getSubTaxRate(this.getClass());
	}
	/**
	 * Indicates if the player can become a citizen or owner of this government
	 * without having to give up ownership and citizenship of any govs.
	 * @param PDI
	 * @return 	<code>true</code> if no consequence
	 */
	public boolean canAddWithoutConsequence(PlayerData PDI) {
		OwnerGov gov = this;
		if(!PDI.getCitizenship(gov.getCommonGov()).isEmpty()) {
			if(gov.getCommonGovObj() != PDI.getCitizenship(gov.getCommonGov()).get(0)) {
				return false;
			}
			else {
				return true;
			}
		}
		return true;
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
	/**
	 * Gets all the requests made by other players to join this government's owners.
	 * @return
	 */
	public IndexedSet<PlayerID> getOwnerRequests() {
		return ownerRequest;
	}
	/**
	 * Sets the list of all the requests by other players to join this government's owners;
	 * @param ownerRequest
	 */
	public void setOwnerRequest(IndexedSet<PlayerID> ownerRequest) {
		this.ownerRequest = ownerRequest;
	}
	/**
	 * Get all the offers made by this government for a player to join the owners.
	 * @return
	 */
	public IndexedSet<PlayerID> getOwnerOffers() {
		return ownerOffers;
	}
	/**
	 * Set the list of offers made by this government for a player to join the owners.
	 * @param ownerOffers
	 */
	public void setOwnerOffers(IndexedSet<PlayerID> ownerOffers) {
		this.ownerOffers = ownerOffers;
	}

}
