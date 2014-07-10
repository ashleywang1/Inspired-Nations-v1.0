package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress.PickGovGeneral;
import com.github.InspiredOne.InspiredNations.ToolBox.Datable;
import com.github.InspiredOne.InspiredNations.ToolBox.Relation;

public class PickRelationMenu extends PickGovGeneral {

	OwnerGov gov;
	
	public PickRelationMenu(PlayerData PDI, Menu previous, Menu next,
			Datable<InspiredGov> superGov, OwnerGov gov) {
		super(PDI, previous, next, superGov);
		this.gov = gov;
	}

	public PickRelationMenu(PlayerData PDI, Menu previous, Menu next, OwnerGov gov) {
		super(PDI, previous, next);
		this.gov = gov;
	}

	@Override
	public boolean check(InspiredGov govre) {
		if(govre.getTier() == gov.getTier() && gov.isSubOf(govre.getSuperGovObj())
				&& !this.gov.getRelations().containsKey(govre) && this.gov != govre) {
			return true;
		}
		return false;
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public void addOptions() {
		if(!this.taboptions.isEmpty()) {
			this.options.add(new ChangeRelationOption(this, "Ally " + this.getData().getName(),
					Relation.ALLY, (OwnerGov) this.getData(), this.gov));
			this.options.add(new ChangeRelationOption(this, "Enemy " + this.getData().getName(),
					Relation.ENEMY, (OwnerGov) this.getData(), this.gov));
		}
	}

	@Override
	public void addActionManagers() {

	}

	@Override
	public String getHeader() {
		return "Pick Government";
	}

}
