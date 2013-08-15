package com.github.InspiredOne.InspiredNations.ToolBox;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.ChatColor;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public class MenuTools {

	public MenuTools() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Builds the refresh space out of new-line characters.
	 * @return	the space required to clear the chat area for a menu
	 */
	public static String space(InspiredNations plugin) {
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
		public static final String Plugin = "Plugin";
		public static final String PlayerData = "Player Data";
		
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
	
	public enum MenuError {
		NO_ERROR(""),
		INVALID_NUMBER_INPUT("Your entry must be a number."),
		OUT_OF_RANGE_NUMBER_INPUT("That is not an option."),
		NOT_AN_OPTION("That is not an option.");
		
		
		
		private String error;
		
        private MenuError(String error) {
                this.error = error;
        }
        @Override
        public String toString() {
        	return error;
        }
	}
	
	public static Menu MenuInstance(InspiredNations plugin, PlayerData PDI, Class<? extends Menu> menu) {
		try {
			return menu.getConstructor(InspiredNations.class, PlayerData.class).newInstance(plugin, PDI);
		} 
		catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
