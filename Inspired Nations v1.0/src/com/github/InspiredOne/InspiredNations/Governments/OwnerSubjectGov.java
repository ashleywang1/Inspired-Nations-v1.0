package com.github.InspiredOne.InspiredNations.Governments;

import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMilitaryLevelExecption;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;
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
	}

	@Override
	public BigDecimal getAdditionalCost() {

		return getAdditionalCost(this.getMilitaryLevel());
	}
	
	public BigDecimal getAdditionalCost(int militarylevel) {
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

	public void setMilitaryLevel(int militarylevel) throws NegativeMilitaryLevelExecption, BalanceOutOfBoundsException {
		if(militarylevel < 0) {
			throw new NegativeMilitaryLevelExecption();
		}
		else {
			BigDecimal refund = this.taxValue(region.getRegion(),InspiredNations.taxTimer.getFractionLeft(), this.protectionlevel, Currency.DEFAULT);
			BigDecimal newcost = this.taxValue(region.getRegion(),InspiredNations.taxTimer.getFractionLeft(), this.protectionlevel, this.getAdditionalCost(militarylevel), Currency.DEFAULT);
			BigDecimal cost = refund.subtract(newcost);
			if(cost.compareTo(BigDecimal.ZERO) < 0) {
				try {
					this.paySuper(cost.negate(), Currency.DEFAULT);
				} catch (NegativeMoneyTransferException e) {
				}
			}
			else {
				try {
					this.pullFromSuper(cost, Currency.DEFAULT);
				} catch (NegativeMoneyTransferException e) {
				} catch (BalanceOutOfBoundsException e) {
					try {
						this.pullFromSuper(this.getSuperGovObj().getAccounts().getTotalMoney(Currency.DEFAULT), Currency.DEFAULT);
					} catch (NegativeMoneyTransferException e1) {
					}
				}
			}
			this.militarylevel = militarylevel;
		}
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
