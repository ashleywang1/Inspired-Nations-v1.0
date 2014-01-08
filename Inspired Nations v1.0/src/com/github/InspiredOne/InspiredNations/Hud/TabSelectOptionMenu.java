package com.github.InspiredOne.InspiredNations.Hud;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.TabScrollManager;
import com.github.InspiredOne.InspiredNations.ToolBox.Datable;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;
/**
 * A menu where the options are listed in plain font except the one that is selected (it's bold).
 * The related options to the selection are listed in the options section of the menu. The data is null until after Init()
 * is run, so in order to do anything with it in the subclass, Init() must be run. This is because Init() is used for
 * putting options into the tab menu, and data is dependent on the list of tab options.
 * 
 * @author Jedidiah Phillips
 *
 * @param <E>
 */
public abstract class TabSelectOptionMenu<E extends Nameable> extends OptionMenu implements Datable<E> {

	private int tabcnt = 0;
	private int rangeBottom = maxLines;
	private static final int maxLines = 9;
	protected List<E> taboptions = new ArrayList<E>();
	private List<E> filteredoptions = new ArrayList<E>();
	private E data;
	public TabSelectOptionMenu(PlayerData PDI) {
		super(PDI);
	}

	public List<E> getTabOptions() {
		return this.taboptions;
	}

	@Override
	public final void init() {
		this.managers.add(new TabScrollManager(this));
		this.filteredoptions = this.taboptions;
		
		this.Init();
		this.data = this.filteredoptions.get(tabcnt);
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
		if(!tabOptionsToText(taboptions, tabcnt).isEmpty()) {
			output = output.concat(tabOptionsToText(taboptions, tabcnt));
			output = MenuTools.addDivider(output);
			output = output.concat(TextColor.INSTRUCTION + "Press '+' + TAB or '-' + TAB to cycle through the list.");
		}
		if(!this.postTabListPreOptionsText().isEmpty()) {
			output = MenuTools.addDivider(output.concat("\n"));
			output = output.concat(this.postTabListPreOptionsText() + "\n");
		}
		return output;
	}
	
	public String tabOptionsToText(List<? extends Nameable> taboptions, int tabcnt) {
		String output = "";
		//int iter = 0; // Used to identify which option to highlight

		// loop to set range that is displayed
		while(tabcnt >= rangeBottom || tabcnt < rangeBottom - maxLines) {
			if(tabcnt >= rangeBottom) {
				rangeBottom++;
				continue;
			}
			if(tabcnt < rangeBottom - maxLines) {
				rangeBottom--;
				continue;
			}
		}
		
		// write the text
		for(int iter = 0; iter<filteredoptions.size(); iter++) {
			output = output.concat(ChatColor.RESET + "");
			Nameable option = filteredoptions.get(iter); 
			if(iter >= rangeBottom - maxLines && iter < rangeBottom) {
				if(tabcnt == iter) {
					output = output.concat(TextColor.LABEL.toString() + ChatColor.BOLD + option.getName() + "\n");
				}
				else {
					output = output.concat(TextColor.LABEL + option.getName() + "\n");
				}
			}
		}
		return output;
	}

	@Override
	public void actionResponse() {
		TabScrollManager manager = ((TabScrollManager) this.managers.get(0));
		int tabsize = this.filteredoptions.size();
		this.setError(MenuError.NO_ERROR());
		
		if (manager.neither) {
			List<E> tempOptions = Tools.filter(manager.preTabEntry, this.taboptions);
			if(tempOptions.size() <= 0) {
				this.setError(MenuError.NO_MATCHES_FOUND());
				return;
			}
			else {
				this.filteredoptions = tempOptions;
				this.setTabcnt(0);
			}
			
		}
		else {
			if(manager.scrollUp) {
				this.setTabcnt(((this.getTabcnt() - 1) + tabsize) % tabsize);
			}
			else if(!manager.scrollUp) {
				this.setTabcnt((this.getTabcnt() + 1) % tabsize);
			}
		}
		this.setData(this.filteredoptions.get(tabcnt));
		System.out.println(this.getData().getName());
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
	
	public final Menu PreviousMenu() {
		if(this.taboptions != this.filteredoptions) {
			this.filteredoptions = this.taboptions;
			return this.getSelf();
		}
		else {
			return getPreviousPrompt();
		}
	}
	
	public Nameable getSelection() {
		return this.filteredoptions.get(tabcnt);
	}
	
	public void setData(E data) {
		this.data = data;
	}
	
	public E getData() {
		return this.data;
	}
	/**
	 * Gets the previous menu
	 * @return	 the previous menu
	 */
	public abstract Menu getPreviousPrompt();
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
