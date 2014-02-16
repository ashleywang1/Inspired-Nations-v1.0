package com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimAndUnclaimLand;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.InputMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.ClaimChunkoidManager;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.MapManager;
import com.github.InspiredOne.InspiredNations.Regions.nullRegion;
import com.github.InspiredOne.InspiredNations.Regions.Implem.Chunkoid;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Point2D;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

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
		return "Claim Chunkoid 1:" + (int) Math.pow(2, mapmanager.zoom);
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
		Chunkoid unit = new Chunkoid(new Point2D(0,0,PDI.getPlayer().getWorld()));
		// Tax paid that is independent of chunks
		BigDecimal zero = gov.taxValue(new nullRegion(), InspiredNations.taxTimer.getFractionLeft(), gov.getProtectionlevel(), PDI.getCurrency());
		// Total tax from chunks
		BigDecimal totalcost = Tools.cut(gov.taxValue(region, 1, gov.getProtectionlevel(), PDI.getCurrency()).subtract(zero));
		// Total cost of one chunk
		BigDecimal unitcost = Tools.cut(gov.taxValue(unit, 1, gov.getProtectionlevel(), PDI.getCurrency()).subtract(zero));
		// Current cost of one chunk at the current moment
		BigDecimal currenccost = Tools.cut(gov.taxValue(unit, InspiredNations.taxTimer.getFractionLeft()
				, gov.getProtectionlevel(), PDI.getCurrency()).subtract(gov.taxValue(new nullRegion(), InspiredNations.taxTimer.getFractionLeft()
						, gov.getProtectionlevel(), PDI.getCurrency())));
		
		String output = this.mapmanager.drawMap(gov,4);
		output = MenuTools.addDivider(output);
		output = output.concat(TextColor.INSTRUCTION + "Type '" + TextColor.VALUE + "begin" + TextColor.INSTRUCTION
				+ "' and walk around to claim. Type '" + TextColor.VALUE + "stop" + TextColor.INSTRUCTION + "' to stop.\n");
		output = MenuTools.oneLineWallet(output, PDI, gov.getAccounts());
		output = output.concat(TextColor.VALUEDESCRI + "Total Chunks: " + TextColor.VALUE + region.getRegions().size()
				+ TextColor.UNIT + " Chunks\n");
		output = output.concat(TextColor.VALUEDESCRI + "Total/Unit/Current: " + TextColor.VALUE +
				totalcost + "/" + unitcost + "/" + currenccost + TextColor.UNIT + " " + PDI.getCurrency() + "\n");
		return output;
	}

	@Override
	public void Init() {
		this.manager = new ClaimChunkoidManager<ClaimChunkoid>(this, new Point2D(PDI.getPlayer().getLocation().getChunk()));
		this.mapmanager = new MapManager<ClaimChunkoid>(this);
		this.managers.add(manager);
		this.managers.add(mapmanager);
	}

	@Override
	public InputMenu getSelf() {
		return new ClaimChunkoid(PDI, previous, gov);
	}

}
