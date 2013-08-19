package com.github.InspiredOne.InspiredNations.ToolBox;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;

public class Tools {
	
	InspiredNations plugin;
	
	public Tools(InspiredNations instance) {
		this.plugin = instance;
	}
	public static BigDecimal cut(BigDecimal input) {
		return input.divide(BigDecimal.ONE, 2, BigDecimal.ROUND_HALF_DOWN);
	}
	
	public enum TextColor {
		HEADER(ChatColor.GOLD + "" + ChatColor.BOLD),
		SUBHEADER(ChatColor.YELLOW + "" + ChatColor.ITALIC + "" + ChatColor.BOLD),
		LABEL(ChatColor.RED + ""),
		VALUE(ChatColor.GOLD + ""),
		VALUEDESCRI(ChatColor.YELLOW + ""),
		DIVIDER(ChatColor.DARK_AQUA + ""),
		
		OPTION(ChatColor.GREEN + ""),
		OPTIONNUMBER(ChatColor.GOLD + ""),
		OPTIONDESCRIP(ChatColor.GRAY + ""),
		UNAVAILABLE(ChatColor.DARK_GRAY + ""),
		UNAVAILREASON(ChatColor.GRAY + ""),
		INSTRUCTION(ChatColor.YELLOW + ""),
		ALERT(ChatColor.RED + ""),
		UNIT(ChatColor.YELLOW + ""),
		ENDINSTRU(ChatColor.AQUA + "");
		
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
	public static String drawMap(InspiredNations plugin, PlayerData PDI, int res, Class<? extends InspiredGov> gov) {
		
		String output = "";
		Location location = PDI.getPlayer(plugin).getLocation();

		
		HashMap<InspiredGov, ChatColor> superGov = new HashMap<InspiredGov, ChatColor>();
		HashMap<InspiredGov, String> subGov = new HashMap<InspiredGov, String>();
		
		int superIter = 0;// for iterating through superGov color choices
		ChatColor[] superColors = {ChatColor.GREEN, ChatColor.YELLOW, ChatColor.GOLD, ChatColor.RED, ChatColor.DARK_RED, ChatColor.DARK_GREEN, ChatColor.WHITE,
				ChatColor.AQUA, ChatColor.DARK_BLUE, ChatColor.DARK_AQUA, ChatColor.DARK_PURPLE, ChatColor.LIGHT_PURPLE};
		int subIter = 0;// for iterating through subGov Char choices
		String[] subChars = {"#", "$", "%", "&", "S"};

		ChatColor color = ChatColor.GRAY;
		String character = "/";
		
		int above = 9;
		int below = 4;
		
		int[] remove = {160*above+240,160*above+77,160*above-80,160*above+83};
		
		for(int z = -above;z < below; z++) {
			Location loctest = location.clone();
			loctest.setZ(location.getBlockZ() + z*res);
			for (int x = -26; x < 27; x++) {
				character = "/";
				loctest.setX(location.getBlockX() + x*res);
				
				//Loop through the superGovs to see if any of them contain loctest
				for(Iterator<InspiredGov> iter1 = plugin.regiondata.get(gov).iterator(); iter1.hasNext();) {
					InspiredGov govtest = iter1.next();
					if(govtest.contains(loctest)) {
						if(!superGov.containsKey(govtest)) {
							superGov.put(govtest, superColors[superIter]);
							superIter++;
							if(superIter == superColors.length) {
								superIter = 0;
							}
						}
						//Color is know, so append that to map string
						color = superGov.get(govtest);
						
						//Loop through the subGovs to see if any of them contain loctest
						for(Class<? extends InspiredGov> subgovclass: govtest.getSubGovs()) {
							for(Iterator<InspiredGov> iter2 = plugin.regiondata.get(subgovclass).iterator(); iter2.hasNext();) {
								InspiredGov subtest = iter2.next();
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
						}
						break;
					}
				}
				//Check if self
				if(x == 0 && z == 0) {
					character = "@";
				}
				output = output.concat(color + character);
			}
			output = output.concat("\n");
		}
		
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
		
		//Key
		for(InspiredGov key:superGov.keySet()) {
			output = output.concat(superGov.get(key) + key.getName() + ", ");
		}
		for(InspiredGov key:subGov.keySet()) {
			output = output.concat(superGov.get(key.getSuperGovObj()) + subGov.get(key) + "=" + key.getName() + ", ");
		}
		output = output.concat(ChatColor.GRAY + "/ = Unclaimed Land, @ = You.\n");
		
		return output;
		
	}
}