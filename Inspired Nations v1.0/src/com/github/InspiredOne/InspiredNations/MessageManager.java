package com.github.InspiredOne.InspiredNations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.InspiredOne.InspiredNations.Exceptions.PlayerOfflineException;
import com.github.InspiredOne.InspiredNations.Hud.MenuUpdateEvent;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Player.PlayerID;
import com.github.InspiredOne.InspiredNations.ToolBox.Alert;
import com.github.InspiredOne.InspiredNations.ToolBox.IndexedMap;
import com.github.InspiredOne.InspiredNations.ToolBox.Message;

public class MessageManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5200407053459680313L;
	
	private TreeMap<Alert, Integer> messages = new TreeMap<Alert, Integer>(Alert.ageSort.getComparator());
	private TreeMap<Alert, Integer> history = new TreeMap<Alert, Integer>(Alert.ageSort.getComparator());
	//private ArrayList<Alert> messages = new ArrayList<Alert>();
	//private ArrayList<Alert> history = new ArrayList<Alert>();
	private PlayerData PDI;
	transient BukkitRunnable Timer;
	public MessageManager(PlayerData PDI) {
		this.PDI = PDI;
	}
	
	public void receiveAlert(Alert alert, Boolean refresh) {
		if(messages.containsKey(alert)) {
			messages.put(alert, messages.get(alert) + 1);
		}
		else {
			messages.put(alert, 1);
		}
		if(history.containsKey(alert)) {
			history.put(alert, history.get(alert) + 1);
		}
		else {
			history.put(alert, 1);
		}
		pushMessage(refresh);
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
			this.receiveAlert(error, false);
		}
	}
	
	public void clearMenuVisible() {
		List<Alert> removeList = new ArrayList<Alert>();
		for(Alert alert:messages.keySet()) {
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
			
			for(Alert alert:messages.keySet()) {
				if(alert.menuVisible || !player.isConversing()) {
					output = output.concat(alert.getMessage(PDI) + " [" + messages.get(alert) + "]" + "\n");
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
	
	public void pushMessage(boolean refresh) {
		try {
			String output = this.pushMessageContent();
			if(!output.isEmpty()) {
				PDI.getPlayer().sendMessage(output);
				if(refresh) {
					MenuUpdateEvent event = new MenuUpdateEvent(PDI.getPlayerID());
					Bukkit.getServer().getPluginManager().callEvent(event);
				}

			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void sendChatMessage(String msg) {
		InspiredNations.plugin.logger.info(this.PDI.getName() + ": " + msg);
		for(PlayerID playerid:InspiredNations.playerdata.keySet()) {
			try {
				Player player = playerid.getPDI().getPlayer();
				if(playerid.equals(PDI.getPlayerID())) {
					playerid.getPDI().getMsg().receiveAlert(new Message(true, PDI.getPlayerID(), msg), true);
				}
				else {
					playerid.getPDI().getMsg().receiveAlert(new Message(playerid.getPDI().chatState, PDI.getPlayerID(), msg), true);
				}
			}
			catch (Exception ex) {
				
			}
		}
		
	}
}
