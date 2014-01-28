package com.github.InspiredOne.InspiredNations.ToolBox;

import java.math.BigDecimal;

import org.bukkit.ChatColor;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

/**
 * Formating for all the text in the menus. Methods in here control everything from
 * the color of text to the spacing and the style.
 * @author Jedidiah Phillips
 *
 */
public class MenuTools {

	public static InspiredNations plugin = InspiredNations.plugin;
	
	public MenuTools() {
	}

	/**
	 * Builds the refresh space out of new-line characters.
	 * @return	the space required to clear the chat area for a menu
	 */
	public static String space() {
		return Tools.repeat("\n ", plugin.getConfig().getInt("hud_pre_message_space"));
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
		public static final String Alert = "Alert";
	//	public static final String PromptData = "PromptData";
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
	
	public static class MenuAlert {
		public static String NO_ALERT() {
			return "";
		}
		
		public static String makeMessage(Object input) {
			return "\n" + TextColor.ALERT + input.toString();
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
		public static String ACCOUNT_NAME_ALREADY_TAKEN() {
			return makeMessage("That account name is already in use.");
		}
		public static String NO_MATCHES_FOUND() {
			return makeMessage("There are no matches found.");
		}
		public static String NO_SUB_GOVS_UNDER_THIS_GOV() {
			return makeMessage("There are no governments under the control of this government.");
		}
		public static String NOT_ENOUGH_MONEY() {
			return makeMessage("There is not enough money.");
		}
		public static String GOV_TOO_STRONG(InspiredGov gov) {
			return makeMessage("The "+gov.getTypeName()+", " +gov.getName()+", is in the way.");
		}
		public static String CLAIM_OUT_OF_BOUNDS(InspiredGov gov) {
			return makeMessage("Your claim goes outside of the " + gov.getTypeName() +", " + gov.getName() + ".");
		}
		public static String NEGATIVE_AMOUNTS_NOT_ALLOWED(BigDecimal useInstead) {
			return makeMessage("You can't use negative values here. Use " + useInstead.abs() + " instead.");
		}
		public static String CUBOID_NOT_FULLY_SELECTED() {
			return makeMessage("You have not selected both points of the cuboid.");
		}
		public static String POINTS_IN_DIFFERENT_WORLDS() {
			return makeMessage("Your selected points were in different worlds.");
		}
		public static String POLYGON_NOT_SIMPLE_SHAPE() {
			return makeMessage("The polygon you selected is not simple. This means that some of the sides"
					+ " cross. Make sure you select each corner in order.");
		}
		private static final String getTypeName(Class<? extends InspiredGov> gov) {
			String GovName = "";
			GovName = GovFactory.getGovInstance(gov).getTypeName();
			return GovName;
		}
		private static final String makeMessage(Object msg) {
			return "\n" + TextColor.ERROR + msg.toString();
		}
	}
}
