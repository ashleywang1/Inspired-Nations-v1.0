package com.github.InspiredOne.InspiredNations.Governments;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
/**
 * Facilities cannot have subgovs
 * @author Jedidiah E. Phillips
 *
 */
public abstract class Facility extends InspiredGov implements Serializable, Nameable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6514705223412901675L;

	@Override
	public Class<? extends InspiredGov> getCommonEcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final List<Class<? extends NoSubjects>> getSubGovs() {
		List<Class<? extends NoSubjects>> output = new ArrayList<Class<? extends NoSubjects>>();
		return output;
	}

	@Override
	public Class<? extends InspiredGov> getSuperGov() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void paySuper(BigDecimal amount) {
		// TODO Auto-generated method stub

	}

}
