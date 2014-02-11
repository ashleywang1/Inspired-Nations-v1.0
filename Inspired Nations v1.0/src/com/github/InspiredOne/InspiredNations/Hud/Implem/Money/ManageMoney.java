package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;

public class ManageMoney extends OptionMenu {
	//private MathContext mcup = new MathContext(5, RoundingMode.UP);//this is temporary

	public ManageMoney(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public String getPreOptionText() {
		BigDecimal total = BigDecimal.ZERO;
		//TODO get rid of this line eventually
		total = Tools.cut(PDI.getAccounts().getTotalMoney(PDI.getCurrency()));
		
		return total + " " + PDI.getCurrency().getName(); 
	}

	@Override
	public void init() {
		this.options.add(new PromptOption(getSelf(), "Pay", new PayNav(PDI, getSelf(), PDI.getAccounts())));
		this.options.add(new PromptOption(getSelf(), "Manage Accounts", new ManageAccounts(PDI, getSelf(), PDI.getAccounts())));
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

	@Override
	public OptionMenu getSelf() {
		return new ManageMoney(PDI);
	}

}
