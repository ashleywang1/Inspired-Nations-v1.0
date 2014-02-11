package com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public abstract class PickPlayerGeneral extends TabSelectOptionMenu<PlayerID> {

	protected Menu previous;
	
	public PickPlayerGeneral(PlayerData PDI, Menu previous) {
		super(PDI);
		this.previous = previous;
	}

	@Override
	public Menu getPreviousPrompt() {
		return previous;
	}

	@Override
	public final void Init() {
		for(PlayerID player:InspiredNations.playerdata.keySet()) {
			if(check(player)) {
				this.taboptions.add(player);
			}
		}
		
		insertOptions();
		
	}
	/**
	 * Checks to see if the player should be listed in the tab options list.
	 * @param player
	 * @return
	 */
	public abstract boolean check(PlayerID player); 
	/**
	 * Inserts the options into the 
	 */
	public abstract void insertOptions();
}
