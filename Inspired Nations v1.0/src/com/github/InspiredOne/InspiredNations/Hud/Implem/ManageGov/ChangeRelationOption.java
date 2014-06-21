package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;
import com.github.InspiredOne.InspiredNations.ToolBox.Relation;

public class ChangeRelationOption extends Option {

	Relation re;
	OwnerGov govre;
	OwnerGov selfgov;
	
	public ChangeRelationOption(OptionMenu menu, String label, Relation re, OwnerGov govre, OwnerGov selfgov,
			OptionUnavail reason) {
		super(menu, label, reason);
		this.re = re;
		this.govre = govre;
		this.selfgov = selfgov;
	}

	public ChangeRelationOption(OptionMenu menu, String label, Relation re, OwnerGov govre, OwnerGov selfgov) {
		super(menu, label);
		this.re = re;
		this.govre = govre;
		this.selfgov = selfgov;
	}

	public ChangeRelationOption(OptionMenu menu, String label, Relation re, OwnerGov govre, OwnerGov selfgov,
			String description) {
		super(menu, label, description);
		this.re = re;
		this.govre = govre;
		this.selfgov = selfgov;
	}

	@Override
	public Menu response(String input) {
		if(re.equals(Relation.NEUTRAL)) {
			selfgov.getRelations().remove(govre);
		}
		else {
			selfgov.getRelations().put(govre, re);
		}
		return menu;
	}

}
