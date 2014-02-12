package com.github.InspiredOne.InspiredNations.Governments;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
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
	public void paySuper(BigDecimal amount, Currency curren) throws BalanceOutOfBoundsException, NegativeMoneyTransferException {
		this.getAccounts().transferMoney(amount, curren, this.getSuperGovObj().getSuperGovObj().getAccounts());
	}

	@Override
	public void pullFromSuper(BigDecimal amount, Currency curren) throws BalanceOutOfBoundsException, NegativeMoneyTransferException {
		this.getAccounts().transferMoney(amount, curren, this.getSuperGovObj().getSuperGovObj().getAccounts());
	}
	
	@Override
	public String getOwnerPositionName() {
		return "Caretaker";
	}
	/**
	 * Is there only allowed to be one of these? 
	 * @return
	 */
	public abstract boolean isUnique();
}
