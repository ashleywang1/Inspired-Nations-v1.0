
package com.github.InspiredOne.InspiredNations;

import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.InspiredOne.InspiredNations.Governments.GlobalGov;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.Implem.Country;
import com.github.InspiredOne.InspiredNations.ToolBox.MultiMap;

public class InspiredNations extends JavaPlugin {

	public Logger logger = Logger.getLogger("Minecraft"); // Variable to communicate with console
	private StartStop SS = new StartStop(this); // Deals with start-up and shut-down
	public MultiMap<Class<? extends InspiredGov>, InspiredGov> regiondata = new MultiMap<Class<? extends InspiredGov>, InspiredGov>(); 
	public HashMap<String, PlayerData> playerdata = new HashMap<String, PlayerData>();
	public GlobalGov global = new GlobalGov(this);
	
	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		this.regiondata.get(Country.class);
		SS.Start();
	}
	
	public void onDisable() {
		SS.Stop();
	}
	
	public class TempCommandListener implements CommandExecutor {

		@Override
		public boolean onCommand(CommandSender arg0, Command arg1, String arg2,
				String[] arg3) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
}
