package com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimLand;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.InputMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.ClaimPolygonPrismManager;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.MapManager;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class ClaimPolygonPrism extends InputMenu {

	public InspiredGov gov;
	public Menu previous;
	public ClaimPolygonPrismManager<ClaimPolygonPrism> manager;
	public MapManager<ClaimPolygonPrism> mapmanager;
	public ClaimPolygonPrism(PlayerData PDI, Menu previous, InspiredGov gov) {
		super(PDI);
		this.gov = gov;
		this.previous = previous;
	}

	@Override
	public Menu nextMenu() {
		return previous;
	}
	
	@Override
	public String getFiller() {
		return this.mapmanager.drawMap(gov);
	}

	@Override
	public String validate(String input) {
		if(input.equalsIgnoreCase("finish")) {
			return MenuError.NO_ERROR();
		}
		return MenuError.NOT_AN_OPTION();
	}

	@Override
	public void useInput(String input) {
		
	}

	@Override
	public List<String> getTabOptions() {
		List<String> output = new ArrayList<String>();
		return output;
	}

	@Override
	public String getInstructions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Init() {
		manager = new ClaimPolygonPrismManager<ClaimPolygonPrism>(this);
		mapmanager = new MapManager<ClaimPolygonPrism>(this);
		this.managers.add(manager);
		this.managers.add(mapmanager);
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Menu getPreviousMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getPassBy() {
		// TODO Auto-generated method stub
		return false;
	}

}
