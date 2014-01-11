package com.github.InspiredOne.InspiredNations.Hud.Implem;

import java.util.List;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Money.ManageMoney;
import com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov.PickSelfType;
import com.github.InspiredOne.InspiredNations.Hud.ManageGov.PickManageSelfType;

public class MainHud extends OptionMenu {
	

	public MainHud(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public String getPreOptionText() {
		return "";
	}

	@Override
	public String getHeader() {
		return "Welcome to the HUD! Enter an option number.";
	}

	@Override
	public Menu PreviousMenu() {
		return this.getSelf();
	}

	@Override
	public boolean getPassBy() {
		return false;
	}

	@Override
	public Menu getPassTo() {
		return null;
	}

	@Override
	public void init() {
		this.options.add(new PromptOption(this, "Map", new Map(PDI)));
		this.options.add(new PromptOption(this, "Player Directory", new PlayerDirectory(PDI)));
		this.options.add(new PromptOption(this, "Manage Money", new ManageMoney(PDI)));
		this.options.add(new PromptOption(this, "Test Tab Menu", new TestTabMenu(PDI)));
		this.options.add(new getMoneyOption(this, "Get 1000 Coins", PDI));
		List<Class<? extends OwnerGov>> array = InspiredNations.global.getAllSubGovs();
		array.remove(InspiredNations.global.getClass());
		
		for(Class<? extends OwnerGov> gov:array) {
			OwnerGov govobj = (OwnerGov) GovFactory.getGovInstance(gov);
			if(!PDI.getOwnership(gov).isEmpty()) {
				this.options.add(new PromptOption(this, "Manage " + govobj.getTypeName(), new PickManageSelfType(PDI, gov)));
			}
		}
		
		for(Class<? extends OwnerGov> gov:array) {
			OwnerGov govobj = (OwnerGov) GovFactory.getGovInstance(gov);
			if(!PDI.getCitizenship(govobj.getSuperGov()).isEmpty()) {
				this.options.add(new PromptOption(this, "New " + govobj.getTypeName(), new PickSelfType(PDI, gov)));
			}
		}
	}
}
