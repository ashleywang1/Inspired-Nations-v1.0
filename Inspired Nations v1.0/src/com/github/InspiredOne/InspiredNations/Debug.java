package com.github.InspiredOne.InspiredNations;


import org.bukkit.ChatColor;

public class Debug {

	public static boolean report = true;
	
	public static void print(String msg) {
		InspiredNations.plugin.logger.info(ChatColor.RED + msg);
	} 
	public static void print(Object msg) {
		InspiredNations.plugin.logger.info(ChatColor.RED + msg.toString());
	}
}
