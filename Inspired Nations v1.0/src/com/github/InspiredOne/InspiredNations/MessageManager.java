package com.github.InspiredOne.InspiredNations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.InspiredOne.InspiredNations.Exceptions.PlayerOfflineException;
import com.github.InspiredOne.InspiredNations.Hud.MenuUpdateEvent;
import com.github.InspiredOne.InspiredNations.ToolBox.Alert;
import com.github.InspiredOne.InspiredNations.ToolBox.Message;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

public class MessageManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5200407053459680313L;
	
	
	private ArrayList<Alert> messages = new ArrayList<Alert>();
	private ArrayList<Alert> history = new ArrayList<Alert>();
	private PlayerData PDI;
	transient BukkitRunnable Timer;
	public MessageManager(PlayerData PDI) {
		this.PDI = PDI;
	}
	
	public void receiveAlert(Alert alert) {
		messages.add(alert);
		history.add(alert);
		pushMessage();
	}
	public void receiveError(final String msg) {
		Alert error = new Alert() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9202786487465493637L;

			@Override
			public String getMessage(PlayerData receiver) {
				return msg;
			}

			@Override
			public boolean menuPersistent() {
				return false;
			}
		};
		if(!msg.isEmpty()) {
			Debug.print("message is: " + msg);
			this.receiveAlert(error);
		}
	}
	
	public void clearMenuVisible() {
		List<Alert> removeList = new ArrayList<Alert>();
		for(Alert alert:messages) {
			if(alert.menuVisible && !alert.menuPersistent()) {
				removeList.add(alert);
			}
		}
		
		for(Alert remove: removeList) {
			messages.remove(remove);
		}
	}
	
	public String pushMessageContent() {
		String output = "";
		List<Alert> removeList = new ArrayList<Alert>();
		try {
			Player player = PDI.getPlayer();
			Collections.sort(messages, Alert.ageSort.getComparator());
			for(Alert alert:messages) {
				if(alert.menuVisible || !player.isConversing()) {
					output = output.concat(alert.getMessage(PDI) + "\n");
					if(alert.expired() || !player.isConversing()) {
						removeList.add(alert);
					}
				}
			}
			for(Alert remove: removeList) {
				messages.remove(remove);
			}
		} catch (PlayerOfflineException e) {
 
		}
		return output;
	}
	
	public void pushMessage() {
		try {
			String output = this.pushMessageContent();
			if(!output.isEmpty()) {
				Debug.print("inside Push Message");
				PDI.getPlayer().sendMessage(output);
				MenuUpdateEvent event = new MenuUpdateEvent(PDI.getPlayerID());
				Bukkit.getServer().getPluginManager().callEvent(event);
				Debug.print("Event has been called");
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void sendChatMessage(String msg) {
		for(PlayerID playerid:InspiredNations.playerdata.keySet()) {
			try {
				Player player = playerid.getPDI().getPlayer();
				if(playerid.equals(PDI.getPlayerID())) {
					playerid.getPDI().getMsg().receiveAlert(new Message(true, PDI.getPlayerID(), msg));
				}
				else {
					playerid.getPDI().getMsg().receiveAlert(new Message(false, PDI.getPlayerID(), msg));
				}
			}
			catch (Exception ex) {
				
			}
		}
		
	}
}
