package com.github.InspiredOne.InspiredNations.Hud.Implem.NewGov;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;

public class WarningAlreadyOwnOne<T extends OwnerGov> extends OptionMenu {

	GovFactory<T> Govf;
	
	public  WarningAlreadyOwnOne(PlayerData PDI, GovFactory<T> Govf) {
		super(PDI);
		this.Govf = Govf;
	}

	@Override
	public String getPreOptionText() {
		return "Warning! Continuing will result in the loss of ownership of your current " + Govf.getGov().getTypeName() + ". " +
				"Are you sure you want to continue?";
	}

	@Override
	public String getHeader() {
		return "Warning!";
	}

	@Override
	public Menu getPreviousMenu() {
		return new PickSuperGov<T>(PDI, Govf);
	}

	@Override
	public boolean getPassBy() {
		OwnerGov gov = Govf.getGov();
		return gov.canAddWithoutConsequence(PDI);
	}

	@Override
	public Menu getPassTo() {
		return new PickName<T>(PDI, Govf);
	}

	@Override
	public void init() {
		options.add(new PromptOption(this, "Yes", new PickName<T>(PDI, Govf.withAccountCollection(PDI.getAccounts()))));
		options.add(new PromptOption(this, "No", new MainHud(PDI)));
	}

	@Override
	public OptionMenu getSelf() {
		return new WarningAlreadyOwnOne<>(PDI, Govf);
	}

}
