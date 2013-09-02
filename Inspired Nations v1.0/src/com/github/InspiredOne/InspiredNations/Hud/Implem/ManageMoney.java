package com.github.InspiredOne.InspiredNations.Hud.Implem;

import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;

public class ManageMoney extends OptionMenu {

	public ManageMoney(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public String getPreOptionText() {
		BigDecimal total = BigDecimal.ZERO;
		for(Account account:PDI.getAccounts()) {
			total = total.add(account.getTotalAdjustedMoney(PDI.getCurrency()));
		}
		return total.toString() + " " + PDI.getCurrency().getName();
	}

	@Override
	public void init() {
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
