package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import com.github.InspiredOne.InspiredNations.Governments.GlobalGov;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Listeners.TabManager;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;

public class MapManager<T extends ActionMenu> extends TabManager<T> {

	public int zoom = 4;
	MapListener<MapManager<T>> maplis;
	public MapManager(T menu) {
		super(menu);
		maplis = new MapListener<MapManager<T>>(this);
		listeners.add(maplis);
		listeners.add(new TabListener<MapManager<T>>(this));
	}
	
	public String drawMap(InspiredGov gov, int size) {
		if(gov.getSuperGov().equals(GlobalGov.class)) {
			
			return drawMap(gov.getClass(), size);
		}
		else {
			return drawMap(gov.getSuperGov(), size);
		}
	}
	
	public String drawMap(Class<? extends InspiredGov> gov, int size) {
		return Tools.drawMap(this.getPlayerData(), (int) Math.pow(2, zoom), gov, size);
	}
	
	@Override
	public void Update() {
		if(this.preTabEntry.equalsIgnoreCase("+") && zoom > 0) {
			zoom--;
		}
		else if(this.preTabEntry.equalsIgnoreCase("-") && zoom < 8) {
			zoom++;
		}
		this.getActionMenu().Update();
		this.preTabEntry = "";
	}
	
	@Override
	public void textChange() {
		maplis.rotacount = maplis.getRotaCount(maplis.yaw);
	}
	
}
