package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Account;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress.PickPlayerGeneral;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public class PickPlayerToShare extends PickPlayerGeneral {

	Account account;
	
	public PickPlayerToShare(PlayerData PDI, Menu previous, Account account) {
		super(PDI, previous);
		this.account = account;
	}

	@Override
	public boolean check(PlayerID player) {
		if(PDI.getPlayerID().equals(player)) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public void insertOptions() {
		this.options.add(new ShareAccountOption(this, "Share Account With " + this.getData(), account, this.getData().getPDI().getAccounts()));
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public String getHeader() {
		return "Pick Player To Share Account";
	}

	@Override
	public TabSelectOptionMenu<?> GetSelf() {
		return new PickPlayerToShare(PDI, previous, account);
	}

}
