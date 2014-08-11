package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.HelpMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public class PlayerCitizenship extends TabSelectOptionMenu<OwnerGov> {

	public PlayerCitizenship(PlayerData PDI) {
		super(PDI);
		help = new HelpMenu(PDI, this).addPage(
				"Welcome to the Citizenship page! \n"
				+ "This page lists all the governments that you belong to. You can only belong to one country"
				+ " and town at a time."
				);
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
		
		//If you are an owner, you may completely delete the government
		if(this.getTabOptions().size() > 0) {
			
			if (this.getData().isOwner(PDI.getPlayerID())) {
				this.options.add(new PromptOption(this, "Delete " + this.getData().getName(), new DeleteGovWarning(PDI, this.getData())));
				
			}
			if (this.getData().isSubject(PDI.getPlayerID())) {
				this.options.add(new LeaveGovOption(new PlayerCitizenship(PDI), "Leave " + this.getData().getName(), this.getData()));
			}
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


	public class LeaveGovOption extends Option {
		
		OwnerGov gov;
		//Menu citizenship;

		public LeaveGovOption(OptionMenu menu, String label, OwnerGov ownerGov) {
			super(menu, label);
			gov = ownerGov;
			//citizenship = menu;
			
		}

		@Override
		public Menu response(String input) {
			
			gov.removePlayer(PDI.getPlayerID());
			return menu;
		}
		
	}

}

