package com.github.InspiredOne.InspiredNations.Hud;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Listeners.ActionManager;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.TabScollManager;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public abstract class TabSelectOptionMenu extends ActionMenu {

	List<ActionManager> managers= new ArrayList<ActionManager>();
	List<Nameable> taboptions = new ArrayList<Nameable>();
	
	private int tabcnt = 0;
	
	public TabSelectOptionMenu(PlayerData PDI) {
		super(PDI);
	}

	public abstract List<Nameable> getTabOptions();
	
	@Override
	public List<ActionManager> getActionManager() {
		return managers;
	}

	@Override
	public void init() {
		this.managers.add(new TabScollManager(this));
		this.taboptions = this.getTabOptions();
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
	public String getFiller() {
		String output = "";
		if(!this.tabOptionsToText().isEmpty()) {
			output = output.concat(this.tabOptionsToText());
			output = MenuTools.addDivider(output);
			output = output.concat(TextColor.INSTRUCTION + "Press TAB to cycle through the list.");
			output = MenuTools.addDivider(output);
		}
		return output;
	}
	
	public String tabOptionsToText() {
		String output = "";
		int iter = 0; // Used to identify which option to highlight
		for(Nameable option:this.taboptions) {
			output = output.concat(ChatColor.RESET + "");
			if(tabcnt == iter) {
				output = output.concat(ChatColor.BOLD + "");
			}
			output = output.concat(TextColor.LABEL + option.getName() + "/n");
		}
		return output;
	}

	@Override
	public void actionResponse() {
		this.setTabcnt(this.getTabcnt() + 1);
	}
	
	public int getTabcnt() {
		return tabcnt;
	}

	public void setTabcnt(int tabcnt) {
		if(this.tabcnt > 1024) {
			// Because trolls and memory leaks t(<.<)t
			this.tabcnt = 0;
		}
		else {
			this.tabcnt = tabcnt;
		}
	}
}
