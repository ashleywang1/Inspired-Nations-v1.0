package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.getMoneyOption;
import com.github.InspiredOne.InspiredNations.Regions.SelectionMode;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;

public class PickClaimType extends PassByOptionMenu {

	InspiredGov gov;
	Menu previous;
	
	public PickClaimType(PlayerData PDI, InspiredGov gov, Menu previous) {
		super(PDI);
		this.gov = gov;
		this.previous = previous;
	}

	@Override
	public String getPreOptionText() {
		
		return "";
	}

	@Override
	public Menu PreviousMenu() {
		return previous;
	}

	@Override
	public String getHeader() {
		return "Pick the Type of Selection";
	}
	
	@Override
	public void init() {
		Debug.print("Inside PickClaimType.init");
		SelectionMode temp;
		this.options.add(new getMoneyOption(this, "get money", PDI));
		for(Class<? extends SelectionMode> regiontype:gov.getRegion().getAllowedForms()) {
			temp = Tools.getInstance(regiontype);
			this.options.add(new PromptOption(this, temp.getTypeName() + ": " + temp.getDescription(), temp.getClaimMenu(PDI, previous) ));
		}
		Debug.print("Outside of loop");
	}

}
