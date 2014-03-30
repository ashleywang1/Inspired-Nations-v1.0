package com.github.InspiredOne.InspiredNations;


public class Debug {

	private static boolean report = true;
	private static String InformPluginDev = "If you see this, tell plugin developer";
	public static void print(Object msg) {
		InspiredNations.plugin.logger.info(msg.toString());
		
	}
	
	public static void print(int i) {
		InspiredNations.plugin.logger.info("Debuger check: " + i);
	}
	
	public static void InformPluginDev() {
		InspiredNations.plugin.logger.info(InformPluginDev);
	}
	
	
}
