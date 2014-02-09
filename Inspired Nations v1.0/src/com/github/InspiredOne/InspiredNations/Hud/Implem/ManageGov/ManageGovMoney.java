package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Money.ManageAccounts;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Money.PayNav;

public class ManageGovMoney extends OptionMenu {

	InspiredGov gov;
	Menu previous;
	public ManageGovMoney(PlayerData PDI, Menu previous, InspiredGov gov) {
		super(PDI);
		this.gov = gov;
		this.previous = previous;
	}

	@Override
	public String getPreOptionText() {
		String output = "";
		for(Class<? extends InspiredGov> govtype:gov.getTaxrates().keySet()) {
			InspiredGov govtemp = GovFactory.getGovInstance(govtype);
			output = output.concat(govtemp.getTypeName() + " Tax: " + gov.getTaxrates().get(govtype) + "\n");
		}
		output = output.concat(gov.getAccounts().getTotalMoney(PDI.getCurrency()).toString() + " " + PDI.getCurrency());
		return output;
		
	}

	@Override
	public Menu getPreviousMenu() {
		return previous;
	}

	@Override
	public String getHeader() {
		return "Manage Gov Money";
	}

	@Override
	public boolean getPassBy() {
		return false;
	}

	@Override
	public Menu getPassTo() {
		return null;
	}

	@Override
	public void init() {
		this.options.add(new PromptOption(this, "Pay", new PayNav(PDI, gov.getAccounts(), this)));
		this.options.add(new PromptOption(this, "Manage Accounts", new ManageAccounts(PDI, this, gov.getAccounts())));
		if(this.gov.getAccounts().isLinked()) {
			this.options.add(new SplitAccountOption(this, "Split " + gov.getTypeName() + " Account", "Separates this account from yours", gov));
		}
	}

}
