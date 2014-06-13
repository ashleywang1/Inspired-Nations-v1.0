package com.github.InspiredOne.InspiredNations.Hud.Implem;

import org.bukkit.ChatColor;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.NameAlreadyTakenException;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
import com.github.InspiredOne.InspiredNations.Hud.Implem.ColorOptions.Color;

public class ColorOptions extends TabSelectOptionMenu<Color> {

	public ColorOptions(PlayerData PDI) {
		super(PDI);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Menu getPreviousPrompt() {
		// TODO Auto-generated method stub
		return new CustomTheme(PDI);
	}

	@Override
	public String postTabListPreOptionsText() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void addTabOptions() {
		this.taboptions.add(new Color() {

			@Override
			public String getDisplayName(PlayerData viewer) {
				// TODO Auto-generated method stub
				return ChatColor.AQUA  + "AQUA";
			}

			@Override
			public String getString() {
				// TODO Auto-generated method stub
				return ChatColor.AQUA + "";
			}
			
		});
		
		this.taboptions.add(new Color() {

			@Override
			public String getDisplayName(PlayerData viewer) {
				// TODO Auto-generated method stub
				return ChatColor.BLACK  + "BLACK";
			}

			@Override
			public String getString() {
				// TODO Auto-generated method stub
				return ChatColor.BLACK + "";
			}
			
		});
		
		this.taboptions.add(new Color() {

			@Override
			public String getDisplayName(PlayerData viewer) {
				// TODO Auto-generated method stub
				return ChatColor.BLUE  + "BLUE";
			}

			@Override
			public String getString() {
				// TODO Auto-generated method stub
				return ChatColor.BLUE + "";
			}
			
		});
		
		this.taboptions.add(new Color() {

			@Override
			public String getDisplayName(PlayerData viewer) {
				// TODO Auto-generated method stub
				return ChatColor.DARK_AQUA  + "DARK AQUA";
			}

			@Override
			public String getString() {
				// TODO Auto-generated method stub
				return ChatColor.DARK_AQUA + "";
			}
			
		});
		
		this.taboptions.add(new Color() {

			@Override
			public String getDisplayName(PlayerData viewer) {
				// TODO Auto-generated method stub
				return ChatColor.DARK_BLUE  + "DARK BLUE";
			}

			@Override
			public String getString() {
				// TODO Auto-generated method stub
				return ChatColor.DARK_BLUE + "";
			}
			
		});
		
		this.taboptions.add(new Color() {

			@Override
			public String getDisplayName(PlayerData viewer) {
				// TODO Auto-generated method stub
				return ChatColor.DARK_GRAY  + "DARK GRAY";
			}

			@Override
			public String getString() {
				// TODO Auto-generated method stub
				return ChatColor.DARK_GRAY + "";
			}
			
		});
		
		this.taboptions.add(new Color() {

			@Override
			public String getDisplayName(PlayerData viewer) {
				// TODO Auto-generated method stub
				return ChatColor.DARK_GREEN  + "DARK GREEN";
			}

			@Override
			public String getString() {
				// TODO Auto-generated method stub
				return ChatColor.DARK_GREEN + "";
			}
			
		});
		
		this.taboptions.add(new Color() {

			@Override
			public String getDisplayName(PlayerData viewer) {
				// TODO Auto-generated method stub
				return ChatColor.DARK_PURPLE  + "DARK_PURPLE";
			}

			@Override
			public String getString() {
				// TODO Auto-generated method stub
				return ChatColor.DARK_PURPLE + "";
			}
			
		});
		
		this.taboptions.add(new Color() {

			@Override
			public String getDisplayName(PlayerData viewer) {
				// TODO Auto-generated method stub
				return ChatColor.GOLD  + "GOLD";
			}

			@Override
			public String getString() {
				// TODO Auto-generated method stub
				return ChatColor.GOLD + "";
			}
			
		});
		
		this.taboptions.add(new Color() {

			@Override
			public String getDisplayName(PlayerData viewer) {
				// TODO Auto-generated method stub
				return ChatColor.GREEN  + "GREEN";
			}

			@Override
			public String getString() {
				// TODO Auto-generated method stub
				return ChatColor.GREEN + "";
			}
			
		});
		
		this.taboptions.add(new Color() {

			@Override
			public String getDisplayName(PlayerData viewer) {
				// TODO Auto-generated method stub
				return ChatColor.LIGHT_PURPLE  + "LIGHT_PURPLE";
			}

			@Override
			public String getString() {
				// TODO Auto-generated method stub
				return ChatColor.LIGHT_PURPLE + "";
			}
			
		});
		
		this.taboptions.add(new Color() {

			@Override
			public String getDisplayName(PlayerData viewer) {
				// TODO Auto-generated method stub
				return ChatColor.RED  + "RED";
			}

			@Override
			public String getString() {
				// TODO Auto-generated method stub
				return ChatColor.RED + "";
			}
			
		});
		
		this.taboptions.add(new Color() {

			@Override
			public String getDisplayName(PlayerData viewer) {
				// TODO Auto-generated method stub
				return ChatColor.WHITE  + "WHITE";
			}

			@Override
			public String getString() {
				// TODO Auto-generated method stub
				return ChatColor.WHITE + "";
			}
			
		});
		
		this.taboptions.add(new Color() {

			@Override
			public String getDisplayName(PlayerData viewer) {
				// TODO Auto-generated method stub
				return ChatColor.YELLOW  + "YELLOW";
			}

			@Override
			public String getString() {
				// TODO Auto-generated method stub
				return ChatColor.YELLOW + "";
			}
			
		});
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
		return "Choose the color here:";
	}
	
	public abstract class Color implements Nameable {
		

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return "";
		}

		@Override
		public void setName(String name) throws NameAlreadyTakenException {
			// TODO Auto-generated method stub
			
		}
		
		public abstract String getString();
		
		
	}

}
