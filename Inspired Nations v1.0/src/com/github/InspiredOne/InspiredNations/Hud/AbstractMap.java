package com.github.InspiredOne.InspiredNations.Hud;


import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.MapManager;

public abstract class AbstractMap extends InputMenu {

	private int Zoom = 4;
	private MapManager<AbstractMap> manager;
	
	public AbstractMap(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public void Init() {
		manager = new MapManager<AbstractMap>(this);
		managers.add(manager);
	}

	@Override
	public void actionResponse() {
		if(this.manager.preTabEntry.equalsIgnoreCase("+") && Zoom < 8) {
			Zoom--;
		}
		else if(this.manager.preTabEntry.equalsIgnoreCase("-") && Zoom > 0) {
			Zoom++;
		}
		ActionResponse();
	}
	
	public abstract void ActionResponse();

}
