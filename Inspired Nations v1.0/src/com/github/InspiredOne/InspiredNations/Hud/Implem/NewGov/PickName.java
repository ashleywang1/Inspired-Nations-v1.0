package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.conversations.Prompt;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.InputMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class PickName extends InputMenu {

	NewGovSeries series;
	public PickName(InspiredNations instance, PlayerData PDI, NewGovSeries series) {
		super(instance, PDI);
		this.series = series;
	}

	@Override
	public Prompt nextPrompt() {
		
		
		
		return null;
	}

	@Override
	public MenuError validate(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void useInput(String input) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getTabOptions() {
		List<String> output = new ArrayList<String>();
		return output;
	}

	@Override
	public Prompt PreviousPrompt() {
		return series;
	}

	@Override
	public String getHeader() {
		return "Type Name";
	}

	@Override
	public String getFiller() {
		// TODO Auto-generated method stub
		return null;
	}

}
