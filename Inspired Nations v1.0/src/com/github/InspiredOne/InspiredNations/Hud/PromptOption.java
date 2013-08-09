package com.github.InspiredOne.InspiredNations.Hud;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;

/**
 * A <code>PromptOption</code> provides the basic structure of an option that simply
 * leads to another prompt with no input args. It has a single prompt that it leads to.
 * @author Jedidiah E. Phillips
 *
 */
public class PromptOption extends Option {

	Class<? extends Menu> nextPrompt;
	PlayerData PDI;
	
	public PromptOption(InspiredNations instance, PlayerData PDI, String lable, Class<? extends Menu> prompt) {
		super(instance, lable);
		this.nextPrompt = prompt;
		this.PDI = PDI;
	}

	@Override
	public Menu response(String input) {
		return MenuTools.getMenuInstance(plugin, PDI, nextPrompt);
	}

	@Override
	public String getLabel() {
		return this.getLabel();
	}

}
