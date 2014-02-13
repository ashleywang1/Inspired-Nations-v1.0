package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerSubjectGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress.PickGovGeneral;
import com.github.InspiredOne.InspiredNations.ToolBox.Datable;

public class PickGovToRequestOwner extends PickGovGeneral {

	public PickGovToRequestOwner(PlayerData PDI, Menu previous, Menu next,
			Datable<InspiredGov> superGov) {
		super(PDI, previous, next, superGov);
	}

	public PickGovToRequestOwner(PlayerData PDI, Menu previous, Menu next) {
		super(PDI, previous, next);
	}

	@Override
	public boolean check(InspiredGov gov) {
		if(!(gov instanceof OwnerGov)) {
			return false;
		}
		else {
			if(((OwnerGov)gov).getOwnerRequests().contains(PDI.getPlayerID())) {
				return false;
			}
			else {
				return true;
			}
		}
	}

	@Override
	public void insertOptions() {
		String description = "";
		if(this.taboptions.size() != 0) {
			OwnerSubjectGov gov = ((OwnerSubjectGov) this.getData());
			if(gov.getAllSubGovsAndFacilitiesJustBelow().size() > 0 && gov.isSubject(PDI.getPlayerID())) {
				this.options.add(new PromptOption(this, "Search Under", new PickGovToRequestOwner(PDI, this, this.next, gov)));
			}
			if(!gov.canAddWithoutConsequence(PDI)) {
				description = "Joining will remove you from your " + gov.getTypeName();
			}
			if(gov.getOwnerOffers().contains(PDI.getPlayerID())) {
				this.options.add(new JoinOwnerGovOption(this, "Accept Offer From " + this.getData().getName(), (OwnerGov) this.getData()));
			}
			else {
				options.add(new RequestOwnerOption(this, "Request " + gov.getOwnerPositionName(), description, gov));
			}
		}
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public TabSelectOptionMenu<?> GetSelf() {
		return new PickGovToRequestOwner(PDI, previous, next, superGov);
	}

	@Override
	public String getHeader() {
		return "Pick A Government To Request Ownership";
	}

}
