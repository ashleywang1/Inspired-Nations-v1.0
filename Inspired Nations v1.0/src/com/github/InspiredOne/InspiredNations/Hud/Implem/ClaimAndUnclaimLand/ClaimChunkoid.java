package com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimAndUnclaimLand;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.InputMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.ClaimChunkoidManager;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.MapManager;
import com.github.InspiredOne.InspiredNations.Regions.Implem.Chunkoid;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Point2D;

public class ClaimChunkoid extends InputMenu {

	public Menu previous;
	public InspiredGov gov;
	public Chunkoid region;
	public ClaimChunkoidManager<ClaimChunkoid> manager;
	private MapManager<ClaimChunkoid> mapmanager;

	public ClaimChunkoid(PlayerData PDI, Menu previous, InspiredGov gov) {
		super(PDI);
		this.previous = previous;
		this.gov = gov;
		if(!(gov.getRegion().getRegion() instanceof Chunkoid)) {
			gov.getRegion().setRegion(new Chunkoid());
		}
		this.region = (Chunkoid) gov.getRegion().getRegion();
	}

	@Override
	public void actionResponse() {
	}
	
	@Override
	public Menu getPreviousMenu() {
		return previous;
	}

	@Override
	public String getHeader() {
		return "Claim Chunkoid";
	}

	@Override
	public boolean getPassBy() {
		return false;
	}

	@Override
	public Menu getPassTo() {
		return null;
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
			manager.setClaiming(true);
		}
		else if (input.equalsIgnoreCase("stop")) {
			manager.setClaiming(false);
		}
	}

	@Override
	public List<String> getTabOptions() {
		List<String> output = new ArrayList<String>();
		return output;
	}

	@Override
	public String getInstructions() {
		return this.mapmanager.drawMap(gov);
	}

	@Override
	public void Init() {
		this.manager = new ClaimChunkoidManager<ClaimChunkoid>(this, new Point2D(PDI.getPlayer().getLocation().getChunk()));
		this.mapmanager = new MapManager<ClaimChunkoid>(this);
		this.managers.add(manager);
		this.managers.add(mapmanager);
	}

}
