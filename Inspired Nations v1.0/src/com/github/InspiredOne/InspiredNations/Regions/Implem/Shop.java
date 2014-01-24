package com.github.InspiredOne.InspiredNations.Regions.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Regions.CummulativeRegion;
import com.github.InspiredOne.InspiredNations.Regions.NonCummulativeRegion;
import com.github.InspiredOne.InspiredNations.Regions.Region;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;

public class Shop extends Region {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2195353839150455099L;


	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Menu getClaimMenu(PlayerData PDI, Menu previous, InspiredGov gov) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean IsIn(NonCummulativeRegion region) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean IsIn(CummulativeRegion region) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int volume() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(Point3D location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Intersects(NonCummulativeRegion region) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Intersects(CummulativeRegion region) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean IsIn(Region region) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Intersects(Region region) {
		// TODO Auto-generated method stub
		return false;
	}

}
