package com.github.InspiredOne.InspiredNations.ToolBox;

import java.util.Comparator;

import com.github.InspiredOne.InspiredNations.PlayerData;

public abstract class SortTool<T> implements Nameable {
	
	public abstract Comparator<T> getComparator();
	
	@Override
	public String getDisplayName(PlayerData viewer) {
		return this.getName();
	}
	
	@Override
	public void setName(String name) {
		
	}

}
