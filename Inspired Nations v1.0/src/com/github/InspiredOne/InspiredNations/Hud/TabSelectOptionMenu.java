package com.github.InspiredOne.InspiredNations.Hud;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.TabScrollManager;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public abstract class TabSelectOptionMenu extends OptionMenu {

	private int tabcnt = 0;
	protected List<Nameable> taboptions = new ArrayList<Nameable>();	
	public TabSelectOptionMenu(PlayerData PDI) {
		super(PDI);
	}

	public List<Nameable> getTabOptions() {
		return this.taboptions;
	}

	@Override
	public final void init() {
		this.managers.add(new TabScrollManager(this));
		this.Init();
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
	public final String getPreOptionText() {
		String output = "";
		if(!TabSelectOptionMenu.tabOptionsToText(taboptions, tabcnt).isEmpty()) {
			output = output.concat(TabSelectOptionMenu.tabOptionsToText(taboptions, tabcnt));
			output = MenuTools.addDivider(output);
			output = output.concat(TextColor.INSTRUCTION + "Press '=' + TAB to cycle through the list, '+' + TAB to move selection up," +
					" and '-' + TAB to move selection down.");
		}
		if(!this.postTabListPreOptionsText().isEmpty()) {
			output = MenuTools.addDivider(output.concat("\n"));
			output = output.concat(this.postTabListPreOptionsText() + "\n");
		}
		return output;
	}
	
	public static String tabOptionsToText(List<Nameable> taboptions, int tabcnt) {
		String output = "";
		int iter = 0; // Used to identify which option to highlight
		for(Nameable option:taboptions) {
			output = output.concat(ChatColor.RESET + "");
			if(tabcnt == iter) {
				output = output.concat(TextColor.LABEL.toString() + ChatColor.BOLD + option.getName() + "\n");
			}
			else {
				output = output.concat(TextColor.LABEL + option.getName() + "\n");
			}
			iter++;
		}
		return output;
	}

	@Override
	public void actionResponse() {
		this.setTabcnt((this.getTabcnt() + 1) % this.getTabOptions().size());
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
	/**
	 * Used to insert text between tab options and input options
	 * @return	the text to be inserted
	 */
	public abstract String postTabListPreOptionsText();
	/**
	 * Used to do things for the conversation, but only for when the user is directed to it.
	 * Use for adding options, managers, and tab-completes.
	 */
	public abstract void Init();
}
