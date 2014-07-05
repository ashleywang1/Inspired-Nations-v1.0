package com.github.InspiredOne.InspiredNations.Regions.Implem;

import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.Implem.ChestShop;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimAndUnclaimLand.ClaimSignShop;
import com.github.InspiredOne.InspiredNations.Regions.CummulativeRegion;
import com.github.InspiredOne.InspiredNations.Regions.NonCummulativeRegion;
import com.github.InspiredOne.InspiredNations.Regions.Region;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;

public class SignShopRegion extends Region {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5427150326293466846L;
	public Point3D loca;

	
	public SignShopRegion() {

	}

	@Override
	protected boolean instantiated() {
		
		return loca != null;
	}

	@Override
	public boolean IsIn(Region region) {
		Debug.print("Inside SignShopRegion 1");
		return region.contains(loca);
	}

	@Override
	public boolean IsIn(NonCummulativeRegion region) {
		Debug.print("Inside SignShopRegion 2");
		return region.contains(loca);
	}

	@Override
	public boolean IsIn(CummulativeRegion<?> region) {
		Debug.print("Inside SignShopRegion3");
		return region.contains(loca);
	}

	@Override
	public int volume() {
		Debug.print("Inside SignShopRegion 4");
		if(instantiated()) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean contains(Point3D location) {
		Debug.print("Inside SignShopRegion 5");
		return location.equals(loca);
	}

	@Override
	public boolean Intersects(Region region) {
		Debug.print("Inside SignShopRegion 6");
		
		return region.contains(loca);
	}

	@Override
	public boolean Intersects(NonCummulativeRegion region) {
		Debug.print("Inside SignShopRegion 7");
		return region.contains(loca);
	}

	@Override
	public boolean Intersects(CummulativeRegion<?> region) {
		Debug.print("Inside SignShopRegion 8");
		return region.contains(loca);
	}

	@Override
	public String getTypeName() {
		Debug.print("Inside SignShopRegion 9");
		return "Sign Shop";
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public Menu getClaimMenu(PlayerData PDI, Menu previous, InspiredGov gov) {
		InspiredGov supergov = gov.getSuperGovObj();
		if(supergov instanceof ChestShop) {
			Debug.print("SignShop 11");
			return new ClaimSignShop(PDI, previous, ((ChestShop) supergov).getItems());
		}
		else {
			Debug.print("SignShop 10");
			return null;
		}
	}
		
	@Override
	public Location getCharacteristicPoint() {
		return loca.getLocation();
	}

}
