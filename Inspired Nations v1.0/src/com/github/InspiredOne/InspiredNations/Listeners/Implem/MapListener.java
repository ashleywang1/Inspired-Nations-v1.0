package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.Listeners.InspiredListener;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;

public class MapListener<T extends MapManager<?>> extends InspiredListener<T> {

	double yaw;
	public int rotacount;
	Point3D position;
	public MapListener(T manager) {
		super(manager);
		try {
			yaw = this.getPlayerData().getPlayer().getLocation().getYaw();
			position = new Point3D(this.getPlayerData().getPlayer().getLocation());
			rotacount = getRotaCount(yaw);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
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
		PlayerID whodunit = new PlayerID(event.getPlayer());
		if(!this.getPlayerData().getPlayerID().equals(whodunit)) {
			return;
		}
		else {
			if(event.getFrom().getYaw() < 360 && event.getFrom().getYaw() > -360) {
				yaw = event.getFrom().getYaw();
			}
			if(!getResoPoint(event.getFrom()).equals(getResoPoint(event.getTo()))) {
				
			}
			if(!getResoPoint(event.getFrom()).equals(position)) {
				position = getResoPoint(event.getFrom());
				this.getManager().Update();
				
			}
			else if(getRotaCount(yaw) != rotacount) {
				this.getManager().Update();
				rotacount = getRotaCount(yaw);
				Debug.print("update because looked");
			}
		}
	}
}
