package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;

public class OwnerOffers extends TabSelectOptionMenu<OwnerGov> {

	public OwnerOffers(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public Menu getPreviousPrompt() {
		return new PlayerCitizenship(PDI);
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public void Init() {
		for(InspiredGov gov:InspiredNations.regiondata) {
			if(gov instanceof OwnerGov) {
				if(((OwnerGov) gov).getOwnerOffers().contains(PDI.getPlayerID())) {
					this.taboptions.add((OwnerGov) gov);
				}
			}
		}
		if(this.taboptions.size() != 0) {
			this.options.add(new IgnoreOfferOption(this, "Ignore Offer From " + this.getData().getName(), PDI.getPlayerID(), this.getData().getOwnerOffers()));
			this.options.add(new JoinOwnerGovOption(this, "Accept Offer From " + this.getData().getName(), this.getData(), PDI.getPlayerID()));
		}
		this.options.add(new PromptOption(this, "Request Ownership", new OwnerRequests(PDI)));
	}

	@Override
	public String getHeader() {
		return "Offers For Ownership";
	}

	@Override
	public TabSelectOptionMenu<?> GetSelf() {
		return new OwnerOffers(PDI);
	}

}
