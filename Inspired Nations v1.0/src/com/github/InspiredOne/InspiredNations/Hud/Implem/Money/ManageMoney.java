package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;

public class ManageMoney extends OptionMenu {

	public ManageMoney(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public String getPreOptionText() {
		BigDecimal total = BigDecimal.ZERO;
		//TODO get rid of this line eventually
		Debug.print("Inside ManageMoney.getPreOptionText");
		Debug.print("Is PDI null? " + PDI == null);
		Debug.print("Is Accounts null? " + PDI.getAccounts() == null);
		Debug.print("Is Currency null? " + PDI.getCurrency() == null);
		total = PDI.getAccounts().getTotalMoney(PDI.getCurrency());
		Debug.print("Inside ManageMoney.getPreOptionText 2");
		return total.toString() + " " + PDI.getCurrency().getName(); 
	}

	@Override
	public void init() {
		Debug.print("Inside ManageMoney.getPreOptionText 3");
		this.options.add(new PromptOption(this, "Pay", new PayNav(PDI, PDI.getAccounts(), this)));
		Debug.print("Inside ManageMoney.getPreOptionText 4");
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
	public String getHeader() {
		return "Manage Money";
	}

	@Override
	public Menu getPreviousMenu() {
		return new MainHud(PDI);
	}

}
