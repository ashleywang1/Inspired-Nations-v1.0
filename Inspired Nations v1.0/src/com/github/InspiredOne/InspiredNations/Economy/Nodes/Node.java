package com.github.InspiredOne.InspiredNations.Economy.Nodes;

import java.math.BigDecimal;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Economy.NPC;

public abstract class Node {

	NPC npc;
	double thresh = 0.0000001;
	Node[] elems;
	
	public Node(NPC instance, Node[] elems) {
		npc = instance;
		this.elems = elems;
	}
	
	public abstract double getCoef();
	
	public abstract void buy(BigDecimal amount, Currency montype);
}
