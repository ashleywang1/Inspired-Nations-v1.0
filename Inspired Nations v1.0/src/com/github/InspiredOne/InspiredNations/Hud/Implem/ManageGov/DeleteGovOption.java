package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;


import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Player.PlayerID;

public class DeleteGovOption extends Option {
	
	public PlayerID PID;
	public OwnerGov gov;

	public DeleteGovOption(OptionMenu menu, String label, OwnerGov gov, PlayerID PID) {
		super(menu, label);
		this.gov = gov;
		this.PID = PID;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Menu response(String input) {
		// TODO Auto-generated method stub
		
//		for (PlayerID PID: gov.getOwners()) {
//			gov.removeOwner(PID);
//		};
		
		gov.deleteGov();
		Debug.print("deleting Gov");
		
		return menu;
	}

}
