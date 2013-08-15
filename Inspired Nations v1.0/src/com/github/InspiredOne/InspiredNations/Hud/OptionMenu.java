package com.github.InspiredOne.InspiredNations.Hud;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public abstract class OptionMenu extends Menu {

	protected List<Option> options = new ArrayList<Option>();
	
	public OptionMenu(InspiredNations plugin, PlayerData PDI) {
		super(plugin, PDI);
	}

	@Override
	public String getFiller() {
		String output = "";
		output = output.concat(this.getPreOptionText());
		output = output.concat(this.optionsToText());
		return output;
	}
	
	@Override
	public Menu getNextMenu(String arg) {
		int answer;
		try {
			answer = Integer.parseInt(arg);
			if(answer > options.size()) {
				this.setError(MenuError.OUT_OF_RANGE_NUMBER_INPUT);
				return this.getSelf();
			}
			else {
				return options.get(answer - 1).response(arg);
			}
		}
		catch (Exception ex) {
				this.setError(MenuError.INVALID_NUMBER_INPUT);
				return this.getSelf();
		}
	}
	
	public List<Option> getOptions() {
		return this.options;
	}
	/**Method used to set the options*/
	
	public abstract String getPreOptionText();
	/**Used to add text before the list of options*/
	
	private final String optionsToText() {
		String output = "";
		int iter = 1;
		
		for(Option option:options)  {
			if(option.isAvailable()) {
				output = output.concat(TextColor.OPTION + "(" + TextColor.OPTIONNUMBER + iter + TextColor.OPTION + ") " + option.getLabel() + "\n");
			}
			else {
				output = output.concat(TextColor.UNAVAILABLE + "(" + TextColor.UNAVAILREASON + iter + TextColor.UNAVAILABLE + ") " + option.getLabel() +
						TextColor.UNAVAILREASON + option.getUnvailReason() + "\n");
			}
			iter ++;
		}
		
		return output;
	}
	
	@Override
	public void register() {
		
	}

}
