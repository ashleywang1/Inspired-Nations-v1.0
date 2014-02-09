package com.github.InspiredOne.InspiredNations.Regions;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;
/**
 * Region to be used when no land is actually claimed.
 * @author Jedidiah Phillips
 *
 */
public class nullRegion extends Region {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3247449813190347666L;

	@Override
	public boolean IsIn(Region region) {
		return false;
	}

	@Override
	public boolean IsIn(NonCummulativeRegion region) {
		return false;
	}

	@Override
	public boolean IsIn(CummulativeRegion<?> region) {
		return false;
	}

	@Override
	public int volume() {
		return 0;
	}

	@Override
	public boolean contains(Point3D location) {
		return false;
	}

	@Override
	public boolean Intersects(Region region) {
		return false;
	}

	@Override
	public boolean Intersects(NonCummulativeRegion region) {
		return false;
	}

	@Override
	public boolean Intersects(CummulativeRegion<?> region) {
		return false;
	}

	@Override
	public String getTypeName() {
		return Debug.InformPluginDev;
	}

	@Override
	public String getDescription() {
		return Debug.InformPluginDev;
	}

	@Override
	public Menu getClaimMenu(PlayerData PDI, Menu previous, InspiredGov gov) {
		return null;
	}

	@Override
	protected boolean instantiated() {
		return false;
	}

}
