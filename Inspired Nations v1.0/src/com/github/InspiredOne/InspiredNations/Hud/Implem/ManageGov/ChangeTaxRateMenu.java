package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.NameAlreadyTakenException;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
import com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov.ChangeTaxRateMenu.TaxRateOption;;

public class ChangeTaxRateMenu extends TabSelectOptionMenu<TaxRateOption> {

	InspiredGov gov;
	Menu previous;
	
	public ChangeTaxRateMenu(PlayerData PDI, Menu previous, InspiredGov gov) {
		super(PDI);
		this.gov = gov;
		this.previous = previous;
	}

	@Override
	public Menu getPreviousPrompt() {
		return previous;
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public void addTabOptions() {
		for(Class<? extends InspiredGov> govtype:gov.getTaxrates()) {
			this.taboptions.add(new TaxRateOption(gov.getTaxrates().get(govtype), govtype));
		}
	}

	@Override
	public void addOptions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return "Change Tax Rate";
	}
	
	public class TaxRateOption implements Nameable {

		Class<? extends InspiredGov> gov;
		double rate;
		public TaxRateOption(double rate, Class<? extends InspiredGov> type) {
			this.rate = rate;
			this.gov = type;
		}
		
		@Override
		public String getName() {
			return "";
		}

		@Override
		public void setName(String name) throws NameAlreadyTakenException {
			
		}

		@Override
		public String getDisplayName(PlayerData viewer) {
			InspiredGov govtemp = GovFactory.getGovInstance(gov);
			return govtemp.getTypeName() + " " + rate;
		}
	}
}
