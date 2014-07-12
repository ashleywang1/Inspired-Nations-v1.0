package com.github.InspiredOne.InspiredNations.ToolBox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.PlayerOfflineException;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
/**
 * Includes Text Style and color enums and a fully generalized ascii Map drawer.
 * @author Jedidiah Phillips
 *
 */
public class Tools {
	
	InspiredNations plugin;
	
	public Tools(InspiredNations instance) {
		this.plugin = instance;
	}
	
	public static List<InspiredGov> getGovsThatContain(Location loc) {
		List<InspiredGov> output = new ArrayList<InspiredGov>();
		
		for(InspiredGov gov:InspiredNations.regiondata) {
			if(gov.contains(loc)) {
				output.add(gov);
				Debug.print(gov.getName());
			}
		}
		return output;
	}
	
	public static <T> T getInstance(Class<T> gov) {
		try {
			return gov.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 *  A method to cut off decimals greater than the hundredth place;
	 * @param input
	 * @return
	 */
	public static BigDecimal cut(BigDecimal input) {
		return input.divide(BigDecimal.ONE, 2, BigDecimal.ROUND_DOWN);
	}
	public static <T extends Nameable> List<T> filter(String key, List<T> fulllist) {
		List<T> output = new ArrayList<T>();
		
		for(T test:fulllist) {
			if(test.getName().toLowerCase().contains(key.toLowerCase())) {
				output.add(test);
			}
		}
		return output;
	}
	
	public static class TextColor {
		/**
		 * Used exclusively for the menu header.
		 */

		public static String HEADER(PlayerData PDI) {
			//ChatColor.BLUE + "" + ChatColor.BOLD
			return PDI.theme.HEADER();
		}
		/**
		 * Subheader
		 */


		public static String SUBHEADER(PlayerData PDI) {
			//ChatColor.YELLOW + "" + ChatColor.ITALIC + "" + ChatColor.BOLD
			return PDI.theme.SUBHEADER();
		}
		/**
		 * Used to say what the proceeding value is.
		 */

		public static String LABEL(PlayerData PDI) {
			//ChatColor.RED + ""
			return PDI.theme.LABEL();
		}
		/**
		 * Anything that is calculated information displayed for the viewer 
		 */
		public static String VALUE(PlayerData PDI) {
			//ChatColor.GOLD + ""
			return PDI.theme.VALUE();
		}
		/**
		 * Additional text describing the value
		 */
		public static String VALUEDESCRI(PlayerData PDI) {
			//ChatColor.RED + ""
			return PDI.theme.VALUEDESCRI();
		}
		/**
		 * Used for the divider between sections of the HUD
		 */
		public static String DIVIDER(PlayerData PDI){
			//ChatColor.DARK_AQUA + ""
			return PDI.theme.DIVIDER();
		}
		/**
		 * The color of the options
		 */
		public static String OPTION(PlayerData PDI) {
			//ChatColor.DARK_GREEN + ""
			return PDI.theme.OPTION();
		}
		/**
		 * The color of the number correlating to the option
		 */
		public static String OPTIONNUMBER(PlayerData PDI) {
			//ChatColor.YELLOW + ""
			return PDI.theme.OPTIONNUMBER();
		}
		/**
		 * Used for text that describes an option
		 */
		public static String OPTIONDESCRIP(PlayerData PDI) {
			//ChatColor.GRAY + ""
			return PDI.theme.OPTIONDESCRI();
		}
		/**
		 * Color to be used for an unavailable option
		 */
		public static String UNAVAILABLE(PlayerData PDI) {
			//ChatColor.DARK_GRAY + ""
			return PDI.theme.UNAVAILABLE();
		}
		/**
		 * Color of text next to unavailable option describing why it's unavailable
		 */
		public static String UNAVAILREASON(PlayerData PDI) {
			//ChatColor.GRAY + ""
			return PDI.theme.UNAVAILREASON();
		}
		/**
		 * Used to give instructions
		 */
		public static String INSTRUCTION(PlayerData PDI) {
			//ChatColor.YELLOW + ""
			return PDI.theme.INSTRUCTION();
		}
		/**
		 * Used for error messages through out the plugin
		 */
		public static String ERROR(PlayerData PDI) {
			//ChatColor.RED + ""
			return PDI.theme.ERROR();
		}
		/**
		 * Used for alert messages through out the plugin
		 */
		public static String ALERT(PlayerData PDI) {
			//ChatColor.YELLOW + ""
			return PDI.theme.ALERT();
		}
		/**
		 * Used for all units mentioned in the HUD
		 */
		public static String UNIT(PlayerData PDI) {
			//ChatColor.YELLOW + ""
			return PDI.theme.UNIT();
		}
		/**
		 * Used for the line of text below the HUD
		 */
		public static String ENDINSTRU(PlayerData PDI) {
			//ChatColor.AQUA + ""
			return PDI.theme.ENDINSTRUCT();
		}
		/**
		 * Used for Enemy Nations and players
		 */
		public static String ENEMY(PlayerData PDI) {
			//ChatColor.AQUA + ""
			return PDI.theme.ENEMY();
		}
		/**
		 * Used for Neutral Nations and players
		 */
		public static String NEUTRAL(PlayerData PDI) {
			//ChatColor.AQUA + ""
			return PDI.theme.NEUTRAL();
		}
		/**
		 * Used for Ally Nations and players
		 */
		public static String ALLY(PlayerData PDI) {
			//ChatColor.AQUA + ""
			return PDI.theme.ALLY();
		}
		
		private String color;
		
        private TextColor(String color) {
                this.color = color;
        }
        @Override
        public String toString() {
        	return color;
        }
	}
	

	
	// A method to simply repeat a string
	public static String repeat(String entry, int multiple) {
		String temp = "";
		for (int i = 0; i < multiple; i++) {
			temp = temp.concat(entry);
		}
		return temp;
	}
	
	/**
	 * Draws a ascii map for the player to view. The map shows the gov
	 * and the gov below that. The gov is encoded with a color while the
	 * subgov is encoded with a symbol.
	 * @param plugin	the <code>InspiredNations</code> plugin
	 * @param PDI		the <code>PlayerData</code> of the player using the map
	 * @param res		the length of one pixel side in blocks
	 * @param gov		the highest government that will be shown on the map
	 * @return			the <code>String</code> that includes the map
	 */
	public static String drawMap(PlayerData PDI, int res, int tier, int size) {
		
		String output = "";
		Location location = null;
		try {
			location = PDI.getPlayer().getLocation();
		} catch (PlayerOfflineException e) {
			e.printStackTrace();
		}

		
		HashMap<InspiredGov, ChatColor> superGov = new HashMap<InspiredGov, ChatColor>();
		HashMap<InspiredGov, String> subGov = new HashMap<InspiredGov, String>();
		
		int superIter = 0;// for iterating through superGov color choices
		ChatColor[] superColors = {ChatColor.GREEN, ChatColor.RED, ChatColor.DARK_BLUE, ChatColor.DARK_GREEN, ChatColor.DARK_RED, ChatColor.GOLD, ChatColor.WHITE,
				ChatColor.AQUA, ChatColor.YELLOW, ChatColor.DARK_AQUA, ChatColor.DARK_PURPLE, ChatColor.LIGHT_PURPLE};
		int subIter = 0;// for iterating through subGov Char choices
		String[] subChars = {"#", "$", "%", "&", "S", "~", "=", "+", "B", "T", "G"};

		ChatColor colorInit = ChatColor.DARK_GRAY;
		String characterInit = "/";
		ChatColor selfcolor = ChatColor.GRAY;
		String selfchar = ">";
		
		int above = size;
		int below = size + 1;
		
		//int[] remove = {160*above+240,160*above+77,160*above-80,160*above+83};
		
		for(int z = -above;z < below; z++) {
			Location loctest = location.clone();
			loctest.setZ(location.getBlockZ() + z*res);
			for (int x = -26; x < 27; x++) {
				String character = characterInit;
				ChatColor color = colorInit;
				loctest.setX(location.getBlockX() + x*res);
				
				//Check if self
				if(x == 0 && z == 0) {
					color = selfcolor;
				}
				
				//Loop through the superGovs to see if any of them contain loctest
				for(InspiredGov govtest:InspiredNations.regiondata) {
					if(govtest.contains(loctest) && govtest.getTier()==tier) {
						if(!superGov.containsKey(govtest)) {
							superGov.put(govtest, superColors[superIter]);
							superIter++;
							if(superIter == superColors.length) {
								superIter = 0;
							}
						}
						//Color is known, so append that to map string
						color = superGov.get(govtest);
						
						//Loop through the subGovs to see if any of them contain loctest
						for(InspiredGov subtest: govtest.getAllSubGovsAndFacilitiesJustBelow()) {
							if(subtest.contains(loctest)) {
								if(!subGov.containsKey(subtest)) {
									subGov.put(subtest, subChars[subIter]);
									subIter++;
									if(subIter == subChars.length) {
										subIter = 0;
									}
								}
								character = subGov.get(subtest);
								break;
							}
						}
						break;
					}
				}
				//Check if self
				if(x == 0 && z == 0) {
					//character = selfchar;
					if ((-45 < location.getYaw() && 45 >= location.getYaw()) || (315 < location.getYaw() && 360 >= location.getYaw())
							|| (-360 < location.getYaw() && -315 >= location.getYaw())) {
						character = "V";
					}
					if ((45 < location.getYaw() && 135 >= location.getYaw()) || (-315 < location.getYaw() && -225 >= location.getYaw())) {
						character = "<";
					} 
					if ((135 < location.getYaw() && 225 >= location.getYaw()) || (-225 < location.getYaw() && -135 >= location.getYaw())) {
						character = "^";

					}
					if ((225 < location.getYaw() && 315 >= location.getYaw()) || (-135 < location.getYaw() && -45 >= location.getYaw())) {
						character = ">";
					}
					selfchar = character;
				}
				output = output.concat(color + character);
			}
			output = output.concat("\n");
		}
/*		
		// Direction Icon
		if ((-45 < location.getYaw() && 45 >= location.getYaw()) || (315 < location.getYaw() && 360 >= location.getYaw())
				|| (-360 < location.getYaw() && -315 >= location.getYaw())) {
			output = output.substring(0, remove[0]).concat("`|`").concat(output.substring(remove[0] + 1));
		}
		if ((45 < location.getYaw() && 135 >= location.getYaw()) || (-315 < location.getYaw() && -225 >= location.getYaw())) {
			output = output.substring(0, remove[1]).concat("-").concat(output.substring(remove[1] + 1));
		} 
		if ((135 < location.getYaw() && 225 >= location.getYaw()) || (-225 < location.getYaw() && -135 >= location.getYaw())) {
			output = output.substring(0, remove[2]).concat(",|,").concat(output.substring(remove[2] + 1));

		}
		if ((225 < location.getYaw() && 315 >= location.getYaw()) || (-135 < location.getYaw() && -45 >= location.getYaw())) {
			output = output.substring(0, remove[3]).concat("-").concat(output.substring(remove[3] + 1));
		}
*/
		//Key
		for(InspiredGov key:superGov.keySet()) {
			output = output.concat(superGov.get(key) + key.getName() + ", ");
		}
		for(InspiredGov key:subGov.keySet()) {
			output = output.concat(superGov.get(key.getSuperGovObj()) + subGov.get(key) + "=" + key.getName() + ", ");
		}
		output = output.concat(ChatColor.DARK_GRAY + "/" + ChatColor.GRAY + "= Unclaimed Land, "+ selfchar +" = You.\n");
		
		return output;
		
	}
}
