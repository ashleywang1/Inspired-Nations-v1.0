package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;


import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Player.PlayerID;

public class DeleteGovOption extends Option {
	
	public PlayerData PDI;
	public OwnerGov gov;

	public DeleteGovOption(OptionMenu menu, String label, OwnerGov gov, PlayerData PDI) {
		super(menu, label);
		this.gov = gov;
		this.PDI = PDI;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Menu response(String input) {
		// TODO Auto-generated method stub
		
//		for (PlayerID PID: gov.getOwners()) {
//			gov.removeOwner(PID);
//		};
		
		Debug.print("Just before deleting Gov");
		//gov.deleteGov();
		gov.removeOwner(PDI.getPlayerID());
		Debug.print("deleting Gov");
		

		return new MainHud(PDI);
		//return menu;
	}

}
