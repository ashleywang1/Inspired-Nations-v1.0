package com.github.InspiredOne.InspiredNations.Economy.Implem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

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
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
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
	
	public void setAmount(int amount) {
		ItemStack stack = this.getItem();
		stack.setAmount(amount);
		saveData=new CardboardBox(stack);
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
			return this.getName() + " " + TextColor.VALUE + this.getPrice(viewer.getCurrency(), viewer.getLocation()) + " " +
		TextColor.UNIT + viewer.getCurrency() + ":" + this.getItem().getAmount();
		}
		else {
			return this.getName();
		}
	}

	@Override
	public void transferOwnership(Buyer buyer, Payable account) {
		int sold = this.getItem().getAmount();
		ArrayList<ItemStack> selling = new ArrayList<ItemStack>();
		for(ItemStack item:this.shop.getInvetorySellables()) {
			if(this.getItem().isSimilar(item)) {
				selling.add(item);
				if(item.getAmount() >= sold) {
					sold = 0;
				}
				else {
					sold = sold - item.getAmount();
				}
			}
			if(sold == 0) break;
		}
		ItemStack transfer = getItem().clone();

		transfer.setAmount(transfer.getAmount() - sold);
		try {
			account.transferMoney(
					this.getPrice(this.curren, buyer.getLocation()).multiply(new BigDecimal(((double) transfer.getAmount())/((double) this.getItem().getAmount())))
					, this.curren, this.shop.getAccounts());
			((ItemBuyer) buyer).recieveItem(transfer);
			try {
				shop.getInventory().removeItem(transfer);
			} catch (NoShopRegionException e) {
				e.printStackTrace();
			}
		} catch (BalanceOutOfBoundsException | NegativeMoneyTransferException e) {
			if(buyer instanceof PlayerData) {
				((PlayerData) buyer).getMsg().receiveError(MenuError.NOT_ENOUGH_MONEY());
			}
			
		}
	}
	
	public void transferOwnership(PlayerData PDI) {
		this.transferOwnership(PDI, PDI);
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
	public BigDecimal getPrice(Currency curren, Location locto) {
		return InspiredNations.Exchange.getTransferValue(price, this.curren, curren, InspiredNations.Exchange.mcup);
		
	}
	
	public BigDecimal getUnitPrice(Currency curren, Location locto) {
		//Debug.print("Inside getUnitPrice" + this.getPrice(curren, locto).divide(new BigDecimal(this.getItem().getAmount())));
		return this.getPrice(curren, locto).divide(new BigDecimal(this.getItem().getAmount()), InspiredNations.Exchange.mcup);
	}
	
	public BigDecimal getTransCost(Currency curren, Location locto) {
		//TODO think about the transportation cost function.
		double dist = locto.distance(shop.getRegion().getRegion().getCharacteristicPoint());
		
		return InspiredNations.Exchange.getTransferValue((new BigDecimal(dist)).divide((new BigDecimal(100))),
				Currency.DEFAULT,curren, InspiredNations.Exchange.mcup);
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
