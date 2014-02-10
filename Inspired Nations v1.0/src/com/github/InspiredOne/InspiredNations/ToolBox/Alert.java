package com.github.InspiredOne.InspiredNations.ToolBox;

import java.util.Calendar;
import java.util.Locale;

import com.github.InspiredOne.InspiredNations.PlayerData;

public abstract class Alert implements Nameable{

	public Calendar calendar;
	
	public Alert() {
		calendar = Calendar.getInstance();
	}

	public abstract String getMessage(PlayerData receiver);
	
	@Override
	public String getName() {
		return "Received: " + calendar.getDisplayName(0, 0, Locale.US);
	}

	@Override
	public void setName(String name) {
		
	}

	@Override
	public String getDisplayName(PlayerData viewer) {
		int end = 20;
		String msg = this.getMessage(viewer);
		if(msg.length() <= 20) {
			end = msg.length() - 1;
			return getName() + " " + msg.substring(0, end);
		}
		else {
			return getName() + " " + msg.substring(0, end) + "...";	
		}
		
	}

}
