package com.github.InspiredOne.InspiredNations.Hud;

import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.ContextData;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

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
