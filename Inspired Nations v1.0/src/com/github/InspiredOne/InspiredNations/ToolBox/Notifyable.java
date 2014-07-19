package com.github.InspiredOne.InspiredNations.ToolBox;

import com.github.InspiredOne.InspiredNations.ToolBox.Messaging.Alert;

public interface Notifyable {

	/**
	 * Makes sure this receiver gets this message
	 */
	public void sendNotification(Alert msg);
	
}
