package com.github.InspiredOne.InspiredNations.Hud.Implem;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.HelpMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Player.PlayerID;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public class PlayerDirectory extends TabSelectOptionMenu<PlayerID> {

	public PlayerDirectory(PlayerData PDI) {
		super(PDI);
		help = new HelpMenu(PDI, this).addPage(
				"Welcome to the Player Directory! \n"
				+ "\n Here you can look up the profiles of all the different "
				+ "players on this server. Their names are color-coded according to their relation to you."
				+ TextColor.ALLY(this.getPlayerData()) + "\n \n xxxxx == ALLY"
				+ TextColor.ENEMY(this.getPlayerData()) + "\n xxxx == ENEMY"
				+ TextColor.NEUTRAL(this.getPlayerData()) + "\n xxxx == NEUTRAL"
				+ TextColor.INSTRUCTION(this.getPlayerData()) + "\n \n These colors are"
				+ " decided by which country or town you belong to. It is not possible to ally "
				+ "or enemy someone individually." 
				);
		help.addPage("Names that show up with the format: [name]xx are rulers of a country. Names "
				+ "that show up as [name]x are rulers of a town."
				+ "\n \n You can also access your own profile here and change your settings, "
				+ "such as your menu colors. To learn more, cycle through the list to your name"
				+ "and select " + TextColor.OPTION(this.getPlayerData()) + "Profile.");
	}

	@Override
	public Menu getPreviousPrompt() {
		return new MainHud(PDI);
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public String getHeader() {
		return "Player Directory";
	}

	@Override
	public void addTabOptions() {
		for(PlayerID player:InspiredNations.playerdata.keySet()) {
			this.taboptions.add(player);
		}
	}

	@Override
	public void addOptions() {
		options.add(new PromptOption(this, "Profile", new PlayerProfile(PDI,this.getData().getPDI())));
	}

	@Override
	public void addActionManagers() {
		
	}
}
