package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress.PickNavGeneral;

public class ShareAccountNav extends PickNavGeneral {

	Account account;
	public ShareAccountNav(PlayerData PDI, Menu previous, Account account) {
		super(PDI, previous);
		this.account = account;
	}

	@Override
	public String getGovOptionText() {
		return "Pick Government";
	}

	@Override
	public String getPlayerOptionText() {
		return "Pick Player";
	}

	@Override
	public Menu getGovMenu() {
		return new PickGovernmentToShare(PDI, previous, previous, account, InspiredNations.global);
	}

	@Override
	public Menu getPlayerMenu() {
		return null;
	}

	@Override
	public String getPreOptionText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

}
