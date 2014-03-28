package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.Debug;
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
		output = output.concat(Tools.cut(gov.getAccounts().getTotalMoney(PDI.getCurrency())).toString() + " " + PDI.getCurrency());
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
		Debug.print("in init of manage gov money 1");
		Debug.print("is gov null? " + (gov == null));
		Debug.print("la de da");
		this.options.add(new PromptOption((OptionMenu) getNewSelf(), "Pay", new PayNav(PDI, getNewSelf(), gov.getAccounts())));
		this.options.add(new PromptOption((OptionMenu) getNewSelf(), "Manage Accounts", new ManageAccounts(PDI, this, gov.getAccounts())));
		Debug.print("in init of manage gov money 3");
		if(this.gov.getAccounts().isLinked()) {
			Debug.print("in init of manage gov money 4");
			this.options.add(new SplitAccountOption((OptionMenu) getNewSelf(), "Split " + gov.getTypeName() + " Account", "Separates this account from yours", gov));
			Debug.print("in init of manage gov money 5");
		}		
	}

	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		
	}

}
