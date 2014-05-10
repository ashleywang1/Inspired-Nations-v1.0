package com.github.InspiredOne.InspiredNations.Hud.Implem;

import java.util.List;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.HelpMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov.PickManageSelfType;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Money.ManageMoney;
import cpom.github.InspiredOne.InspiredNations.Hud.Implem.NewGov.PickSelfType;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public class MainHud extends OptionMenu {
	

	public MainHud(PlayerData PDI) {
		super(PDI);
/*		help = new HelpMenu(PDI, this).addPage(
				"Welcome to the main HUD. This documentation will help get you "
				+ "familiar with how to use the HUD. You can navigate these help "
				+ "docs by typing a page number in chat and hitting " + TextColor.VALUE
				+ "Enter" + TextColor.INSTRUCTION +". "
				);*/
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
	public Menu getPreviousMenu() {
		return this.getSelfPersist();
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
	public void addOptions() {
		this.options.add(new PromptOption(this, "Map", new Map(PDI)));
		if(!InspiredNations.Markets.isEmpty()) {
			this.options.add(new PromptOption(this, "Market", new PickMarketplace(PDI)));
		}
		this.options.add(new PromptOption(this, "Player Directory", new PlayerDirectory(PDI)));
		this.options.add(new PromptOption(this, "Citizenship", new PlayerCitizenship(PDI)));
		this.options.add(new PromptOption(this, "Manage Money", new ManageMoney(PDI)));
		this.options.add(new getMoneyOption(this, "Get 1000 " + PDI.getCurrency().getName(), PDI));
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
				this.options.add(new PromptOption(this, "New " + govobj.getTypeName(), new PickSelfType<>(PDI, gov)));
			}
		}
		
	}

	@Override
	public void addActionManagers() {
		
	}
}
