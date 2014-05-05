package com.github.InspiredOne.InspiredNations.Economy.Nodes;

import java.math.BigDecimal;

import org.bukkit.inventory.ItemStack;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Economy.Currency;
import com.github.InspiredOne.InspiredNations.Economy.NPC;
import com.github.InspiredOne.InspiredNations.Economy.Implem.ItemMarketplace;
import com.github.InspiredOne.InspiredNations.Economy.Implem.ItemSellable;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.NegativeMoneyTransferException;

public class ItemNode extends Node {

	BigDecimal cost;
	boolean choseThis;
	boolean available = true;
	ItemStack item;
	
	public ItemNode(NPC npc, ItemStack item, Node[] elems) {
		/**Only put one node in the list*/

		super(npc, elems);
		this.item = item;
		if(((ItemMarketplace) InspiredNations.Markets.get(0)).getCheapestUnit(item, npc) != null) {
			ItemSellable temp =((ItemMarketplace) InspiredNations.Markets.get(0)).getCheapestUnit(item, npc);
			Debug.print("Item type = " + this.item.getData().getItemType().toString());
			cost = temp.getUnitPrice(npc.getCurrency(), npc.getLocation());
		}
		else {
			available = false;
		}
		
	}
	
	public ItemNode(NPC npc, ItemStack item) {
		// {
		super(npc, new Node[] {});
		this.item = item;
		choseThis = true;
		if(((ItemMarketplace) InspiredNations.Markets.get(0)).getCheapestUnit(item, npc) != null) {
			ItemSellable temp =((ItemMarketplace) InspiredNations.Markets.get(0)).getCheapestUnit(item, npc);
			Debug.print("Item type = " + this.item.getData().getItemType().toString());
			cost = temp.getUnitPrice(npc.getCurrency(), npc.getLocation());
		}
		else {
			available = false;
		}
		
		// }
	}
	
	@Override
	public double getCoef() {

		if(!available) {
			Debug.print("Not available");
			return 0;
		}
		if(cost.compareTo(new BigDecimal(thresh)) < 0) {
			cost = new BigDecimal(thresh);
		}
		
		if(choseThis) {
			Debug.print("available  chose this" + this.item.getData().getItemType().toString());
			return BigDecimal.ONE.divide(cost).doubleValue();
		}
			
		if(elems[0].getCoef() > BigDecimal.ONE.divide(cost).doubleValue()) {
			Debug.print("available other pieces cheaper" + this.item.getData().getItemType().toString());
			choseThis = false;
			return elems[0].getCoef();
		}
		

		else {
			Debug.print("available else" + this.item.getData().getItemType().toString() + BigDecimal.ONE.divide(cost).doubleValue());
			choseThis = true;
			return BigDecimal.ONE.divide(cost).doubleValue();
		}
	}
	@SuppressWarnings("deprecation")
	@Override
	public void buy(BigDecimal amount, Currency curren) {
		Debug.print("Inside buy of " + this.item.getData().getItemType().toString());
		if(choseThis) {
			try {
				Debug.print("Inside choose this for: " + this.item.getData().getItemTypeId());
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