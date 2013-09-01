package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Hud.InputMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class PickMoneyName extends InputMenu{

	GovFactory Govf;
	public PickMoneyName(PlayerData PDI, GovFactory Govf) {
		super(PDI);
		this.Govf = Govf;
	}

	@Override
	public Menu nextMenu() {
		return new PickEconomy(PDI, Govf);
	}

	@Override
	public String validate(String input) {
		for(Currency currency:plugin.Exchange.keySet()) {
			if(currency.getName().equalsIgnoreCase(input)) {
				return MenuError.MONEY_NAME_ALREADY_TAKEN();
			}
		}
		return MenuError.NO_ERROR();
	}

	@Override
	public void useInput(String input) {
		Govf = Govf.withMoneyname(input);
	}

	@Override
	public List<String> getTabOptions() {
		List<String> output = new ArrayList<String>();
		return output;
	}

	@Override
	public Menu PreviousMenu() {
		return new PickName(PDI, Govf);
	}

	@Override
	public String getHeader() {
		return "Pick Money Name.";
	}

	@Override
	public String getInstructions() {
		return "Type the name of your currency.";
	}

	@Override
	public boolean getPassBy() {
		if(Govf.getGov().getCommonEcon().equals(Govf.getGov().getClass())) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public void init() {
	}

}
