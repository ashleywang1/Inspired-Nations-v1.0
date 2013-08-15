package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;

import org.bukkit.conversations.Prompt;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.MainHud;
import com.github.InspiredOne.InspiredNations.Hud.Option;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;

public class PickSelfType extends OptionMenu {

	NewGovSeries series;
	
	@SuppressWarnings("static-access")
	public PickSelfType(InspiredNations instance, PlayerData PDI, NewGovSeries series) {
		super(instance, PDI);
		this.series = series;
		
		for(Class<? extends InspiredGov> gov:series.getGovInstance().getSelfGovs()) {
			this.options.add(new PromptOption(plugin, PDI, Tools.getGovInstance(gov).getTypeName(), PickName.class));
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
	public Prompt getPreviousPrompt() {
		return MenuTools.getMenuInstance(plugin, PDI, MainHud.class);
	}

}
