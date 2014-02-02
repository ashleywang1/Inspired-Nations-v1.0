package com.github.InspiredOne.InspiredNations.Hud;

import org.bukkit.event.EventHandler;

import com.github.InspiredOne.InspiredNations.Economy.TaxTimerEvent;
import com.github.InspiredOne.InspiredNations.Listeners.InspiredListener;

public class TaxTimerListener<T extends TaxTimerManager<?>> extends InspiredListener<T> {

	public TaxTimerListener(T manager) {
		super(manager);
	}
	
	@EventHandler
	public void onTaxTimerEvent(TaxTimerEvent event) {
		this.manager.Update();
	}

}
