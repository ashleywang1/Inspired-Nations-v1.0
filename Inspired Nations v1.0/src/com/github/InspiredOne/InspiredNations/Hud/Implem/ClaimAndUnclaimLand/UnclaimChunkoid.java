package com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimAndUnclaimLand;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.InputMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.MapManager;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.UnclaimChunkoidManager;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Point2D;

public class UnclaimChunkoid extends InputMenu {

	UnclaimChunkoidManager<UnclaimChunkoid> manager;
	MapManager<UnclaimChunkoid> mapmanager;
	
	Menu previous;
	public InspiredGov gov;
	
	public UnclaimChunkoid(PlayerData PDI, Menu previous, InspiredGov gov) {
		super(PDI);
		this.previous = previous;
		this.gov = gov;
	}

	@Override
	public Menu nextMenu() {
		return this;
	}

	@Override
	public String validate(String input) {
		if(input.equalsIgnoreCase("begin") || input.equalsIgnoreCase("stop")) {
			return MenuError.NO_ERROR();
		}
		else {
			return MenuError.NOT_AN_OPTION();
		}
	}

	@Override
	public void useInput(String input) {
		if(input.equalsIgnoreCase("begin")) {
			this.manager.setUnclaim(true);
		}
		else if(input.equalsIgnoreCase("stop")) {
			this.manager.setUnclaim(false);
		}
	}

	@Override
	public List<String> getTabOptions() {
		List<String> output = new ArrayList<String>();
		return output;
	}

	@Override
	public String getInstructions() {
		return "Type 'begin' to begin claiming and 'stop' or 'back' to stop." + mapmanager.drawMap(gov, 4);
	}

	@Override
	public void Init() {
		mapmanager = new MapManager<UnclaimChunkoid>(this);
		manager = new UnclaimChunkoidManager<UnclaimChunkoid>(this, new Point2D(PDI.getPlayer().getLocation().getChunk()));
		this.managers.add(mapmanager);
		this.managers.add(manager);
	}

	@Override
	public String getHeader() {
		return "Unclaim Chunkoid";
	}

	@Override
	public Menu getPreviousMenu() {
		return previous;
	}

	@Override
	public boolean getPassBy() {
		return false;
	}

}
