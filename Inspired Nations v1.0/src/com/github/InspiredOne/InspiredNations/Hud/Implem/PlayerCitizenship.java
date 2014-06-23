package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public class PlayerCitizenship extends OptionMenu {

	public PlayerCitizenship(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public String getPreOptionText() {
		String output = TextColor.SUBHEADER(PDI) + "Citizenship\n";
		for(OwnerGov gov:PDI.getCitizenship()) {
			if(gov != InspiredNations.global) {
				output = output.concat(gov.getDisplayName(PDI) + "\n");
			}
			
		}
		return output;
	}

	@Override
	public String getHeader() {
		return "Where You Live";
	}

	@Override
	public Menu getPreviousMenu() {
		return new MainHud(PDI);
	}

	@Override
	public boolean getPassBy() {
		return false;
	}

	@Override
	public Menu getPassTo() {
		return this;
	}

	@Override
	public void addOptions() {
		this.options.add(new PromptOption(this, "Ownership Requests and Offers", new OwnerOffers(PDI)));
		this.options.add(new PromptOption(this, "Citizenship Requests and Offers", new SubjectOffers(PDI)));
		
	}

	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		
	}

}
