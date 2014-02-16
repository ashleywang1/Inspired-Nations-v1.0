package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.inventory.DoubleChestInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Implem.ItemSellable;
import com.github.InspiredOne.InspiredNations.Exceptions.NoShopRegionException;
import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov.ManageItemsForSale;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;
import com.github.InspiredOne.InspiredNations.Regions.Implem.ShopLand;
import com.github.InspiredOne.InspiredNations.Regions.Implem.ShopRegion;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;

public class ChestShop extends Facility {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2528500041859238661L;
	
	private List<ItemSellable> items = new ArrayList<ItemSellable>();

	public ChestShop() {
	}

	@Override
	public boolean isUnique() {
		return false;
	}

	public Inventory getInventory() throws NoShopRegionException {
		ShopRegion region = ((ShopRegion) this.getRegion().getRegion());
		Point3D chest = region.one;
		Inventory inv = null;
		if(region.volume() >= 1) {
			try {
				inv = (Inventory) ((Chest) chest.getLocation().getBlock().getState()).getInventory();
			}
			catch (Exception e) {
				e.printStackTrace();
				throw new NoShopRegionException();
			}
		}
		else {
			throw new NoShopRegionException();
		}
		return inv;
	}
	
	public List<ItemStack> getInvetorySellables() {
		List<ItemStack> output = new ArrayList<ItemStack>();
		try {
			for(ItemStack stack:this.getInventory()) {
				if(stack != null) {
					output.add(stack);
				}
			}
			return output;
		} catch (NoShopRegionException e) {
			e.printStackTrace();
			return output;
		}
	}
	
	@Override
	public Class<? extends InspiredRegion> getInspiredRegion() {
		return ShopLand.class;
	}

	@Override
	public Class<? extends InspiredGov> getCommonEcon() {
		return Country.class;
	}

	@Override
	public List<Class<? extends Facility>> getGovFacilities() {
		List<Class<? extends Facility>> output = new ArrayList<Class<? extends Facility>>();
		return output;
	}

	@Override
	public List<Class<? extends InspiredGov>> getSelfGovs() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		output.add(ChestShop.class);
		return output;
	}

	@Override
	public String getTypeName() {
		return "Chest Shop";
	}

	@Override
	public String getFacilityGroupName() {
		return "";
	}

	@Override
	public Class<? extends OwnerGov> getSuperGov() {
		return GoodBusiness.class;
	}

	@Override
	public void setFunctionOptions(PlayerData PDI, OptionMenu menu) {
		if(this.getRegion().getRegion() instanceof ShopRegion) {
			menu.getOptions().add(new PromptOption(menu, "Manage Shop Inventory",new ManageItemsForSale(PDI, menu, this)));
		}
	}

	public List<ItemSellable> getItems() {
		return items;
	}

	public void setItems(List<ItemSellable> items) {
		this.items = items;
	}
}
