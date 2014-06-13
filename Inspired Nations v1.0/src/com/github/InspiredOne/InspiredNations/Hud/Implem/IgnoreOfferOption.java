package com.github.InspiredOne.InspiredNations.Hud.Implem;

import java.util.ArrayList;

import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;

public class IgnoreOfferOption extends Option {

	Nameable item;
	ArrayList<?> list;
	public IgnoreOfferOption(OptionMenu menu, String label, OptionUnavail reason, Nameable item, ArrayList<?> list) {
		super(menu, label, reason);
		this.list = list;
		this.item = item;
	}

	public IgnoreOfferOption(OptionMenu menu, String label, Nameable item, ArrayList<?> list) {
		super(menu, label);
		this.list = list;
		this.item = item;
	}

	public IgnoreOfferOption(OptionMenu menu, String label, String description, Nameable item, ArrayList<?> list) {
		super(menu, label, description);
		this.list = list;
		this.item = item;
	}

	@Override
	public Menu response(String input) {
		
		this.list.remove(item);
		return menu;
	}

}
