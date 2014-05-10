package com.github.InspiredOne.InspiredNations.Economy.Nodes;

import java.math.BigDecimal;

import org.bukkit.configuration.file.YamlConfiguration;
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

	BigDecimal cost = BigDecimal.ZERO;
	boolean choseThis = false;
	boolean available = true;
	ItemStack item;
	
	public ItemNode(ItemStack item, Node[] elems) {
		/**Only put one node in the list*/

		super(elems);
		this.item = item;
		
	}
	
	public ItemNode(ItemStack item) {
		// {
		super(new Node[] {});
		this.item = item;
		choseThis = true;
		
		// }
	}
	
	@Override
	public double getCoef(NPC npc) {
		String output = "";
		Node.tier++;
		
		if(((ItemMarketplace) InspiredNations.Markets.get(0)).getCheapestUnit(item, npc) != null) {
			ItemSellable temp =((ItemMarketplace) InspiredNations.Markets.get(0)).getCheapestUnit(item, npc);
			cost = temp.getUnitPrice(npc.getCurrency(), npc.getLocation());
		}
		else {
			available = false;
		}
		
		if(cost.compareTo(new BigDecimal(thresh)) < 0) {
			cost = new BigDecimal(thresh);
		}
		double itemcoef = BigDecimal.ONE.divide(cost, InspiredNations.Exchange.mcup).doubleValue();
		double subcoef = 0;
		if(elems.length >0) {
			subcoef = elems[0].getCoef(npc);
		}
		if(!available) {
			Debug.node("ItemNode: " + item.getData().getItemType() + " not available.");
			return subcoef;
		}
		
		Debug.node("ItemNode: " + itemcoef + "*" + item.getData().getItemType().toString() + " + " + subcoef + "*X1");
		

		
		if(choseThis) {
			return itemcoef;
		}
			
		if(elems.length > 0) {
			if(elems[0].getCoef(npc) > itemcoef) {
				choseThis = false;
				return subcoef;
			}
			else {
				choseThis = true;
				return itemcoef;
			}
		}
		else {
			choseThis = true;
			return itemcoef;
		}
	}
	@Override
	public void buy(BigDecimal amount, Currency curren, NPC npc) {
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
			elems[0].buy(amount, curren, npc);
		}
	}

	@Override
	public void writeToConfig(String addr, YamlConfiguration output) {
		output.addDefault(addr + "ItemNode.item",item.getData().getItemType().toString());
		int id = 0;
		for(Node elem:this.elems) {
			elem.writeToConfig(addr + "ItemNode." + id, output);
		}
		
	}
}