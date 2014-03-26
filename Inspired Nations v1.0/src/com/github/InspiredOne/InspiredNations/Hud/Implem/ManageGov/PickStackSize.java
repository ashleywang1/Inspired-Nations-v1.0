package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Economy.Implem.ItemSellable;
import com.github.InspiredOne.InspiredNations.Governments.Implem.ChestShop;
import com.github.InspiredOne.InspiredNations.Hud.InputMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public class PickStackSize extends InputMenu {

	Menu previous;
	ItemSellable item;
	ChestShop shop;
	public PickStackSize(PlayerData PDI, Menu previous, ItemSellable item, ChestShop shop) {
		super(PDI);
		this.previous = previous;
		this.item = item;
		this.shop = shop;
	}

	@Override
	public Menu nextMenu() {
		shop.getItems().add(item);
		return previous;
	}

	@Override
	public String validate(String input) {
		try {
			Integer.parseInt(input);
		}
		catch (Exception ex) {
			return MenuError.INVALID_NUMBER_INPUT();
		}
		return MenuError.NO_ERROR();
	}

	@Override
	public void useInput(String input) {
		int temp = Integer.parseInt(input);
		this.item.getItem().setAmount(temp);
	}

	@Override
	public List<String> getTabOptions() {
		List<String> output = new ArrayList<String>();
		return output;
	}

	@Override
	public String getInstructions() {
		return "Type the amount you would like to sell with each sale. Limit "+ TextColor.VALUE  + "1 - "
	+ this.item.getItem().getMaxStackSize() + TextColor.INSTRUCTION + ".";
	}

	@Override
	public void init() {

	}

	@Override
	public InputMenu getSelf() {
		return new PickStackSize(PDI, previous, item, shop);
	}

	@Override
	public String getHeader() {
		return "Pick Amount";
	}

	@Override
	public Menu getPreviousMenu() {
		return previous;
	}

	@Override
	public boolean getPassBy() {
		return (item.getItem().getMaxStackSize() == 1);
	}
}
