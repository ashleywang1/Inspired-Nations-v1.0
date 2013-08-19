package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.InputMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class PickName extends InputMenu {

	GovFactory Govf;
	public PickName(PlayerData PDI, GovFactory Govf) {
		super(PDI);
		this.Govf = Govf;
	}

	@Override
	public String validate(String input) {
		
		boolean allowed = true;
		
		for(InspiredGov gov: plugin.regiondata.get(Govf.getGov().getClass())) {
			if(gov.getSuperGov().equals(Govf.getGov().getSuperGovObj()) && gov.getName().equalsIgnoreCase(input)) {
				allowed = false;
			}
		}
		if(allowed) {
			return MenuError.NO_ERROR();
		}
		else {
			return MenuError.NAME_ALREADY_TAKEN(Govf.getGov().getClass());
		}
	}

	@Override
	public void useInput(String input) {
	}

	@Override
	public List<String> getTabOptions() {
		List<String> output = new ArrayList<String>();
		return output;
	}

	@Override
	public String getHeader() {
		return "Type Name";
	}

	@Override
	public String getFiller() {
		return "Type the name that you would like to use for this " + Govf.getGov().getTypeName() + ".";
	}

	@Override
	public Menu nextMenu() {
		return new PickMoneyName(PDI, Govf);
	}

	@Override
	public Menu PreviousMenu() {
		return new PickSuperGov(PDI, Govf);
	}

	@Override
	public boolean passBy() {
		return false;
	}
}
