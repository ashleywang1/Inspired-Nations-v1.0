package com.github.InspiredOne.InspiredNations.Economy.Implem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Economy.Sellable;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;
import com.github.InspiredOne.InspiredNations.Exceptions.NoShopRegionException;
import com.github.InspiredOne.InspiredNations.Governments.Implem.ChestShop;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;

public class ItemSellable implements Sellable, Nameable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2447803453590052464L;
	private Map<String, Object> saveData;
	ChestShop shop;
	//private String saveData;
	
	public ItemSellable(ItemStack item, ChestShop shop) {
		saveData = item.serialize();
		this.shop = shop;
	}
	
	public ItemStack getItem() {
		return ItemStack.deserialize(saveData);
	}

	@Override
	public String getName() {
		String name = this.getItem().getType().name().toLowerCase();
		name = name.concat(" (" + this.getItem().getData().getItemType().name() + ")");
		if(this.getItem().getItemMeta().hasEnchants()) {
			for(Enchantment ench:this.getItem().getItemMeta().getEnchants().keySet()) {
				name = name.concat("*" + ench.getName() + " " + this.getItem().getItemMeta().getEnchantLevel(ench) + "*");
			}
		}
		return name;
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
		int sold = this.getItem().getAmount();
		for(ItemStack item:this.shop.getInvetorySellables()) {
			if(this.getItem().isSimilar(item)) {
				if(item.getAmount() >= sold) {
					item.setAmount(item.getAmount() - sold);
					sold = 0;
				}
				else {
					sold = sold - item.getAmount();
					item.setAmount(0);
				}
			}
			if(sold == 0) break;
		}
		ItemStack transfer = getItem();
		transfer.setAmount(transfer.getAmount() - sold);
		try {
			playerTo.getPDI().getAccounts().transferMoney(
					this.getPrice(Currency.DEFAULT).multiply(new BigDecimal(((double) transfer.getAmount())/((double) this.getItem().getAmount())))
					, Currency.DEFAULT, this.shop.getAccounts());
		} catch (BalanceOutOfBoundsException | NegativeMoneyTransferException e) {
			playerTo.getPDI().getMsg().receiveError(MenuError.NOT_ENOUGH_MONEY());
			
			e.printStackTrace();
		}
		playerTo.getPDI().getPlayer().getWorld().dropItem(playerTo.getPDI().getPlayer().getLocation(), transfer);
		
	}

	@Override
	public boolean isForSale() {
		try {
			for(ItemStack stack:shop.getInventory()) {
				Debug.print("Stack Is Null: " + (stack == null));
				if(stack != null) {
					ItemSellable itemtemp = new ItemSellable(stack, shop);
					if(itemtemp.equals(this)) {
						return true;
					}
				}
			} 
		} catch (NoShopRegionException e) {
			return false;
		}
		return false;
	}

	@Override
	public Point3D getLocation() {
		
		return null;
	}

	@Override
	public BigDecimal getPrice(Currency curren) {
		return null;
	}
	
	@Override
	public boolean equals(Object e) {
		try {
		return this.getItem().isSimilar(((ItemSellable) e).getItem());
		}
		catch (Exception ex) {
			return false;
		}
	}

}
