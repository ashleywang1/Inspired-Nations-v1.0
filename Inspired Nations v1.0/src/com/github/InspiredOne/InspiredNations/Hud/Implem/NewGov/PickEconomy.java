package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
		Govf.registerGov();
		return new MainHud(PDI);
	}

	@Override
	public String validate(String input) {
		try {
			BigDecimal answer = new BigDecimal(input);
			if(answer.compareTo(new BigDecimal(10000)) > 0) {
				return MenuError.MONEY_MULTIPLYER_TOO_LARGE();
			}
			else if(answer.compareTo(new BigDecimal(50)) < 0) {
				return MenuError.MONEY_MULTIPLYER_TOO_SMALL();
			}
			return MenuError.NO_ERROR();
		}
		
		catch (Exception ex) {
			return MenuError.INVALID_NUMBER_INPUT();
		}
	}

	@Override
	public void useInput(String input) {
		Govf = Govf.withDiamondValue(new BigDecimal(input));
	}

	@Override
	public List<String> getTabOptions() {
		List<String> output = new ArrayList<String>();
		return output;
	}

	@Override
	public Menu PreviousMenu() {
		return new PickMoneyName(PDI, Govf);
	}

	@Override
	public String getHeader() {
		return "Set Up Economy";
	}

	@Override
	public String getInstructions() {
		return "Type the price of the diamond in " + Govf.getGov().getCurrency().getName() + ".";
	}

	@Override
	public boolean getPassBy() {
		if(Govf.getGov().getCommonEcon().equals(Govf.getGov().getClass())) {
			return false;
		}
		else return true;
	}

	@Override
	public void Init() {
		
	}
}
