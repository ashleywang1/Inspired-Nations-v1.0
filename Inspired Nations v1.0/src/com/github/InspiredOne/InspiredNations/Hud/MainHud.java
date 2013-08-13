package com.github.InspiredOne.InspiredNations.Hud;

import org.bukkit.conversations.Prompt;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Map;

public class MainHud extends OptionMenu {
	

	public MainHud(InspiredNations instance, PlayerData PDI) {
		super(instance, PDI);
		this.options.add(new PromptOption(plugin, PDI, "Map", Map.class));
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
	public Prompt getPreviousPrompt() {
		return this.getSelf();
	}

}
