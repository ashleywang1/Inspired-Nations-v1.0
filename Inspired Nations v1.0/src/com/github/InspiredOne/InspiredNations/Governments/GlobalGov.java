package com.github.InspiredOne.InspiredNations.Governments;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bukkit.OfflinePlayer;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Governments.Implem.Country;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;

public class GlobalGov extends OwnerSubjectGov {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4539890228965981190L;
	private static final String typeName = "Global Government"; 

	public GlobalGov() {
	}
	
	@Override
	public Class<? extends InspiredRegion> getSelfRegionType() {
		return null;
	}

	@Override
	public List<Class<? extends Facility>> getGovFacilities() {
		List<Class<? extends Facility>> output = new ArrayList<Class<? extends Facility>>();
		return output;
	}

	@Override
	public List<Class<? extends OwnerGov>> getSubGovs() {
		List<Class<? extends OwnerGov>> output = new ArrayList<Class<? extends OwnerGov>>();
		output.add(Country.class);
		return output;
	}

	@Override
	public HashSet<String> getSubjects() {
		
		HashSet<String> output = new HashSet<String>();
		
		for(OfflinePlayer player:InspiredNations.plugin.getServer().getOfflinePlayers()) {
			output.add(player.getName());
		}
		return output;
	}
	
	@Override
	public Class<? extends OwnerGov> getSuperGov() {
		return null;
	}

	@Override
	public String getTypeName() {
		return typeName;
	}

	@Override
	public Class<? extends InspiredGov> getCommonEcon() {
		return this.getClass();
	}

	@Override
	public List<Class<? extends InspiredGov>> getSelfGovs() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		output.add(this.getClass());
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getCommonGov() {
		return this.getClass();
	}

	@Override
	public Class<? extends InspiredRegion> getInspiredRegion() {
		return null;
	}

}
