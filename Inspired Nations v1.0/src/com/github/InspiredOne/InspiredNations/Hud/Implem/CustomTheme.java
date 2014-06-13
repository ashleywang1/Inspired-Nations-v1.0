package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.NameAlreadyTakenException;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
import com.github.InspiredOne.InspiredNations.Hud.Implem.CustomTheme.TextCatagory;

public class CustomTheme extends TabSelectOptionMenu<TextCatagory> {

	public CustomTheme(PlayerData PDI) {
		super(PDI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Menu getPreviousPrompt() {
		// TODO Auto-generated method stub
		return new ColorMenu(PDI);
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
						// TODO Auto-generated method stub
						return "Header";
					}

					@Override
					public String getDisplayName(PlayerData viewer) {
						// TODO Auto-generated method stub
						return viewer.theme.HEADER() + this.getName();
					}

					@Override
					void changeColor(String color) {
						// TODO Auto-generated method stub
						PDI.theme.setHEADER(color);
					} 
					
				});
		
		//SUBHEADER
		this.taboptions.add(
				new TextCatagory(PDI) {

					@Override
					public String getName() {
						// TODO Auto-generated method stub
						return "Sub-Header";
					}

					@Override
					public String getDisplayName(PlayerData viewer) {
						// TODO Auto-generated method stub
						return viewer.theme.SUBHEADER() + this.getName();
					}

					@Override
					void changeColor(String color) {
						// TODO Auto-generated method stub
						PDI.theme.setSUBHEADER(color);
					} 
					
				});
		//OPTION NUMBERS
		this.taboptions.add(
				new TextCatagory(PDI) {

					@Override
					public String getName() {
						// TODO Auto-generated method stub
						return "Option Numbers (#)";
					}

					@Override
					public String getDisplayName(PlayerData viewer) {
						// TODO Auto-generated method stub
						return viewer.theme.OPTIONNUMBER() + this.getName();
					}

					@Override
					void changeColor(String color) {
						// TODO Auto-generated method stub
						PDI.theme.setOPTIONNUMBER(color);
					} 
					
				});
		
		//OPTION
		this.taboptions.add(
				new TextCatagory(PDI) {

					@Override
					public String getName() {
						// TODO Auto-generated method stub
						return "Options";
					}

					@Override
					public String getDisplayName(PlayerData viewer) {
						// TODO Auto-generated method stub
						return viewer.theme.OPTION() + this.getName();
					}

					@Override
					void changeColor(String color) {
						// TODO Auto-generated method stub
						PDI.theme.setOPTION(color);
					} 
					
				});
		
		
		
	}

	@Override
	public void addOptions() {
		//PICK COLOR
		this.options.add(new PromptOption(this, "Color Selection", new ColorOptions(PDI)));
				
		
	}

	@Override
	public void addActionManagers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getHeader() {
		// TODO Auto-generated method stub
		return "Choose which section you want to change:";
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
