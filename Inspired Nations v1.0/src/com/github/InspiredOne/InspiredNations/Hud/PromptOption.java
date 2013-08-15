package com.github.InspiredOne.InspiredNations.Hud;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

/**
 * A <code>PromptOption</code> provides the basic structure of an option that simply
 * leads to another prompt with no input args. It has a single prompt that it leads to.
 * @author Jedidiah E. Phillips
 *
 */
public class PromptOption extends Option {

	Class<? extends Menu> nextPrompt;
	PlayerData PDI;
	
	public PromptOption(InspiredNations instance, PlayerData PDI, OptionMenu menu, String lable, Class<? extends Menu> prompt, OptionUnavail reason) {
		super(instance, menu, lable, reason);
		this.nextPrompt = prompt;
		this.PDI = PDI;
	}


	@Override
	public Menu response(String input) {
		if(this.isAvailable()) {
			return MenuTools.getMenuInstance(plugin, PDI, nextPrompt);
		}
		else {
			this.menu.setError(MenuError.NOT_AN_OPTION);
			return this.menu.getSelf();
		}
	}

}
