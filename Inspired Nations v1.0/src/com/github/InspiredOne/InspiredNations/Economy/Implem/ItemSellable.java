package com.github.InspiredOne.InspiredNations.Economy.Implem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

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
import com.github.InspiredOne.InspiredNations.ToolBox.CardboardBox;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;

public class ItemSellable implements Sellable, Nameable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2447803453590052464L;
	private CardboardBox saveData;
	ChestShop shop;
	private BigDecimal price = BigDecimal.ZERO;
	private Currency curren = Currency.DEFAULT;
	//private String saveData;
	
	public ItemSellable(ItemStack item, ChestShop shop) {
		saveData = new CardboardBox(item);
		this.shop = shop;
	}
	
	public ItemStack getItem() {
		return saveData.unbox();
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
		if(this.isForSale()) {
			return this.getName() + " " + TextColor.VALUE + this.getPrice(viewer.getCurrency()) + " " +
		TextColor.UNIT + viewer.getCurrency();
		}
		else {
			return this.getName();
		}
	}

	@Override
	public void transferOwnership(PlayerID playerTo) {
		int sold = this.getItem().getAmount();
		HashMap<ItemStack,Integer> selling = new HashMap<ItemStack, Integer>();
		for(ItemStack item:this.shop.getInvetorySellables()) {
			if(this.getItem().isSimilar(item)) {
				if(item.getAmount() >= sold) {
					selling.put(item, item.getAmount() - sold);
					sold = 0;
				}
				else {
					sold = sold - item.getAmount();
					selling.put(item, 0);
				}
			}
			if(sold == 0) break;
		}
		ItemStack transfer = getItem().clone();
		Debug.print("Amount " + transfer.getAmount() + " How many not bought: " + sold);
		transfer.setAmount(transfer.getAmount() - sold);
		try {
			playerTo.getPDI().getAccounts().transferMoney(
					this.getPrice(Currency.DEFAULT).multiply(new BigDecimal(((double) transfer.getAmount())/((double) this.getItem().getAmount())))
					, Currency.DEFAULT, this.shop.getAccounts());
			playerTo.getPDI().getPlayer().getWorld().dropItemNaturally(playerTo.getPDI().getPlayer().getLocation(), transfer);
			for(ItemStack item:selling.keySet()) {
				item.setAmount(selling.get(item));
			}
		} catch (BalanceOutOfBoundsException | NegativeMoneyTransferException e) {
			playerTo.getPDI().getMsg().receiveError(MenuError.NOT_ENOUGH_MONEY());
			
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public boolean isForSale() {
		if(this.shop.getItems().contains(this)) {
			try {
				for(ItemStack stack:shop.getInventory()) {
					if(stack != null) {
						ItemSellable itemtemp = new ItemSellable(stack, shop);
						if(itemtemp.equals(this)) {
								return true;
						}
					}
				}
				return false;
			} 
			catch (NoShopRegionException e) {
				return false;
			}
		}
		else return false;
	}

	@Override
	public Point3D getLocation() {
		
		return null;
	}

	@Override
	public BigDecimal getPrice(Currency curren) {
		return InspiredNations.Exchange.getTransferValue(price, this.curren, curren, InspiredNations.Exchange.mcup);
		
	}
	
	public void setPrice(BigDecimal mon, Currency monType) {
		this.price = mon;
		this.curren = monType;
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
