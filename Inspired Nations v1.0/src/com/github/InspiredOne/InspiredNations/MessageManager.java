package com.github.InspiredOne.InspiredNations;

import java.io.Serializable;
import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.ToolBox.Tools;
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
		return from.getPlayer().getDisplayName() + ": " + conditionForMoney(msg, from);
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
	/**
	 * Takes the input string and replaces every instances of $(amount) with the exchanged value relevant
	 * to the player looking at it.
	 * @param input	
	 * @param target	the player who sees the message;
	 * @return
	 */
	public String conditionForMoney(String input, PlayerData from) {
		Debug.print("Inside ConditionMoneym 1");
		BigDecimal amount;
		String output = "";
		if(input.contains("$")) {
			Debug.print("Inside ConditionMoney 2");
			String[] args = input.split(" ");
			boolean checkNext = false;
			boolean scan = false;
			int index = 0;
			String append;
			for(String test:args) {
				append = "";
				Debug.print("Inside ConditionMoney " + test);
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
					Debug.print("Inside ConditionMoney 4");
					// Try to do the conversion
					try {
						amount = new BigDecimal(test);
						test = Tools.cut(InspiredNations.Exchange.getValue(amount, from.getCurrency(), PDI.getCurrency())).toString() + " " + PDI.getCurrency();
						checkNext = false;
					}
					catch (Exception ex) {
						Debug.print("Inside ConditionMoney 5");
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
