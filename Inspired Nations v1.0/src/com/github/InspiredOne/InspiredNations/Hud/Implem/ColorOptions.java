package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.NameAlreadyTakenException;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;

public class ColorOptions<colorList> extends TabSelectOptionMenu<String> { //TODO fix this error

	public ColorOptions(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public Menu getPreviousPrompt() {
		// TODO Auto-generated method stub
		return new PlayerProfile(PDI); //TODO fix this
	}

	@Override
	public String postTabListPreOptionsText() {
		// TODO Auto-generated method stub
		return "Tab to select color";
	}

	@Override
	public void addTabOptions() {
		
		//HEADER
		this.taboptions.add(
				new TextCatagory(PDI) {

					@Override
					public String getName() {
						return "Header";
					}

					@Override
					public String getDisplayName(PlayerData viewer) {
						return viewer.theme.HEADER() + this.getName();
					}

					@Override
					void changeColor(String color) {
						// TODO Auto-generated method stub
						//PDI.theme.setName(color);jik
					} 
					
				});
		
		//OPTION NUMBERS
		this.taboptions.add(
				new TextCatagory(PDI) {

					@Override
					public String getName() {
						return "Option Number";
					}

					@Override
					public String getDisplayName(PlayerData viewer) {
						return viewer.theme.HEADER() + this.getName();
					}

					@Override
					void changeColor(String color) {
						// TODO Auto-generated method stub
						
					}
					
							
				}
			);
		
		//this.options.add(new PromptOption(this, "Custom Theme", new ColorOptions(PDI)));
		
	}

	@Override
	public void addOptions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public abstract class TextCatagory implements Nameable {
		
		PlayerData PDI;
		
		public TextCatagory(PlayerData PDI) {
			this.PDI = PDI;
		}

		@Override
		public void setName(String name) throws NameAlreadyTakenException {
			// TODO Auto-generated method stub
			
		}
		
		abstract void changeColor(String color);

		//implement getDisplayName seperately
		
	}

}
