package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;

import org.bukkit.conversations.Prompt;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;

public class PickSelfType extends OptionMenu {

	
	@SuppressWarnings("static-access")
	public PickSelfType(PlayerData PDI) {
		super(PDI);
		
		for(Class<? extends InspiredGov> gov:series.getGovInstance().getSelfGovs()) {
			this.options.add(new PromptOption(this, Tools.getGovInstance(gov).getTypeName(), PickName.class, ));
		}
	}

	@Override
	public String getPreOptionText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Menu getPreviousMenu() {
		return MenuTools.getMenuInstance(plugin, PDI, MainHud.class);
	}

}
