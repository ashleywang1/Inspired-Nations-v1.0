package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.NameAlreadyTakenException;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;

public class ColorOptions<E> extends TabSelectOptionMenu<E> {

	public ColorOptions(PlayerData PDI) {
		super(PDI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Menu getPreviousPrompt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String postTabListPreOptionsText() {
		// TODO Auto-generated method stub
		return "Tab to select color";
	}

	@Override
	public void addTabOptions() {
		this.taboptions.add(
				new TextCatagory(PDI) {

					@Override
					public String getName() {
						// TODO Auto-generated method stub
						return "Header";
					}

					@Override
					public String getDisplayName(PlayerData viewer) {
						// TODO Auto-generated method stub
						return PDI.theme.header;
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
