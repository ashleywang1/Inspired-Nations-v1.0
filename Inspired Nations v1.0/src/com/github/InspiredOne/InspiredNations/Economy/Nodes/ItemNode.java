package com.github.InspiredOne.InspiredNations.Economy.Nodes;

import java.math.BigDecimal;

import org.bukkit.inventory.ItemStack;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Economy.NPC;
import com.github.InspiredOne.InspiredNations.Economy.Implem.ItemMarketplace;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;

public class ItemNode extends Node {

	BigDecimal cost;
	boolean choseThis;
	ItemStack item;
	
	public ItemNode(NPC npc, ItemStack item, Node[] elems) {
		/**Only put one node in the list*/

		super(npc, elems);
		this.cost = ((ItemMarketplace) InspiredNations.Markets.get(0)).getCheapestUnit(item, npc)
				.getUnitPrice(npc.getCurrency(), npc.getLocation());
		this.item = item;
	}
	
	public ItemNode(NPC npc, ItemStack item) {
		// {
		super(npc, new Node[] {});
		choseThis = true;
		this.cost = ((ItemMarketplace) InspiredNations.Markets.get(0)).getCheapestUnit(item, npc)
				.getUnitPrice(npc.getCurrency(), npc.getLocation());
		this.item = item;
		// }
	}
	
	@Override
	public double getCoef() {

		if(cost.signum() < 0) {
			return 0;
		}
		if(cost.compareTo(new BigDecimal(thresh)) < 0) {
			cost = new BigDecimal(thresh);
		}
		
		if(choseThis) {
			return BigDecimal.ONE.divide(cost).doubleValue();
		}
			
		if(elems[0].getCoef() > BigDecimal.ONE.divide(cost).doubleValue()) {
			choseThis = false;
			return elems[0].getCoef();
		}
		

		else {
			choseThis = true;
			return BigDecimal.ONE.divide(cost).doubleValue();
		}
	}
	@Override
	public void buy(BigDecimal amount, Currency curren) {
		if(choseThis) {
			try {
				npc.saveMoneyFor(this.item, amount, curren);
			} catch (BalanceOutOfBoundsException e) {
				e.printStackTrace();
			} catch (NegativeMoneyTransferException e) {
				e.printStackTrace();
			}
		}
		else {
			elems[0].buy(amount, curren);
		}
	}
}