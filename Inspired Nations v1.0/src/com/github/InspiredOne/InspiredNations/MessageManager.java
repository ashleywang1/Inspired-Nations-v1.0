package com.github.InspiredOne.InspiredNations;

import java.io.Serializable;

import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.ContextData;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuAlert;

public class MessageManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5200407053459680313L;
	
	PlayerData PDI;
	
	public MessageManager(PlayerData PDI) {
		this.PDI = PDI;
	}
	
	public void sendMessage(String msg) {
		for(PlayerData PDITarget:InspiredNations.playerdata.values()) {
			PDITarget.getMsg().recieveMessage(msg);
		}
		this.recieveMessage(msg);
	}
	public void recieveMessage(String msg) {
		if(PDI.getPlayer().isConversing()) {
			this.PDI.getCon().getContext().setSessionData(ContextData.Alert, MenuAlert.makeMessage(msg));
			this.PDI.getCon().outputNextPrompt();
		}
		else {
			PDI.getPlayer().sendMessage(MenuAlert.makeMessage(msg));
		}
	}

}
