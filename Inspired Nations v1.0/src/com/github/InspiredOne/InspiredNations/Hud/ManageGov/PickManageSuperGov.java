package com.github.InspiredOne.InspiredNations.Hud.ManageGov;

import java.util.Iterator;
import java.util.LinkedHashSet;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.IsDirectSuperGovException;
import com.github.InspiredOne.InspiredNations.Exceptions.NotASuperGovException;
import com.github.InspiredOne.InspiredNations.Governments.GlobalGov;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.NoSubjects;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;

public class PickManageSuperGov extends PassByOptionMenu {

	Class<? extends InspiredGov> GovType;
	InspiredGov supergov;
	public PickManageSuperGov(PlayerData PDI, Class<? extends InspiredGov> GovType, InspiredGov supergov) {
		super(PDI);
		this.GovType = GovType;
		this.supergov = supergov;
	}

	@Override
	public String getPreOptionText() {
		// TODO Auto-generated method stub
		return "";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Menu PreviousMenu() {
		if(supergov.equals(InspiredNations.plugin.global)) {
			return new PickManageSelfType(PDI, (Class<? extends NoSubjects>) GovType);
		}
		else {
			return new PickManageSuperGov(PDI, GovType, supergov.getSuperGovObj());
		}
	}

	@Override
	public String getHeader() {
		return "Pick Super Gov";
	}

	@Override
	public void init() {
		LinkedHashSet<InspiredGov> options = PDI.getAllSuperGovsBelow(this.GovType, supergov);
		for(Iterator<InspiredGov> iter = options.iterator(); iter.hasNext();) {
			InspiredGov gov = iter.next();
			if(gov.getClass().equals(this.GovType)) {
				this.options.add(new PromptOption(this, gov.getName(), new ManageGov(PDI, (NoSubjects) gov)));
			}
			else {
				this.options.add(new PromptOption(this, gov.getName(), new PickManageSuperGov(PDI, this.GovType, gov)));
			}
		}
	}
}
