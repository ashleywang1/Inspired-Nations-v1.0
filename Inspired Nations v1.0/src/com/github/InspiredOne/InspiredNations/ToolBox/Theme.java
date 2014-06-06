package com.github.InspiredOne.InspiredNations.ToolBox;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.NameAlreadyTakenException;

public abstract class Theme implements Nameable {

	private String name;
	
	public Theme(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) throws NameAlreadyTakenException {

	}

	@Override
	public String getDisplayName(PlayerData viewer) {
		return name;
	}
}
