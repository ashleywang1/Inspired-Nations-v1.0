package com.github.InspiredOne.InspiredNations.Hud;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.conversations.Prompt;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public abstract class OptionMenu extends Menu {

	protected List<Option> options = new ArrayList<Option>();
	
	public OptionMenu(InspiredNations instance, PlayerData PDI) {
		super(instance, PDI);
	}

	@Override
	public String getFiller() {
		String output = "";
		output = output.concat(this.getPreOptionText());
		output = output.concat(this.optionsToText());
		return output;
	}
	
	@Override
	public Prompt getNextPrompt(String arg) {
		
		int answer;
		try {
			answer = Integer.parseInt(arg);
			if(answer > options.size()) {
				this.getContext().setSessionData("Error", MenuError.OUT_OF_RANGE_NUMBER_INPUT);
				return this.getSelf();
			}
			else {
				return options.get(answer - 1).response(arg);
			}
		}
		catch (Exception ex) {
				this.PDI.getCon().getContext().setSessionData("Error", MenuError.INVALID_NUMBER_INPUT);
				return this.getSelf();
		}
	}
	
	public List<Option> getOptions() {
		return this.options;
	}
	/**Method used to set the options*/
	
	public abstract String getPreOptionText();
	/**Used to add text before the list of options*/
	
	public String optionsToText() {
		String output = "";
		int iter = 1;
		
		for(Option option:options)  {
			output = output.concat(TextColor.OPTION + "(" + TextColor.OPTIONNUMBER + iter + TextColor.OPTION + ")" + option.getLabel() + "\n");
			iter ++;
		}
		
		return output;
	}

}
