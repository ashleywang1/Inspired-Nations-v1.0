package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Listeners.InspiredListener;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;

public class MapListener<T extends MapManager<?>> extends InspiredListener<T> {

	double yaw;
	public int rotacount;
	public MapListener(T manager) {
		super(manager);
		yaw = this.getPlayerData().getPlayer().getLocation().getYaw();
		rotacount = getRotaCount(yaw);
	}
	
	public int getRotaCount(double yawValue)	{
		return (int) (Math.floor((45 + Math.abs(yawValue))/90) % 4);
	}
	public Point3D getResoPoint(Location loca) {
		Point3D outpoint = new Point3D((int) Math.floor(loca.getBlockX()/((int) Math.pow(2, manager.zoom))), loca.getBlockY(), (int) Math.floor(loca.getBlockZ()/((int) Math.pow(2, manager.zoom))), loca.getWorld());
		return outpoint;
	//	return new Point3D(loca);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {

		if(this.getPlayerData().getPlayer() != event.getPlayer()) {
			return;
		}
		else {
			if(event.getTo().getYaw() < 360 && event.getTo().getYaw() > -360) {
				yaw = event.getTo().getYaw();
			}
			if(!getResoPoint(event.getFrom()).equals(getResoPoint(event.getTo()))) {
				this.getManager().Update();
				Debug.print("update because moved");
			}
			else if(getRotaCount(yaw) != rotacount) {
				this.getManager().Update();
				Debug.print("update because looked");
			}
		}
	}
}
