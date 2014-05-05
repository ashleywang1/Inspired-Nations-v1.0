package com.github.InspiredOne.InspiredNations.Economy.Nodes;

import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Economy.NPC;

public class CobDugNode extends Node {

	
	double[] power;
	double[] ratios;
	
	public CobDugNode(NPC instance, double[] power, Node[] elems) {
		super(instance, elems);
		this.power = power;
	}

	@Override
	public double getCoef() {
		ratios = new double[elems.length];
		double coeftemp = 1;
		
		for(int i = 0; i<elems.length; i++) {
			double holder = Math.pow(elems[i].getCoef(), power[i]);
			Debug.print(holder + " = Holder of CobDugRatio Determiner");
			if(holder >= thresh) {
				ratios[i] = 1;
				coeftemp = coeftemp*holder;
			}
			else {
				ratios[i] = 0;
			}
		}
		Debug.print("CobDug coef: " + coeftemp);
		return coeftemp; 
	}

	@Override
	public void buy(BigDecimal amount, Currency curren) {
		double divisor = 0;
		
		if(ratios == null) {
			this.getCoef();
		}
		
		for(int i = 0; i < power.length; i++) {
			if(ratios[i] > 0) {
				divisor += power[i];
			}
			else {
				power[i] = 0;
			}
		}	
		for(int i = 0; i < elems.length; i++) {
			if(ratios[i] > 0) {
				Debug.print("Power: " + power[i] + " Divisor: " + divisor);
				elems[i].buy(amount.multiply(new BigDecimal(power[i])).divide(new BigDecimal(divisor)), curren);
			}
		}
	}
}
