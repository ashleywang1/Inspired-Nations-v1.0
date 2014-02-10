package com.github.InspiredOne.InspiredNations.ToolBox;

import java.util.Calendar;
import java.util.Locale;

import com.github.InspiredOne.InspiredNations.PlayerData;

public class Alert implements Nameable{

	public Calendar calendar;
	public String msg;
	
	public Alert(String msg) {
		calendar = Calendar.getInstance();
		this.msg = msg;
	}

	public String getMessage() {
		return msg;
	}
	
	@Override
	public String getName() {
		return "Revieved: " + calendar.getDisplayName(0, 0, Locale.US);
	}

	@Override
	public void setName(String name) {
		
	}

	@Override
	public String getDisplayName(PlayerData viewer) {
		int end = 20;
		if(msg.length() <= 20) {
			end = msg.length() - 1;
			return getName() + " " + msg.substring(0, end);
		}
		else {
			return getName() + " " + msg.substring(0, end) + "...";	
		}
		
	}

}
