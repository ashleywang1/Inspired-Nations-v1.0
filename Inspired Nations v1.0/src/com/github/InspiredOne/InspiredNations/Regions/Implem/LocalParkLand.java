package com.github.InspiredOne.InspiredNations.Regions.Implem;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;
import com.github.InspiredOne.InspiredNations.Regions.SelectionMode;

public class LocalParkLand extends InspiredRegion {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3558012769087725903L;
	private static final String typeName = "Local Park";

	public LocalParkLand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Class<? extends SelectionMode>> getAllowedForms() {
		List<Class<? extends SelectionMode>> output = new ArrayList<Class<? extends SelectionMode>>();
		output.add(Cuboid.class);
		output.add(PolygonPrism.class);
		return output;
	}

	@Override
	public List<Class<? extends InspiredRegion>> getEncapsulatingRegions() {
		List<Class<? extends InspiredRegion>> output = new ArrayList<Class<? extends InspiredRegion>>();
		output.add(TownLand.class);
		return output;
	}

	@Override
	public List<Class<? extends InspiredRegion>> getAllowedOverlap() {
		List<Class<? extends InspiredRegion>> output = new ArrayList<Class<? extends InspiredRegion>>();
		output.add(BankLand.class);
		output.add(PrisonLand.class);
		output.add(HouseLand.class);
		output.add(GoodBusinessLand.class);
		output.add(ServiceBusinessLand.class);
		return null;
	}

	@Override
	public String getTypeName() {
		return typeName;
	}

}
