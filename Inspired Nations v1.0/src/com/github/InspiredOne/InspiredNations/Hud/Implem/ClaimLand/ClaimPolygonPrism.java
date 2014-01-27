package com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimLand;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.InspiredGovTooStrongException;
import com.github.InspiredOne.InspiredNations.Exceptions.InsufficientRefundAccountBalanceException;
import com.github.InspiredOne.InspiredNations.Exceptions.RegionOutOfEncapsulationBoundsException;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.InputMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.ClaimPolygonPrismManager;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.MapManager;
import com.github.InspiredOne.InspiredNations.Regions.Implem.PolygonPrism;
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
	public String validate(String input) {
		PolygonPrism prism = (PolygonPrism) manager.prism.clone();
		manager.prism = new PolygonPrism();
		if(input.equalsIgnoreCase("finish")) {
			try {
				if(prism.isSimple()) {
					gov.setLand(prism);
					return MenuError.NO_ERROR();
				}
				else {
					return (MenuError.POLYGON_NOT_SIMPLE_SHAPE());
				}
			} catch (BalanceOutOfBoundsException e) {
				return (MenuError.NOT_ENOUGH_MONEY());
			} catch (InspiredGovTooStrongException e) {
				return (MenuError.GOV_TOO_STRONG(e.gov));
			} catch (RegionOutOfEncapsulationBoundsException e) {
				return (MenuError.CLAIM_OUT_OF_BOUNDS(e.gov));
			} catch (InsufficientRefundAccountBalanceException e) {
				//TODO do something with this.
			}
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
		return this.mapmanager.drawMap(gov);
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
		return "Claim Polygon Prism " + manager.prism.volume();
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
