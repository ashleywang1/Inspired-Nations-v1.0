package com.github.InspiredOne.InspiredNations.ToolBox;

import java.io.Serializable;
import java.util.HashSet;

import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;

public class MultiGovMap extends MultiMap<Class<? extends InspiredGov>, InspiredGov> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3307693156378400946L;

	@Override
	public HashSet<InspiredGov> get(Object key) {
		@SuppressWarnings("unchecked")
		InspiredGov gov = GovFactory.getGovInstance((Class<? extends InspiredGov>) key);
		HashSet<InspiredGov> output = new HashSet<InspiredGov>();

		for(Class<? extends InspiredGov> govtype:gov.getSelfGovs()) {
			output.addAll(set.get(govtype));
		}
		return output;
	}
	
}
