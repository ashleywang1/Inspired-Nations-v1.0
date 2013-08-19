package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.conversations.Prompt;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Hud.InputMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class PickEconomy extends InputMenu{

	public GovFactory Govf;
	
	public PickEconomy(PlayerData PDI, GovFactory Govf) {
		super(PDI);
		this.Govf = Govf;
	}

	@Override
	public Menu nextMenu() {
		return new MainHud(PDI);
	}

	@Override
	public String validate(String input) {
		try {
			BigDecimal answer = new BigDecimal(input);
			// TODO check to make sure that the price is not out of reasonable range.
			return MenuError.NO_ERROR();
		}
		
		catch (Exception ex) {
			return MenuError.INVALID_NUMBER_INPUT();
		}
	}

	@Override
	public void useInput(String input) {
		// TODO Make this so it sets economy multiplier. 

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
		return "Set Up Economy";
	}

	@Override
	public String getFiller() {
		return "Type the price of the diamond in this";
	}

	@Override
	public boolean passBy() {
		if(Govf.getGov().getCommonEcon().equals(Govf.getGov().getClass())) {
			return false;
		}
		else return true;
	}

}
