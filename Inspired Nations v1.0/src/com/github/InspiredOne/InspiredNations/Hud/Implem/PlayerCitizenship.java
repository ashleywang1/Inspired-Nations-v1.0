package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public class PlayerCitizenship extends TabSelectOptionMenu<OwnerGov> {

	public PlayerCitizenship(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public Menu getPreviousPrompt() {
		// TODO Auto-generated method stub
		return new MainHud(PDI);
	}

	@Override
	public String postTabListPreOptionsText() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void addTabOptions() {
		this.taboptions.addAll(PDI.getCitizenship());
		this.taboptions.remove(InspiredNations.global);
	}

	@Override
	public void addOptions() {
		this.options.add(new PromptOption(this, "Ownership Requests and Offers", new OwnerOffers(PDI)));
		this.options.add(new PromptOption(this, "Citizenship Requests and Offers", new SubjectOffers(PDI)));
		if(this.getTabOptions().size() > 0) {
			this.options.add(new LeaveGovOption(this, "Leave " + this.getData().getName(), this.getData()));
		}
	}

	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return "Manage Citizenship";
	}


}
