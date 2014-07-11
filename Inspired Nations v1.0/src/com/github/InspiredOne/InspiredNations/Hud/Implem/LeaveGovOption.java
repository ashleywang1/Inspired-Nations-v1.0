package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerSubjectGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Player.PlayerID;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

public class LeaveGovOption extends Option {

	OwnerGov gov;
	PlayerData PDI;
	
	public LeaveGovOption(OptionMenu menu, String label, OptionUnavail reason, OwnerGov gov) {
		super(menu, label, reason);
		this.gov = gov;
		PDI = menu.getPlayerData();
	}

	public LeaveGovOption(OptionMenu menu, String label, OwnerGov gov) {
		super(menu, label);
		this.gov = gov;
		PDI = menu.getPlayerData();
	}

	public LeaveGovOption(OptionMenu menu, String label, String description, OwnerGov gov) {
		super(menu, label, description);
		this.gov = gov;
		PDI = menu.getPlayerData();
	}
	
	///Is this needed or will a OwnerSubject gov still apply in the cases above?
	public LeaveGovOption(OptionMenu menu, String label, OptionUnavail reason, OwnerSubjectGov gov) {
		super(menu, label, reason);
		this.gov = gov;
		PDI = menu.getPlayerData();
	}

	public LeaveGovOption(OptionMenu menu, String label, OwnerSubjectGov gov) {
		super(menu, label);
		this.gov = gov;
		PDI = menu.getPlayerData();
	}

	public LeaveGovOption(OptionMenu menu, String label, String description, OwnerSubjectGov gov) {
		super(menu, label, description);
		this.gov = gov;
		PDI = menu.getPlayerData();
	}

	@Override
	public Menu response(String input) {
		
		int numOwners = gov.getOwnersList().size();
		int protection = gov.getProtectionlevel();
		
		//If player is only ruler and protection < 2, player leaves and subjects banished, country disappears
		if (numOwners == 1 && protection < 2) {
			gov.removeOwner(PDI.getPlayerID());
			
			//banish all the subjects. NOTE: may or may not be redundant with gov.unregister()
			for (PlayerID PID: gov.getSubjects()) {
				gov.removePlayer(PID);
			}
			
			gov.unregister();
		}
		
		//If player is the only ruler and protection > 2, player leaves and subjects have option to become ruler
		if (numOwners == 1 && protection > 2) {
			gov.removeOwner(PDI.getPlayerID());
			
			//allow subjects to become ruler
			gov.setOwnerOffers(gov.getSubjects());
		}
		
		//If player is not the only ruler, player leaves
		if ( numOwners> 1) {
			gov.removeOwner(PDI.getPlayerID());
		};
		
		//gov.removePlayer(PDI.getPlayerID());
		return menu;
	}
	
}