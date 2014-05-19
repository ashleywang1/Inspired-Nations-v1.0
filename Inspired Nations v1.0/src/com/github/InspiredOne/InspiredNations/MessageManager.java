package com.github.InspiredOne.InspiredNations;

import java.io.Serializable;
import java.math.BigDecimal;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.InspiredOne.InspiredNations.Exceptions.PlayerOfflineException;
import com.github.InspiredOne.InspiredNations.ToolBox.Alert;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuAlert;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class MessageManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5200407053459680313L;
	
	private PlayerData PDI;
	private String notification = MenuError.NO_ERROR(); //The string that is to be returned when getNotif() is called.
	private int taskID = -1;
	transient BukkitRunnable Timer;
	public MessageManager(PlayerData PDI) {
		this.PDI = PDI;
	}
	
	private void setNotif(String msg) {
		if(!notification.equals(msg)) {
			if(Bukkit.getScheduler().isQueued(taskID)) {
				Timer.cancel();
			}
		}
		this.notification = msg;
	}
	
	public String getNotif() {
		if(!Bukkit.getScheduler().isQueued(taskID) && !notification.equals(MenuError.NO_ERROR())) {
			this.Timer = new BukkitRunnable() {
				@Override
				public void run() {
					setNotif(MenuError.NO_ERROR());
				}
			};
			taskID = Timer.runTaskLater(InspiredNations.plugin, 100).getTaskId();
		}
		return notification;
	}
	
	public void receiveAlert(Alert msg) {
		this.setNotif(msg.getMessage(PDI));
	}
	public void receiveError(String msg) {
		this.setNotif(msg);
	}
	
	
	public String MessageConstructor(String msg, PlayerData from) {
		return from.getDisplayName(this.PDI) + ": " + conditionForMoney(msg, from);
	}
	

	
	/**
	 * Method to send a message from this player
	 * @param msg
	 */
	public void sendChatMessage(String msg) {
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
	public void recieveMessage(String msg) {

		try {
			if(PDI.getPlayer().isConversing()) {
				this.receiveAlert(MenuAlert.MESSAGE_ALERT(msg));
				PDI.getCon().outputNextPrompt();
			}
		} catch (PlayerOfflineException e) {
			
		}
	}
	
	/**
	 * makes sure the player receives the message, even if they are in a menu
	 * @param msg
	 * @param from
	 */
	public void recieveMessage(String msg, PlayerData from) {
		try {
			if(PDI.getPlayer().isConversing()) {
				this.receiveAlert(MenuAlert.MESSAGE_ALERT(this.MessageConstructor(msg, from)));
				if(from != PDI) {
					this.PDI.getCon().outputNextPrompt();
				}
			}
			else {
				PDI.getPlayer().sendMessage(this.MessageConstructor(msg, from));
			}
		} catch (PlayerOfflineException e) {

		}
		InspiredNations.plugin.getServer().getLogger().info(this.MessageConstructor(msg, from));

	}
	
	public void recieveChatMessage(String msg, PlayerData from) {
		try {
			PDI.getPlayer().sendMessage(this.MessageConstructor(msg, from));
		} catch (PlayerOfflineException e) {

		}
	}
	
//////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * Takes the input string and replaces every instances of $(amount)
	 *  with the exchanged value relevant
	 * to the player looking at it.
	 * @param input	
	 * @param target	the player who sees the message;
	 * @return
	 */
	public String conditionForMoney(String input, PlayerData from) {
		BigDecimal amount;
		String output = "";
		if(input.contains("$")) {
			String[] args = input.split(" ");
			boolean checkNext = false;
			boolean scan = false;
			int index = 0;
			String append;
			for(String test:args) {
				append = "";
				if(test.startsWith("$")) {
					scan = true;
					test = test.substring(1);
				}
				// If we want to check next or encountered a $
				if(checkNext || scan) {
					//Check for punctuation
					if(test.endsWith(".") || test.endsWith("!") || test.endsWith(",")
							|| test.endsWith(";") || test.endsWith(":")) {
						append = test.substring(test.length() - 1);
						test = test.substring(0, test.length() - 1);
					}
					scan = false;
					// Try to do the conversion
					try {
						amount = new BigDecimal(test);
						test = Tools.cut(InspiredNations.Exchange.getTransferValue(amount, from.getCurrency(), PDI.getCurrency(),InspiredNations.Exchange.mcup)).toString() + " " + PDI.getCurrency();
						checkNext = false;
					}
					catch (Exception ex) {
						checkNext = !checkNext;
					}
					// Modify the value of the string in the list
					args[index] = test + append;
				}
				index++;
			}
			// Assemble the list back into a readable string.
			for(String concat:args) {
				if(!concat.isEmpty()) {
				output = output.concat(concat + " ");
				}
			}
			return output.substring(0, output.length() - 1);
		}
		else {
			return input;
		}
	}

}
