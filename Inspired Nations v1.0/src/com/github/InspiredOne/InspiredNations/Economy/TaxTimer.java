package com.github.InspiredOne.InspiredNations.Economy;

import java.io.Serializable;

import org.bukkit.scheduler.BukkitRunnable;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.InspiredNations;

public class TaxTimer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7051455502418466458L;
	
	private int cycleLength;
	private int countdown;
	
	public TaxTimer(){
		cycleLength = InspiredNations.plugin.getConfig().getInt("tax_cycle_length");
		countdown = cycleLength;
	}
	
	

	public void startTimer() {
		
		new BukkitRunnable() {

			@Override
			public void run() {
				countdown--;
				if(countdown == 0) {
					countdown = cycleLength;
				}
			}
			
		}.runTaskTimer(InspiredNations.plugin, 0, 20);		
	}
	
	public double getFractionLeft() {
		return ((double) countdown)/((double)cycleLength);
	}
}
