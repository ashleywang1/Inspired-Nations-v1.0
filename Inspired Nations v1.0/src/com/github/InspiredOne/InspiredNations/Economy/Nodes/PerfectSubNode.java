package com.github.InspiredOne.InspiredNations.Economy.Nodes;

import java.math.BigDecimal;
import java.util.Vector;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Economy.NPC;

public class PerfectSubNode extends Node {

	double[] coefs;
	Node choice = null;
	
	public PerfectSubNode(NPC instance, double[] coefs, Node[] elems) {
		super(instance, elems);
		this.coefs = coefs;
	}

	@Override
	public double getCoef() {
		
		double coefTemp = 0;
		Vector<Node> ids = new Vector<Node>();
		Debug.print("elems length" + elems.length);
		for(int i = 0; i < elems.length; i++) {
			double holder = elems[i].getCoef()*coefs[i];
			if(holder > coefTemp) {
				ids.clear();
				ids.add(elems[i]);
				coefTemp = holder;
			}
			else if(holder == coefTemp) {
				ids.add(elems[i]);
			}
		}
		Debug.print("Ids size " + ids.size());
		
		if(ids.size() > 1) {
			int rand = ids.size();
			while(rand == ids.size()) {
				rand = (int) Math.floor(Math.random()*ids.size());
			}
			choice = ids.get(rand);
			return ids.get(rand).getCoef();
		}

		else if (ids.size() == 1) {
			Debug.print("PefectSubNode coeff of: " + ids.get(0).getCoef());
			choice = ids.get(0);
			return ids.get(0).getCoef();
		}
		else {
			Debug.print("PefectSubNode coeff 0");
			return 0;
		}
	}

	@Override
	public void buy(BigDecimal amount, Currency curren) {
		if(choice == null) {
			this.getCoef();
		}
		choice.buy(amount, curren);
	}
}
