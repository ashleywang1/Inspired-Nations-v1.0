package com.github.InspiredOne.InspiredNations.ToolBox;

import java.math.BigDecimal;

import org.bukkit.ChatColor;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerSubjectGov;
import com.github.InspiredOne.InspiredNations.Regions.Region;
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
	public static String addDivider(String text, PlayerData receiver) {
		return text.concat(TextColor.DIVIDER(receiver) + Tools.repeat("-", 53) + "\n" + ChatColor.RESET);
	}
	/**
	 * Adds a line that shows the value of the player's wallet.
	 * @param text
	 * @return
	 */
	public static String oneLineWallet(String text, PlayerData PDI, Payable account) {
		String output = text.concat(TextColor.LABEL(PDI) + "Holdings: " + TextColor.VALUE(PDI) +
				Tools.cut(account.getTotalMoney(PDI.getCurrency(), InspiredNations.Exchange.mcdown)) + TextColor.UNIT(PDI) +" " + PDI.getCurrency() + "\n");
		return output;
	}
	/**
	 * Builds the header for menus. Adds the <code>ChatColor</code> character to the beginning
	 * and clears it afterward.
	 * @param msg	the <code>String</code> to be used in the header
	 * @return		the <code>String</code> processed to be in the menu
	 */
	public static String header(String msg, PlayerData receiver) {
		return addDivider(TextColor.HEADER(receiver) + msg + "\n" + ChatColor.RESET, (receiver));
	}

	public class ContextData {
		public static final String Error = "Error";
		public static final String Alert = "Alert";
	//	public static final String PromptData = "PromptData";
	}
	
	public enum OptionUnavail {
		NOT_UNAVAILABLE(""),
		NOBODY_TO_SHARE_WITH("No governments or people");
		
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
		public static Alert NO_ALERT() {
			
			return new Alert() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 3618500001516080580L;

				@Override
				public String getMessage(PlayerData receiver) {
					return "";
				}

				@Override
				public boolean menuPersistent() {
					// TODO Auto-generated method stub
					return false;
				}
				
			};
			
		}
		public static Alert CANNOT_INTERACT(final InspiredGov attacked) {
			return new Alert() {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = -2499830621442259952L;

				@Override
				public String getMessage(PlayerData receiver) {
					return makeMessage("You cannot interact in " + attacked.getDisplayName(receiver) 
							+ ". Their protection is too high.", (receiver));
				}

				@Override
				public boolean menuPersistent() {
					// TODO Auto-generated method stub
					return false;
				}
			};
		}
		public static Alert LOST_OWNERSHIP(final OwnerGov govlost) {
			return new Alert() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 6630552666164107410L;

				@Override
				public String getMessage(PlayerData receiver) {
					return makeMessage("You are no longer " + govlost.getOwnerPositionName() + " of " + govlost.getName(), (receiver));
				}

				@Override
				public boolean menuPersistent() {
					// TODO Auto-generated method stub
					return false;
				}
				
			};
		}
		public static Alert LOST_CITIZENSHIP(final OwnerSubjectGov govlost) {
			return new Alert() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 5356796387575937695L;

				@Override
				public String getMessage(PlayerData receiver) {
					return makeMessage("You are no longer " + govlost.getSubjectPositionName()
							+ " of " + govlost.getName() + ".", (receiver));
				}

				@Override
				public boolean menuPersistent() {
					// TODO Auto-generated method stub
					return false;
				}
				
			};
		}
		public static Alert MESSAGE_ALERT(final String msg) {
			return new Alert() {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 2410593640115800320L;

				@Override
				public String getMessage(PlayerData reciever) {
					return makeMessage(msg, (reciever));
				}

				@Override
				public boolean menuPersistent() {
					// TODO Auto-generated method stub
					return false;
				}
			};
		}
		public static Alert REGION_UPDATED_SUCCESSFULY(final Region region, final InspiredGov gov) {
			return new Alert() {

				/**
				 * 
				 */
				private static final long serialVersionUID = -8470406903932530715L;

				@Override
				public String getMessage(PlayerData receiver) {
					return makeMessage(region.getTypeName() + " of " + gov.getDisplayName(receiver) + " successfully modified.", (receiver));
				}

				@Override
				public boolean menuPersistent() {
					// TODO Auto-generated method stub
					return false;
				}
				
			};
		}
		public static Alert RECEIVED_MONEY(final BigDecimal amount, final Currency curren, final Nameable sender) {
			return new Alert() {

				/**
				 * 
				 */
				private static final long serialVersionUID = -8611525947131654856L;

				@Override
				public String getMessage(PlayerData receiver) {
					BigDecimal converted = Tools.cut(InspiredNations.Exchange.getExchangeValue(amount, curren, receiver.getCurrency()));
					return makeMessage(sender.getDisplayName(receiver) + makeMessage(" paid you " +TextColor.VALUE(receiver)+ converted + " "
							+ TextColor.UNIT(receiver) + receiver.getCurrency() + ".",(receiver)), (receiver));
				}

				@Override
				public boolean menuPersistent() {
					// TODO Auto-generated method stub
					return false;
				}
				
			};
		}
		public static Alert TRANSFER_SUCCESSFUL(final BigDecimal amount, final Currency curren, final Nameable sender, final Nameable paid) {
			return new Alert() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 8894522114770201993L;

				@Override
				public String getMessage(PlayerData receiver) {
					BigDecimal converted = Tools.cut(InspiredNations.Exchange.getExchangeValue(amount, curren, receiver.getCurrency()));
					return makeMessage(sender.getDisplayName(receiver) + " paid " + TextColor.VALUE(receiver) + converted +
							" " + TextColor.UNIT(receiver) + receiver.getCurrency() + TextColor.ALERT(receiver) + " to " + paid.getDisplayName(receiver), (receiver));
				}

				@Override
				public boolean menuPersistent() {
					// TODO Auto-generated method stub
					return false;
				}
			};
		}
		
		public static Alert GOV_MADE_SUCCESSFULLY(final InspiredGov gov) {
			return new Alert() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public String getMessage(PlayerData receiver) {
					return makeMessage(gov.getTypeName() + " created successfully.", receiver);
				}

				@Override
				public boolean menuPersistent() {
					return true;
				}
			};
		}
		
		public static String makeMessage(Object input, PlayerData reciever) {
			return TextColor.ALERT(reciever) + input.toString();
		}

	}
	
	public static class MenuError {
		
		public static String NO_ERROR() {
			return "";
		}
		public static String HELP_PAGE_NOT_AVAILABLE(int maxPages, PlayerData PDI) {
			return makeMessage("There are only " + maxPages + " pages to this help document. Inputs must be positive.", PDI);
		}
		public static String ACCOUNT_ALREADY_LINKED(PlayerData PDI) {
			return makeMessage("The account is already linked.", PDI);
		}
		public static String INVALID_NUMBER_INPUT(PlayerData PDI) {
			return makeMessage("Your entry must be a number.", PDI);
		}
		public static String OUT_OF_RANGE_NUMBER_INPUT(PlayerData PDI) {
			return makeMessage("That is not an option.", PDI);
		}
		public static String NOT_AN_OPTION(PlayerData PDI) {
			return makeMessage("That is not an option.", PDI);
		}
		public static String NAME_ALREADY_TAKEN(Class<? extends InspiredGov> gov, PlayerData PDI) {
			
			String GovName = getTypeName(gov);
			return makeMessage("That " + GovName + " name is already taken.", PDI);
		}
		public static String MONEY_NAME_ALREADY_TAKEN(PlayerData PDI) {
			return makeMessage("That currency name is already in use.", PDI);
		}
		public static String MONEY_MULTIPLYER_TOO_LARGE(PlayerData PDI) {
			return makeMessage("Your currency is too inflated.", PDI);
		}
		public static String MONEY_MULTIPLYER_TOO_SMALL(PlayerData PDI) {
			return makeMessage("Your currency is too valuable.", PDI);
		}
		public static String ACCOUNT_NAME_ALREADY_TAKEN(PlayerData PDI) {
			return makeMessage("That account name is already in use.", PDI);
		}
		public static String NO_MATCHES_FOUND(PlayerData PDI) {
			return makeMessage("There are no matches found.", PDI);
		}
		public static String NO_SUB_GOVS_UNDER_THIS_GOV(PlayerData PDI) {
			return makeMessage("There are no governments under the control of this government.", PDI);
		}
		public static String NOT_ENOUGH_MONEY(PlayerData PDI) {
			return makeMessage("There is not enough money.", PDI);
		}
		public static String GOV_TOO_STRONG(InspiredGov gov, PlayerData PDI) {
			return makeMessage("The "+gov.getTypeName()+", " +gov.getName()+", is in the way.", PDI);
		}
		public static String CLAIM_OUT_OF_BOUNDS(InspiredGov gov, PlayerData PDI) {
			return makeMessage("Your claim goes outside of the " + gov.getTypeName() +", " + gov.getName() + ".", PDI);
		}
		public static String NEGATIVE_AMOUNTS_NOT_ALLOWED(BigDecimal useInstead, PlayerData PDI) {
			return makeMessage("You can't use negative values here. Use " + useInstead.abs() + " instead.", PDI);
		}
		public static String CUBOID_NOT_FULLY_SELECTED(PlayerData PDI) {
			return makeMessage("You have not selected both points of the cuboid.", PDI);
		}
		public static String POINTS_IN_DIFFERENT_WORLDS(PlayerData PDI) {
			return makeMessage("Your selected points were in different worlds.", PDI);
		}
		public static String POLYGON_NOT_SIMPLE_SHAPE(PlayerData PDI) {
			return makeMessage("The polygon you selected is not simple. This means that some of the sides"
					+ " cross. Make sure you select each corner in order.", PDI);
		}
		public static String SELECTION_MUST_BE_CHEST(PlayerData PDI) {
			return makeMessage("You may only select chests for your shop.", PDI);
		}
		public static String NEGATIVE_PROTECTION_LEVEL_NOT_ALLOWED(PlayerData PDI) {
			return makeMessage("Negative numbers are not allowed for protection levels.", PDI);
		}
		public static String NEGATIVE_MILITARY_LEVEL_NOT_ALLOWED(PlayerData PDI) {
			return makeMessage("Negative numbers are not allowed for military levels.", PDI);
		}
		public static String EMPTY_INPUT(PlayerData PDI) {
			return makeMessage("Your input was blank.", PDI);
		}
		public static String ACCOUNT_ALREADY_HAS_THAT_CURRENCY(PlayerData PDI) {
			return makeMessage("The account already has that currency.", PDI);
		}
		public static String ACCOUNT_COLLECTION_NOT_LINKED(PlayerData PDI) {
			return makeMessage("This account is not linked to any other accounts.", PDI);
		}
		private static final String getTypeName(Class<? extends InspiredGov> gov) {
			String GovName = "";
			GovName = GovFactory.getGovInstance(gov).getTypeName();
			return GovName;
		}
		private static final String makeMessage(Object msg, PlayerData PDI) {
			return "\n" + TextColor.ERROR(PDI) + msg.toString();
		}
	}
}
