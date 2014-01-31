package com.github.InspiredOne.InspiredNations;


public class Debug {

	public static boolean report = true;
	public static String InformPluginDev = "If you see this, tell plugin developer";
	public static void print(Object msg) {
		InspiredNations.plugin.logger.info(msg.toString());
		
	}
}
