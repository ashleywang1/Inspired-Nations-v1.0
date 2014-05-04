package com.github.InspiredOne.InspiredNations.Economy.Implem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Economy.MarketPlace;
import com.github.InspiredOne.InspiredNations.Economy.Sellable;
import com.github.InspiredOne.InspiredNations.Exceptions.NameAlreadyTakenException;
import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.Implem.ChestShop;
import com.github.InspiredOne.InspiredNations.Governments.Implem.GoodBusiness;
import com.github.InspiredOne.InspiredNations.ToolBox.SortTool;

public class ItemMarketplace implements MarketPlace<ItemSellable> {

	public static final String name = "Item Marketplace";
	
	public ItemMarketplace() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ItemSellable> getSales(PlayerData viewer) {
		List<ItemSellable> output = new ArrayList<ItemSellable>();
		
		for(Class<? extends InspiredGov> govclass:InspiredNations.regiondata.keySet()) {
			for(InspiredGov busi:InspiredNations.regiondata.get(govclass)) {
				GoodBusiness good = (GoodBusiness) busi;
				for(Facility fac:good.getFacilities()) {
					if(fac instanceof ChestShop) {
						for(ItemSellable item:((ChestShop) fac).getItems()) {
							if(item.isForSale()) {
								output.add(item);
							}
						}
					}
				}
			}
		}
		if(this.getComparators(viewer).size() > 0) {
			Collections.sort(output, this.getComparators(viewer).get(0).getComparator());
		}
		return output;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		
	}

	@Override
	public String getDisplayName(PlayerData viewer) {
		return name;
	}

	@Override
	public List<SortTool<ItemSellable>> getComparators(final PlayerData viewer) {
		List<SortTool<ItemSellable>> output = new ArrayList<SortTool<ItemSellable>>();
		// Alphabetically
		output.add(new SortTool<ItemSellable>() {

			@Override
			public String getName() {
				return "Alphabetically";
			}

			@Override
			public Comparator<ItemSellable> getComparator() {
				
				return new Comparator<ItemSellable>() {

					@Override
					public int compare(ItemSellable o1, ItemSellable o2) {
						return o1.getName().compareToIgnoreCase(o2.getName());
					}
					
				};
			}
			
		});
		
		// Price
		output.add(new SortTool<ItemSellable>() {

			@Override
			public String getName() {
				return "Price";
			}

			@Override
			public Comparator<ItemSellable> getComparator() {
				return new Comparator<ItemSellable>() {

					@Override
					public int compare(ItemSellable o1, ItemSellable o2) {

						return o1.getPrice(viewer.getCurrency(), viewer.getPlayerLocation()).compareTo(
								o2.getPrice(viewer.getCurrency(), viewer.getPlayerLocation()));
					}
				};
			}
		});
		
		// Unit Price
		output.add(new SortTool<ItemSellable>() {

			@Override
			public String getName() {
				return "Unit Price";
			}

			@Override
			public Comparator<ItemSellable> getComparator() {
				return new Comparator<ItemSellable>() {

					@Override
					public int compare(ItemSellable o1, ItemSellable o2) {
						BigDecimal o1uprice = o1.getPrice(viewer.getCurrency(), viewer.getPlayerLocation()).
								divide(new BigDecimal(o1.getItem().getAmount()));
						BigDecimal o2uprice = o2.getPrice(viewer.getCurrency(), viewer.getPlayerLocation()).
								divide(new BigDecimal(o2.getItem().getAmount()));
						
						return o1uprice.compareTo(o2uprice);
					}
				};
			}
		});
		
		// Distance
		output.add(new SortTool<ItemSellable>() {

			@Override
			public String getName() {
				return "Distance";
			}

			@Override
			public Comparator<ItemSellable> getComparator() {
				return new Comparator<ItemSellable>() {

					@Override
					public int compare(ItemSellable o1, ItemSellable o2) {
						double distdif = o1.getLocation().getLocation().distance(viewer.getPlayerLocation())
								- o2.getLocation().getLocation().distance(viewer.getPlayerLocation());
						if(distdif < 0) {
							return -1;
						}
						else if(distdif == 0) {
							return 0;
						}
						else  {
							return 1;
						}
					}
				};
			}
		});
		
		return output;
	}

}
