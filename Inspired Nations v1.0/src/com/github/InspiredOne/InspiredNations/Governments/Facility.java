package com.github.InspiredOne.InspiredNations.Governments;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Economy.NPC;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;
/**
 * Facilities cannot have subgovs
 * @author Jedidiah E. Phillips
 *
 */
public abstract class Facility extends InspiredGov implements Serializable, Nameable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6514705223412901675L;

	@Override
	public final List<Class<? extends OwnerGov>> getSubGovs() {
		List<Class<? extends OwnerGov>> output = new ArrayList<Class<? extends OwnerGov>>();
		return output;
	}
	
	@Override
	public void updateTaxRate() {
		this.taxedrate = this.getSuperGovObj().getSuperGovObj().getSubTaxRate(this.getSuperGov());
	}
	@Override
	public BigDecimal currentTaxCycleValue(Currency curren) {
		return this.taxValue(this.getRegion().getRegion(), 1, this.protectionlevel, this.getAdditionalCost(),
				this.getSuperGovObj().getSuperGovObj().getTaxrates().get(this.getSuperGov()), curren);
	}
	
	@Override
	public void paySuper(BigDecimal amount, Currency curren) throws BalanceOutOfBoundsException, NegativeMoneyTransferException {
		if(this.getSuperGovObj().getSuperGovObj() instanceof GlobalGov) {
			int npccount = 0;
			for(PlayerID player:this.getSuperGovObj().getSubjects()) {
				npccount += player.getPDI().npcs.size();
			}
			BigDecimal payment = amount.divide(new BigDecimal(npccount), InspiredNations.Exchange.mcdown);
			for(PlayerID player:this.getSuperGovObj().getSubjects()) {
				for(NPC npc:player.getPDI().npcs) {
					this.transferMoney(payment, curren, npc);
				}
			}
		}
		this.transferMoney(amount, curren, this.getSuperGovObj().getSuperGovObj());
	}

	@Override
	public void pullFromSuper(BigDecimal amount, Currency curren) throws BalanceOutOfBoundsException, NegativeMoneyTransferException {
		this.getSuperGovObj().getSuperGovObj().transferMoney(amount, curren, this);
		//this.transferMoney(amount, curren, this.getSuperGovObj().getSuperGovObj()); I'm not sure why this is like this rather than the line above.
	}
	
	@Override
	public String getOwnerPositionName() {
		return "Caretaker";
	}
	
	public int getMilitaryLevel() {
		return this.getSuperGovObj().getMilitaryLevel();
	}

	/**
	 * Gets all the options that this facility needs to be functional as a facility.
	 * @return
	 */
	public abstract void setFunctionOptions(PlayerData PDI, OptionMenu menu);
	/**
	 * Is there only allowed to be one of these? 
	 * @return
	 */
	public abstract boolean isUnique();
}
