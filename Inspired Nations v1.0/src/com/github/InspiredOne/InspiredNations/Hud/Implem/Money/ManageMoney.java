package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Account;
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
		for(Account account:PDI.getAccounts()) {
			total = total.add(account.getTotalMoney(PDI.getCurrency()));
		}
		return total.toString() + " " + PDI.getCurrency().getName();
	}

	@Override
	public void init() {
		this.options.add(new PromptOption(this, "Pay", new PayNav(PDI, PDI.getAccounts().keySet())));
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
	public Menu PreviousMenu() {
		return new MainHud(PDI);
	}

}
