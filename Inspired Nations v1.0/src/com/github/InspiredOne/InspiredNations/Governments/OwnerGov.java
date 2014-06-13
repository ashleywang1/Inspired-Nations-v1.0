package com.github.InspiredOne.InspiredNations.Governments;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Economy.NPC;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;
import com.github.InspiredOne.InspiredNations.ToolBox.IndexedSet;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuAlert;
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
		for(OwnerGov gov:player.getPDI().getCitizenship()) {
			for(OwnerGov govlost:gov.getGovsLost(this, player)) {
				govlost.removeOwner(player);
				player.getPDI().sendNotification(MenuAlert.LOST_OWNERSHIP(govlost));
				if(govlost instanceof OwnerSubjectGov) {
					((OwnerSubjectGov) govlost).removeSubject(player);
					player.getPDI().sendNotification(MenuAlert.LOST_CITIZENSHIP((OwnerSubjectGov) govlost));
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
		if(InspiredNations.playerdata.get(player).getAccounts() == (this.getAccounts())) {
			this.getAccounts().setName(this.getName());
			this.splitAccount(player.getPDI(), new ArrayList<PlayerID>(), this.getAccounts());
		}
		if(this.isSubjectLess()) {
			this.unregister();
		}
	}
	

	public OwnerGov getCommonGovObj() {
		boolean found = false;
		OwnerGov test = this;
		while(!found) {
			if(test.getClass().equals(this.getCommonGov())) {
				return test;
			}
			else test = (OwnerGov) test.getSuperGovObj();
		}
		return null;
	}
	/**
	 * Gets a list of all the govs that would be lost if this player were to
	 * switch to this govTo
	 * @param govTo
	 * @return
	 */
	public ArrayList<OwnerGov> getGovsLost(OwnerGov govTo, PlayerID PID) {
		ArrayList<OwnerGov> output = new ArrayList<OwnerGov>();
			if(this.getCommonGovObj() != govTo.getSuperGovObj(this.getCommonGov())) {
				Debug.print("These are not equal");
				Debug.print(this.getCommonGovObj() + " is not equal to " + govTo.getSuperGovObj(this.getCommonGov()));
				
				output.add(this);
			}
			for(Class<? extends InspiredGov> govtype: this.getSubGovs()) {
				for(InspiredGov subgovtest:this.getAllSubGovs(govtype)) {
					if(subgovtest instanceof OwnerGov) {
						if(subgovtest.isSubject(PID)) {
							output.addAll(((OwnerGov) subgovtest).getGovsLost(govTo, PID));
						}
					}
				}
			}
		return output;
	}
	
	public boolean isOwner(PlayerID player) {
		return this.owners.contains(player);
	}
	
	@Override
	public IndexedSet<PlayerID> getSubjects() {
		return this.owners;
	}
	
	@Override
	public void paySuper(BigDecimal amount, Currency curren) throws BalanceOutOfBoundsException, NegativeMoneyTransferException {
		if(this.getSuperGovObj() instanceof GlobalGov) {
			int npccount = 0;
			for(PlayerID player:this.getSubjects()) {
				npccount += player.getPDI().npcs.size();
			}
			BigDecimal payment = amount.divide(new BigDecimal(npccount), InspiredNations.Exchange.mcdown);
			for(PlayerID player:this.getSubjects()) {
				for(NPC npc:player.getPDI().npcs) {
					this.transferMoney(payment, curren, npc);
				}
			}
		}
		else {
			this.transferMoney(amount, curren, this.getSuperGovObj());
		}
		
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
		for(OwnerGov gov:PDI.getCitizenship()) {
			Debug.print("///////" + gov.getName() + "/////////");
			if(!gov.getGovsLost(this, PDI.getPlayerID()).isEmpty()) {
				return false;
			}
		}
		return true;
		
		/*		OwnerGov gov = this;
		if(!PDI.getCitizenship(gov.getCommonGov()).isEmpty()) {
			if(gov.getCommonGovObj() != PDI.getCitizenship(gov.getCommonGov()).get(0)) {
				return false;
			}
			else {
				return true;
			}
		}
		return true;*/
	}
	

	/**Returns the gov of citizenship that must be common for both the player and this gov
	 * Returns self if you're only allowed to have one
	 * Regions that do not have owners or subjects are excluded
	 * The (new gov)'s (gov) must be the same as the (current gov)'s (gov)
	 * @return	<code>Class<? extends InspiredGov></code>
	 * 
	 * */	
	public abstract Class<? extends OwnerGov> getCommonGov();
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
