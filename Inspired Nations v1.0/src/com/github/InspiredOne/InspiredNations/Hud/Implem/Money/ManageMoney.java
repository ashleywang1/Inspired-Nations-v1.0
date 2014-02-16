package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;
import com.github.InspiredOne.InspiredNations.ToolBox.IndexedMap;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

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
		String output = TextColor.VALUEDESCRI + "Total Holdings: " + TextColor.VALUE + total.toString() +
				TextColor.UNIT + " " + PDI.getCurrency() + "\n";
		IndexedMap<Class<? extends InspiredGov>, BigDecimal> taxmap = PDI.getAccounts().getTaxes(PDI.getCurrency());
		if(!taxmap.isEmpty()) {
			output = output.concat(TextColor.SUBHEADER + "Taxes\n");
		}
		for(Class<? extends InspiredGov> govtype:taxmap) {
			InspiredGov gov = GovFactory.getGovInstance(govtype);
			output = output.concat(TextColor.VALUEDESCRI + gov.getTypeName() + ": " + TextColor.VALUE +
					Tools.cut(taxmap.get(govtype))) + TextColor.UNIT + " " + PDI.getCurrency() +"\n";
		}
		
		
		return output; 
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
