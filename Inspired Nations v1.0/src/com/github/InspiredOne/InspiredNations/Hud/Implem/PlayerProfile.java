package com.github.InspiredOne.InspiredNations.Hud.Implem;

import javax.swing.text.html.Option;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.ActionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.TaxTimerManager;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Player.PlayerID;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Player.RelationList;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Player.SettingsMenu;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.MenuUpdateManager;
import com.github.InspiredOne.InspiredNations.ToolBox.Datable;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public class PlayerProfile extends OptionMenu {
	
	PlayerData PDITarget;
	
	public <T extends Datable<PlayerID>> PlayerProfile(PlayerData PDI, T PDITarget) {
		super(PDI);
		this.PDITarget = PDITarget.getData().getPDI();
	}

	@Override
	public String getHeader() {
		return "Profile: " + PDITarget.getName();
	}

	@Override
	public Menu getPreviousMenu() {
		return new PlayerDirectory(PDI);
	}


	@Override
	public boolean getPassBy() {
		return false;
	}

	@Override
	public Menu getPassTo() {
		return this.getSelfPersist();
	}

	@Override
	public void actionResponse() {
		
	}

	@Override
	public void addActionManagers() {
		
	}

	@Override
	public String getPreOptionText() {
		String output = TextColor.LABEL(PDI) + "Citizenship: ";
		for(OwnerGov gov:this.PDITarget.getCitizenship()) {
			output = output.concat(gov.getDisplayName(PDI) + ", ");
		}
		if(output.length() > 0) {
			output = output.substring(0,output.length() - 2) + "\n";
		}
		
		return output;
	}

	@Override
	public void addOptions() {
		// TODO Auto-generated method stub
		
		this.options.add(new PromptOption(this, "Ally List", new RelationList(PDI, "Ally")));
		this.options.add(new PromptOption(this, "Enemy List", new RelationList(PDI, "Enemy")));
		
		if (this.PDITarget.equals(PDI)) {
			this.options.add(new PromptOption(this, "Settings", new SettingsMenu(PDI)));
		}
		
	}

}
