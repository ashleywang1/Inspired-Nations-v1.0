package com.github.InspiredOne.InspiredNations.Hud.Implem;


import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerSubjectGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

public class DeleteGovOption extends Option {

	OwnerGov gov;
	PlayerData PDI;
	
	public DeleteGovOption(OptionMenu menu, String label, OptionUnavail reason, OwnerGov gov) {
		super(menu, label, reason);
		this.gov = gov;
		PDI = menu.getPlayerData();
	}

	public DeleteGovOption(OptionMenu menu, String label, OwnerGov gov) {
		super(menu, label);
		this.gov = gov;
		PDI = menu.getPlayerData();
	}

	public DeleteGovOption(OptionMenu menu, String label, String description, OwnerGov gov) {
		super(menu, label, description);
		this.gov = gov;
		PDI = menu.getPlayerData();
	}

	@Override
	public Menu response(String input) {
		
		
		int numOwners = gov.getOwnersList().size();
		int protection = gov.getProtectionlevel();
		
		//If player is only ruler and protection >= 2, player leaves and subjects banished, country disappears
		if (numOwners == 1 && protection >= 2) {
			
			
			//unregister the government from the map
			gov.unregister();
			
//			//banish all the subjects.
//			for (PlayerID PID: gov.getSubjects()) {
//				gov.removePlayer(PID);
//				
//			}

//			for (InspiredGov SubGov: gov.getAllSubGovsAndFacilitiesJustBelow()) {//list that returns OwnerGovs and OwnerSubjectGovs
//				if (SubGov instanceof OwnerGov) {
//					for (PlayerID PID: ((OwnerGov) SubGov).getSubjects()) {
//						gov.removePlayer(PID);
//						//SubGov.unregister();
//					}
//				}
//			}
			
//			//banish the player from all sub governments
//			List<OwnerGov> AllGovs = PDI.getCitizenship();
//			AllGovs.remove(InspiredNations.global);
//			
//			for (OwnerGov subGov: AllGovs) {
//				Debug.print(subGov.getName());
//				subGov.removePlayer(PDI.getPlayerID());
//				subGov.unregister();
//			};
		}
		
		//If player is the only ruler and protection < 2, player leaves and subjects have option to become ruler
		else if (numOwners == 1 && protection < 2) {
			gov.removePlayer(PDI.getPlayerID());
			
			//allow subjects to become ruler
			//TODO later replace this with checks on MainHud for governments without owners
			gov.setOwnerOffers(gov.getSubjects());
		}
		
		//If player is not the only ruler, player leaves
		else if ( numOwners> 1) {
			gov.removePlayer(PDI.getPlayerID());
		};
		
		return menu;
	}
	
}