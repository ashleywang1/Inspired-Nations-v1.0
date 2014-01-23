package com.github.InspiredOne.InspiredNations.Governments;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Economy.AccountCollection;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Exceptions.NotASuperGovException;
import com.github.InspiredOne.InspiredNations.Regions.InspiredRegion;
import com.github.InspiredOne.InspiredNations.Regions.Region;
import com.github.InspiredOne.InspiredNations.ToolBox.Datable;
import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;

/**
 * Used as the base class to build governments. Each level
 * of government must be an <code>InspiredGov</code> object. The abstract
 * methods to be developed dictate the hierarchy of governments
 * and how they are treated by the plugin.
 * 
 * @author InspiredOne
 *
 */
public abstract class InspiredGov implements Serializable, Nameable, Datable<InspiredGov> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5014430464149332251L;
	
	private AccountCollection accounts = new AccountCollection();
	private InspiredRegion region;
	private List<InspiredGov> facilities = new ArrayList<InspiredGov>();
	private HashMap<Class<? extends InspiredGov>, Double> taxrates = new HashMap<Class<? extends InspiredGov>, Double>();
	private InspiredGov supergov;
	private String name;
	private int protectionlevel = 0;
	private Currency currency = Currency.DEFAULT;
	
	/**
	 * @param instance	the plugin instance
	 */
	public InspiredGov() {
		for(Class<? extends InspiredGov> gov:this.getSubGovs()) {
			taxrates.put(gov, 1.0);
		}
	}
	/**
	 * 
	 * @return	a <code>HashMap<code> with each subgov class mapping to it's associated tax rate
	 */
	public HashMap<Class<? extends InspiredGov>, Double> getTaxrates() {
		return taxrates;
	}
	/**
	 * 
	 * @param taxrates	the new <code>HashMap</code> of class mappings to tax rates
	 */
	public void setTaxrates(HashMap<Class<? extends InspiredGov>, Double> taxrates) {
		this.taxrates = taxrates;
	}
	/**
	 * Returns the <code>InspiredRegion</code> associated with this government.
	 * The <code>InspiredRegion</code> dictates what selection types can be used
	 * and the nesting of the regions. 
	 * @return	the <code>InspiredRegion</code> associated with this government
	 */
	public InspiredRegion getRegion() {
		if(region == null) {
			region = Tools.getInstance(this.getInspiredRegion());
		}
		return region;
	}
	/**
	 * 
	 * @param region	the new <code>InspiredRegion</code> to be used
	 */
	public void setRegion(InspiredRegion region) {
		this.region = region;
	}
	/**
	 * If supergov has not been defined yet, then returns a new instance of type
	 * that getSuperGov() returns.
	 * @return the <code>InspiredGov</code> instance above this government
	 */
	public InspiredGov getSuperGovObj() {
		if(supergov == null) {
			return GovFactory.getGovInstance(this.getSuperGov());
		}
		else {
			return supergov;
		}
	}
	/**
	 * 
	 * @param supergov	the new <code>InspiredGov</code> instance above this government
	 */
	public void setSuperGovObj(InspiredGov supergov) {
		this.supergov = supergov;
	}
	/**
	 * Returns the set containing the names of all players who
	 * are directly under this governments control.
	 * @return	the <code>HashSet</code> of all subject names
	 */
	public HashSet<String> getSubjects() {
		return this.getSuperGovObj().getSubjects();
	}
	/**
	 * Returns a <code>List</code> of all <code>InspiredGovs</code> that have been created under this government.
	 * It does this by searching through the <code>HashMap</code> in the plugin class
	 * for any <code>InspiredGov</code> instance that returns this <code>InspiredGov</code> instance
	 * from <code>getSuperGovObj()</code>.
	 * @param key	the Class of InspiredGovs to find
	 * @return		a List of InspiredGovs of the type 
	 */
	public List<OwnerGov> getAllSubGovs(Class<? extends OwnerGov> key) {
		List<OwnerGov> output = new ArrayList<OwnerGov>();
		for(Iterator<InspiredGov> iter = InspiredNations.regiondata.get(key).iterator(); iter.hasNext(); ) {
			InspiredGov gov = iter.next();
			if (gov.getSuperGovObj().equals(this)) {
				output.add((OwnerGov) gov);
			}
		}
		
		return output;
		
	}
	/**
	 * Gets the class of the InspiredRegion for this government
	 * @return
	 */
	public abstract Class<? extends InspiredRegion> getInspiredRegion();
	/**
	 * Dictates which super government this government synchronizes it's economy with.
	 * The values of the economy fluctuate relative to other governments. The return must be either a government
	 * that is above this government or itself. 
	 * @return	the <code>InspiredGov</code> class that this class synchronizes it's economy variables
	 */
	public abstract Class<? extends InspiredGov> getCommonEcon();
	/**
	 * 
	 * @return	the <code>InspiredRegion</code> class that this government uses
	 */
	public abstract Class<? extends InspiredRegion> getSelfRegionType();
	/**
	 * Gets the <code>Facility</code> classes that this government uses as facilities.
	 * facilities are not taxed by this gov, but are taxed by the supergov. They share owners with
	 * this government. They can only be claimed by owners of this government.
	 * @return	a <code>List</code> of <code>InspiredGov</code> classes that serve as facilities for this gov
	 */
	public abstract List<Class<? extends Facility>> getGovFacilities();
	/**
	 * Gets the <code>InspiredGov</code>s that are under this government's control.
	 * SubGovs are taxed by this government and can be claimed by any of the subjects.
	 * They have their own set of owners who are responsible for paying up to this government.
	 * @return	a <code>List</code> of the <code>InspiredGov</code> that can be claimed by subjects
	 */
	public abstract List<Class<? extends OwnerGov>> getSubGovs();
	/**
	 * Returns the class of the supergov. If this is the highest form of government, then it should
	 * return a <code>Class<? extends GlobalGov></code>.
	 * @return	the <code>InspiredGov</code> class that is the supergov to this government
	 */
	public abstract Class<? extends OwnerGov> getSuperGov();
	/**
	 * Returns all the governments that could be claimed with 
	 * @return	a <code>List</code> of <code>InspiredGov</code> classes that can
	 * be used in parallel to this government.
	 */
	public abstract List<Class<? extends InspiredGov>> getSelfGovs();
	/**
	 * Returns the general gov that returns this gov in it's getSelfGovs() method.
	 * @return	the class of the general gov of this type.
	 */
	public Class<? extends InspiredGov> getGeneralGovType() {
		return this.getClass();
	}
	/**
	 * 
	 * @return	the <code>String</code> to be used as the name for this government in the menus
	 */
	public abstract String getTypeName();
	/**
	 * 
	 * @param subgov	the <code>InspiredGov</code> type to be searched for
	 * @return			the <code>double</code> representation of the tax rate
	 */
	public double getSubTaxRate(Class<? extends OwnerGov> subgov) {
		return taxrates.get(subgov);
	}
	/**
	 * 
	 * @return	the <code>double</code> representation of the tax rate effective on this government
	 */
	@SuppressWarnings("unchecked")
	public double getSuperTaxRate() {
		 return this.supergov.getSubTaxRate((Class<? extends OwnerGov>) this.getClass());
	}
	/**
	 * 
	 * @return a <code>List</code> of the <code>InspiredGov</code>s that are used as facilities
	 */
	public List<InspiredGov> getFacilities() {
		return facilities;
	}
	/**
	 * 
	 * @param facilities	the new <code>List</code> of the facilities
	 */
	public void setFacilities(List<InspiredGov> facilities) {
		this.facilities = facilities;
	}
	/**
	 * Sifts through all the InspiredGovs and coalesces all the governments that are one level below this
	 * government. Includes Facilities.
	 * @return
	 */
	public List<InspiredGov> getAllSubGovsAndFacilitiesJustBelow() {
		List<InspiredGov> output = new ArrayList<InspiredGov>();
		// Iterate over all the sub-gov types.
		for(Class<? extends OwnerGov> subType:this.getData().getSubGovs()) {
			// Iterate over every government of that type
			for(InspiredGov govToTest:InspiredNations.regiondata.get(subType)) {
				// Check if the government is under the particular superGov
				if(govToTest.isSubOf(this.getData())) {
					output.add(govToTest);
				}
			}
		}
		output.addAll(this.getFacilities());
		return output;
	}
	
	/**
	 * 
	 * @return	the <code>String</code> to be used for this government's name
	 */
	@Override
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name	the <code>String</code> to be used for the name of this government
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Determines if the given location is within the geographical region of this government.
	 * Returns true if the <code>Location</code> is within the region and false otherwise.
	 * @param location	the <code>Location</code> to be checked
	 * @return			the <code>boolean</code> indicating if the location is within the region
	 */
	public boolean contains(Location location) {
		if(this.region == null) {
			return false;
		}
		else {
			return this.region.contains(location);
		}
	}
	/**
	 * 
	 * @param amount	the <code>BigDecimal</code> amount to be paid up to the supergov
	 */
	public abstract void paySuper(BigDecimal amount);
	/**
	 * Gets a list of all the governments that are below this government (including itself)
	 * @return	A list of all the subgovs
	 */
	public final List<Class<? extends OwnerGov>> getAllSubGovs() {
		List<Class<? extends OwnerGov>> output = new ArrayList<Class<? extends OwnerGov>>();
		for(Class<? extends OwnerGov> gov:this.getSubGovs()) {
			output.add((Class<? extends OwnerGov>) gov);
			output.addAll(GovFactory.getGovInstance(gov).getAllSubGovs());
/*			for(Class<? extends InspiredGov> gov2:GovFactory.getGovInstance(gov).getSelfGovs()) {
				output.add((Class<? extends OwnerGov>) gov2);
				output.addAll(GovFactory.getGovInstance(gov2).getAllSubGovs());
			}*/
		}
		return output;
	}
	/**
	 * Returns a list of all govs that are subgovs of the parameter and
	 * are super govs of this gov or are this gov.
	 * @param govtop
	 * @return
	 */
	public final OwnerGov getSuperGovBelow(InspiredGov govtop) throws NotASuperGovException{
		InspiredGov govbottom = this;
		for(Class<? extends OwnerGov> govtype:govtop.getSubGovs()) {
			for(InspiredGov govtest:InspiredNations.regiondata.get(govtype)) {
				if(govbottom.equals(govtest)) {
					return (OwnerGov) govtest;
				}
				else if(govbottom.isSubOf(govtest)) {
					return (OwnerGov) govtest;
				}
			}
		}
		throw new NotASuperGovException();
	}
	
	public final boolean isSubOfClass(Class<? extends OwnerGov> gov) {
		if(gov.equals(InspiredNations.global.getClass())) {
			return true;
		}
		else if (this instanceof GlobalGov) {
			return false;
		}
		else if(gov.equals(this.getClass())) {
			return false;
		}
		else {
			return this.getSuperGovObj().isSubOfClass(gov);
		}

	}
	
	/**
	 * Registers all the region types into the plugin.regiondata hashmap.
	 * @param plugin	the <code>InspiredNations</code> plugin where
	 * the regiondata hashmap is stored
	 */
	public void register() {
		HashSet<InspiredGov> value = new HashSet<InspiredGov>();
		if(!InspiredNations.regiondata.containsKey(this.getClass())) {
			InspiredNations.regiondata.put(this.getClass(), value);
		}
		
		for(Class<? extends InspiredGov> cla:this.getSubGovs()) {
			InspiredGov obj = GovFactory.getGovInstance(cla);
			obj.register();
		}
		
		for(Class<? extends InspiredGov> cla:this.getGovFacilities()) {
			InspiredGov obj = GovFactory.getGovInstance(cla);
			obj.register();
		}
		
		for(Class<? extends InspiredGov> cla:this.getSelfGovs()) {
			if(!cla.equals(this.getClass())) {
				InspiredGov obj = GovFactory.getGovInstance(cla);
				obj.register();
			}
		}
	}
	
	public static boolean fromSameBranch(InspiredGov gov1, InspiredGov gov2) {
		return gov1.getSuperGovObj().equals(gov2.getSuperGovObj());
	}
	
	/**
	 * Determines if this government is below gov.
	 * @param gov	the government to check if is above this government
	 * @return		true if this is below gov
	 */
	public final boolean isSubOf(InspiredGov gov) {
		if(this.equals(InspiredNations.global)) {
			return false;
		}
		else if(gov.equals(InspiredNations.global)) {
			return true;
		}
		else if(gov.equals(this.getSuperGovObj())) {
			return true;
		}
		else {
			return this.getSuperGovObj().isSubOf(gov);
		}
	}
	
	public int getProtectionlevel() {
		return protectionlevel;
	}
	public void setProtectionlevel(int protectionlevel) {
		this.protectionlevel = protectionlevel;
	}
	
	public int getMilitaryLevel() {
		return this.getSuperGovObj().getMilitaryLevel();
	}
	public AccountCollection getAccounts() {
		return accounts;
	}
	public void setAccounts(AccountCollection accounts) {
		this.accounts = accounts;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public void setData(InspiredGov datatemp) {
	}
	public InspiredGov getData() {
		return this;
	}
	public void removeLand(InspiredGov gov, Region select) {
		if((this.getProtectionlevel() - gov.getMilitaryLevel()) < 1 || this == gov) {
			//TODO Do something with this function
		}
	}
}
