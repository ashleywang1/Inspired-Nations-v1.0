package com.github.InspiredOne.InspiredNations.Hud;

import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.OptionUnavail;

/**
 * A prompt that passes data to the next menu by using the conversation context.
 * It puts the data in with the key PromptData. Following menus can access this
 * data by using the PromptData key on the conversation context.
 * @author Jedidiah E. Phillips
 *
 */
public class DataPassPromptOption extends PromptOption {

	InspiredGov data;
	DataCatchMenu<InspiredGov> nextPrompt;
	
	public DataPassPromptOption(OptionMenu menu, String lable, DataCatchMenu<InspiredGov> nextPrompt,
			OptionUnavail reason, InspiredGov data) {
		super(menu, lable, nextPrompt, reason);
		this.data = data;
		this.nextPrompt = nextPrompt;
	}
	
	public DataPassPromptOption(OptionMenu menu, String lable, DataCatchMenu<InspiredGov> nextPrompt,
			String description, InspiredGov data) {
		super(menu, lable, nextPrompt, description);
		this.data = data;
		this.nextPrompt = nextPrompt;
	}
	
	public DataPassPromptOption(OptionMenu menu, String lable, DataCatchMenu<InspiredGov> nextPrompt, InspiredGov data) {
		super(menu, lable, nextPrompt);
		this.data = data;
		this.nextPrompt = nextPrompt;
	}
	
	@Override
	public final void doStuff() {
		this.nextPrompt.setData(data);
	}
}
