package com.github.InspiredOne.InspiredNations.Hud;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public abstract class OptionMenu extends ActionMenu {

	protected List<Option> options = new ArrayList<Option>();
	
	public OptionMenu(PlayerData PDI) {
		super(PDI);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Menu> T getSelf(T self) {
		Debug.print("Is self null? " + this.self == null);
		Debug.print(self);
		OptionMenu output = (OptionMenu) this.self;
		output.options = new ArrayList<Option>();
		output.initialized = false;
		return (T) this.self;
	}
	
	@Override
	public final String getFiller() {
		String output = "";
		if(!this.getPreOptionText().isEmpty()) {
			output = output.concat(TextColor.INSTRUCTION + this.getPreOptionText() + "\n");
			if(!optionsToText(options).isEmpty()) {
				output = MenuTools.addDivider(output);
			}
		}
		output = output.concat(optionsToText(options));
		return output;
	}
	
	@Override
	public final Menu getNextMenu(String arg) {
		int answer;
		try {
			String[] args = arg.split(" ");
			answer = Integer.parseInt(args[0]);
			if(answer > options.size()) {
				this.setError(MenuError.OUT_OF_RANGE_NUMBER_INPUT());
				return getSelf(this);
			}
			else {
				return options.get(answer - 1).response(arg.substring(args[0].length()).trim());
			}
		}
		catch (Exception ex) {
				this.setError(MenuError.INVALID_NUMBER_INPUT());
				return getSelf(this);
		}
	}
	
	public static String optionsToText(List<Option> options) {
		String output = "";
		int iter = 1;
		
		for(Option option:options)  {
			if(option.isAvailable()) {
				output = output.concat(TextColor.OPTION + "(" + TextColor.OPTIONNUMBER + iter + TextColor.OPTION + ") "
			+ option.getName() + TextColor.OPTIONDESCRIP + " " + option.getDescription() + "\n");
			}
			else {
				output = output.concat(TextColor.UNAVAILABLE + "(" + TextColor.UNAVAILREASON + iter + TextColor.UNAVAILABLE + ") " + option.getName() +
					TextColor.UNAVAILREASON + option.getUnvailReason() + "\n");
			}
			iter ++;
		}
		
		return output;
	}
	
	/**
	 * Used to get the options.
	 * @return	the options for this menu
	 */
	public final List<Option> getOptions() {
		return this.options;
	}
	/**
	 * Used to add text before the list of options.
	 * @return	the text used before the options
	 */
	public abstract String getPreOptionText();

	@Override
	public void actionResponse() {
		
	}

}
