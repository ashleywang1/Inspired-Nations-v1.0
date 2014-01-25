package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.Exceptions.CuboidNotCompletedException;
import com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimLand.ClaimCuboid;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.Regions.Cuboid;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class ClaimCuboidManager<T extends ClaimCuboid> extends ActionManager<T> {

	public Point3D point1;
	public Point3D point2;
	private Cuboid temp;
	public ClaimCuboidManager(T menu) {
		super(menu);
		this.listeners.add(new ClaimCuboidListener<ClaimCuboidManager<?>>(this));
	}
	
	public void setPoint1(Location local) {
		point1 = new Point3D(local);
		Debug.print(point1.y);
		if(point2 != null) {
			temp = new Cuboid(point1, point2);
			Debug.print(temp.getPointMax().y);
			Debug.print(temp.getPointMin().y);
		}
	}
	
	public void setPoint2(Location local) {
		point2 = new Point3D(local);
		Debug.print(point2.y);
		if(point1 != null) {
			temp = new Cuboid(point1, point2);
			Debug.print(temp.getPointMax().y);
			Debug.print(temp.getPointMin().y);
		}
	}
	
	public int getVolume() {
		if(temp == null) {
			return 0;
		}
		else {
			return temp.volume();
		}
	}
	
	public Cuboid getCuboid() throws CuboidNotCompletedException {
		if(temp == null) {
			throw new CuboidNotCompletedException();
		}
		else {

			return temp;
		}
	}
	
	@Override
	public void Update() {
		this.getActionMenu().setError(MenuError.NO_ERROR());
		this.getActionMenu().Update();
	}
	/**
	 * Used to reset this manager, putting all variables back to null
	 */
	public void reset() {
		point1 = null;
		point2 = null;
		temp = null;
	}

	
}
