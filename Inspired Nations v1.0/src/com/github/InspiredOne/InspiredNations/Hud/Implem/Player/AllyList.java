package com.github.InspiredOne.InspiredNations.Hud.Implem.Player;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;
import com.github.InspiredOne.InspiredNations.Hud.Implem.PlayerProfile;

public class AllyList extends TabSelectOptionMenu<PlayerData> {

	public AllyList(PlayerData PDI) {
		super(PDI);
	}

	public AllyList(PlayerData PDI, String string) {
		super(PDI);
	}

	@Override
	public Menu getPreviousPrompt() {
		// TODO can't figure out the line below
		//return new PlayerProfile(PDI, PDI.getPlayerID());
		return new MainHud(PDI);
	}

	@Override
	public String postTabListPreOptionsText() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void addTabOptions() {
		// TODO this has repeated tabs
		for (OwnerGov gov: PDI.getCitizenship()) {
			for (OwnerGov allygov: gov.getRelations().keySet()) {
				for (PlayerID ally: allygov.getSubjects()) {
					this.taboptions.add(ally.getPDI());
				}
				
			};
		};
		
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
		return "List of " + PDI.getName() + "'s Allies";
	}

}
