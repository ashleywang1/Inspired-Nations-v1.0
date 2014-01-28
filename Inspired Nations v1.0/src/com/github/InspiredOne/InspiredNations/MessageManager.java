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
	
	public String MessageConstructor(String msg, PlayerData from) {
		return from.getPlayer().getDisplayName() + ": " + msg;
	}
	/**
	 * Method to send a message from this player
	 * @param msg
	 */
	public void sendChatMessage(String msg) {
		Debug.print("inside sendMessage");
		for(PlayerData PDITarget:InspiredNations.playerdata.values()) {
			if(PDITarget == PDI) {
				PDITarget.getMsg().recieveMessage(msg, PDI);
			}
			else {
				PDITarget.getMsg().recieveChatMessage(msg, PDI);
			}
		}
	}
	/**
	 * makes sure the player receives the message, even if they are in a menu
	 * @param msg
	 * @param from
	 */
	public void recieveMessage(String msg, PlayerData from) {
		if(PDI.getPlayer() == null) {
			return;
		}
		if(PDI.getPlayer().isConversing()) {
			this.PDI.getCon().getContext().setSessionData(ContextData.Alert, MenuAlert.makeMessage(this.MessageConstructor(msg, from)));
			if(from != PDI) {
				this.PDI.getCon().outputNextPrompt();
			}
		}
		else {
			PDI.getPlayer().sendMessage(this.MessageConstructor(msg, from));
		}
	}
	
	public void recieveChatMessage(String msg, PlayerData from) {
		if(PDI.getPlayer() == null) {
			return;
		}
		PDI.getPlayer().sendMessage(this.MessageConstructor(msg, from));
	}

}
