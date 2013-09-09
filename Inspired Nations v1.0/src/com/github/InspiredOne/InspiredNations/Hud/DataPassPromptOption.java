package com.github.InspiredOne.InspiredNations.Hud;

import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.ContextData;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

/**
 * A prompt that passes data to the next menu by using the conversation context.
 * It puts the data in with the key PromptData. Following menus can access this
 * data by using the PromptData key on the conversation context.
 * @author Jedidiah E. Phillips
 *
 */
public class DataPassPromptOption extends PromptOption {

	Object data;
	
	public DataPassPromptOption(OptionMenu menu, String lable, Menu nextPrompt,
			OptionUnavail reason, Object data) {
		super(menu, lable, nextPrompt, reason);
		this.data = data;
	}
	
	public DataPassPromptOption(OptionMenu menu, String lable, Menu nextPrompt,
			String description, Object data) {
		super(menu, lable, nextPrompt, description);
		this.data = data;
	}
	
	public DataPassPromptOption(OptionMenu menu, String lable, Menu nextPrompt, Object data) {
		super(menu, lable, nextPrompt);
		this.data = data;
	}
	
	@Override
	public final void doStuff() {
		menu.getContext().setSessionData(ContextData.PromptData, data);
	}
}
