package com.github.InspiredOne.InspiredNations.ToolBox;

import org.bukkit.ChatColor;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public class MenuTools {

	public static InspiredNations plugin = InspiredNations.plugin;
	
	public MenuTools() {
	}

	/**
	 * Builds the refresh space out of new-line characters.
	 * @return	the space required to clear the chat area for a menu
	 */
	public static String space() {
		return Tools.repeat("\n", plugin.getConfig().getInt("hud_pre_message_space"));
	}
	
	/**
	 * Builds the divider to be used in the menus
	 * @param text
	 * @return
	 */
	public static String addDivider(String text) {
		return text.concat(TextColor.DIVIDER + Tools.repeat("-", 53) + "\n" + ChatColor.RESET);
	}
	/**
	 * Builds the header for menus. Adds the <code>ChatColor</code> character to the beginning
	 * and clears it afterward.
	 * @param msg	the <code>String</code> to be used in the header
	 * @return		the <code>String</code> processed to be in the menu
	 */
	public static String header(String msg) {
		return addDivider(TextColor.HEADER + msg + "\n" + ChatColor.RESET);
	}

	public class ContextData {
		public static final String Error = "Error";
		
	}
	
	public enum OptionUnavail {
		NOT_UNAVAILABLE("");
		
		private String reason;
		
		private OptionUnavail(String reason) {
			this.reason = reason;
		}
        @Override
        public String toString() {
        	return reason;
        }
	}
	
	public static class MenuError {
		
		public static String NO_ERROR() {
			return "";
		}
		public static String INVALID_NUMBER_INPUT() {
			return makeMessage("Your entry must be a number.");
		}
		public static String OUT_OF_RANGE_NUMBER_INPUT() {
			return makeMessage("That is not an option.");
		}
		public static String NOT_AN_OPTION() {
			return makeMessage("That is not an option.");
		}
		public static String NAME_ALREADY_TAKEN(Class<? extends InspiredGov> gov) {
			
			String GovName = getTypeName(gov);
			return makeMessage("That " + GovName + " name is already taken.");
		}
		public static String MONEY_NAME_ALREADY_TAKEN() {
			return makeMessage("That currency name is already in use.");
		}
		public static String MONEY_MULTIPLYER_TOO_LARGE() {
			return makeMessage("Your currency is too inflated.");
		}
		public static String MONEY_MULTIPLYER_TOO_SMALL() {
			return makeMessage("Your currency is too valuable.");
		}
		
		private static final String getTypeName(Class<? extends InspiredGov> gov) {
			String GovName = "";
			GovName = GovFactory.getGovInstance(gov).getTypeName();
			return GovName;
		}
		
		private static final String makeMessage(String msg) {
			return "\n" + TextColor.ALERT + msg;
		}
	}
}
