package com.github.InspiredOne.InspiredNations.ToolBox;

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

}
