package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Money.ManageAccounts;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Money.PayNav;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;

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
		output = output.concat(Tools.cut(gov.getAccounts().getTotalMoney(PDI.getCurrency(), InspiredNations.Exchange.mcdown)).toString() + " " + PDI.getCurrency());
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
	public void addOptions() {
		this.options.add(new PromptOption(this, "Pay", new PayNav(PDI, this, gov.getAccounts())));
		this.options.add(new PromptOption(this, "Manage Accounts", new ManageAccounts(PDI, this, gov.getAccounts())));
		if(this.gov.getAccounts() == PDI.getAccounts()) {
			this.options.add(new SplitAccountOption(this, "Split " + gov.getTypeName() + " Account", "Separates this account from yours", gov));
		}
		else {
			this.options.add(new JoinAccountOption(this, "Join " + gov.getTypeName() + " Account", "Joins this account to yours", gov));
		}
		if(gov.getCommonEcon().equals(gov.getClass())) {
			this.options.add(new RenameMoneyOption(this, "Rename Money <name>", gov));
		}
	}

	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		
	}

}
