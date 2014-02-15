package com.github.InspiredOne.InspiredNations.Economy;

import java.io.Serializable;
import java.math.BigDecimal;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;
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
		return this.getItem().getType().name();
	}

	@Override
	public void setName(String name) {

	}

	@Override
	public String getDisplayName(PlayerData viewer) {
		return this.getName();
	}

	@Override
	public void transferOwnership(PlayerID playerTo) {
		this.getItem().setAmount(3);
	}

	@Override
	public boolean isForSale() {
		// TODO Auto-generated methoDd stub
		return false;
	}

	@Override
	public Point3D getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getPrice(Currency curren) {
		// TODO Auto-generated method stub
		return null;
	}

}
