package com.github.InspiredOne.InspiredNations.ToolBox;

import com.github.InspiredOne.InspiredNations.PlayerData;

/**
 * For testing purposes only. Delete after done playing with tabselectmenus
 * @author Jedidiah Phillips
 *
 */
public class TabTestNameable implements Nameable {

	private String name;
	public TabTestNameable(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		
	}

	@Override
	public String getDisplayName(PlayerData viewer) {
		// TODO Auto-generated method stub
		return null;
	}

}
