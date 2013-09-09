package com.github.InspiredOne.InspiredNations.Hud.ManageGov;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.IsDirectSuperGovException;
import com.github.InspiredOne.InspiredNations.Exceptions.NotASuperGovException;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.NoSubjects;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.OptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;

public class PickManageSuperGov extends OptionMenu {

	Class<? extends InspiredGov> GovType;
	InspiredGov supergov;
	public PickManageSuperGov(PlayerData PDI, Class<? extends InspiredGov> GovType, InspiredGov supergov) {
		super(PDI);
		this.GovType = GovType;
		this.supergov = supergov;
	}

	@Override
	public String getPreOptionText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Menu PreviousMenu() {
		if(supergov.getSuperGovObj().equals(InspiredNations.plugin.global)) {
			return null;
		}
		else {
			return new PickManageSuperGov(PDI, GovType, supergov.getSuperGovObj());
		}
	}

	@Override
	public String getHeader() {
		return "Pick Super Gov";
	}

	@Override
	public boolean getPassBy() {
		LinkedHashSet<InspiredGov> count = this.getAllSuperGovBelow();
		
		if(count.size() == 1) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public Menu getPassTo() {
		return new PickManageSuperGov(PDI, GovType, this.getAllSuperGovBelow().iterator().next());
	}

	@Override
	public void init() {
		System.out.println("Inside Init() 1 of: " + this.getHeader());
		LinkedHashSet<InspiredGov> options = this.getAllSuperGovBelow();
		System.out.println("Inside Init() 2 of: " + this.getHeader());
		System.out.println("options is empty: " + options.isEmpty());
		for(Iterator<InspiredGov> iter = options.iterator(); iter.hasNext();) {
			System.out.println("Inside Init() 3 of: " + this.getHeader());
			InspiredGov gov = iter.next();
			System.out.println("Inside Init() 4 of: " + this.getHeader());
			if(gov.getClass().equals(this.GovType)) {
				System.out.println("Inside Init() 5 of: " + this.getHeader());
				this.options.add(new PromptOption(this, gov.getName(), new ManageGov(PDI, (NoSubjects) gov)));
				System.out.println("Inside Init() 6 of: " + this.getHeader());
			}
			else {
				System.out.println("Inside Init() 7 of: " + this.getHeader());
				this.options.add(new PromptOption(this, gov.getName(), new PickManageSuperGov(PDI, this.GovType, gov)));
			}
		}
	}
	
	private final LinkedHashSet<InspiredGov> getAllSuperGovBelow() {
		LinkedHashSet<InspiredGov> count = new LinkedHashSet<InspiredGov>();
		
		for(NoSubjects gov:PDI.getOwnership(GovType)) {
			InspiredGov result = null;
			try {
				System.out.println("Inside getAllSuperGovBelow() 1 of: " + this.getHeader());
				result = gov.getSuperGovBelow(supergov.getClass());
				System.out.println(gov.isSubOf(supergov) + "Is gov sub of supergov");
				if(gov.isSubOf(supergov)) {
					count.add(result);
				}
			} catch (IsDirectSuperGovException e) {
				System.out.println("Inside getAllSuperGovBelow() 2 of: " + this.getHeader());
				result = gov;
				System.out.println(result.getSuperGovObj().equals(supergov) + "Is result's super equal to this supergov");
				if(result.getSuperGovObj().equals(supergov)) {
					count.add(result);
				}
					
			} catch (NotASuperGovException e) {
				System.out.println("Inside getAllSuperGovBelow() 2 of: " + this.getHeader());
				e.printStackTrace();
			}
			
		}
		
		return count;
	}

}
