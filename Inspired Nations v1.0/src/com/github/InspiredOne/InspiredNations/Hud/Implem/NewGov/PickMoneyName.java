package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Hud.InputMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void useInput(String input) {
		// TODO Auto-generated method stub

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
	public String getFiller() {
		return "Type the name of your currency.";
	}

	@Override
	public boolean passBy() {
		if(Govf.getGov().getCommonEcon().equals(Govf.getGov().getClass())) {
			return false;
		}
		else return true;
	}

}
