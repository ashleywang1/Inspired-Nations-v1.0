package com.github.InspiredOne.InspiredNations.Economy;

import java.io.File;
import java.io.Serializable;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;
import com.github.InspiredOne.InspiredNations.ToolBox.Sellable;

public class ItemSellable implements Sellable, Nameable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2447803453590052464L;
	private String saveData;
	
	public ItemSellable(ItemStack item) {
		YamlConfiguration config = new YamlConfiguration();
		config.set("item",item);
		saveData = config.saveToString();
	}
	
	public ItemStack getItem() {
		YamlConfiguration config = new YamlConfiguration();
		config.addDefault("item", saveData);
		return config.getItemStack("item");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDisplayName(PlayerData viewer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void transferOwnership(PlayerID playerTo) {
		// TODO Auto-generated method stub

	}

}
