package com.github.InspiredOne.InspiredNations.Economy;

import java.math.BigDecimal;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Economy.Nodes.CobDugNode;
import com.github.InspiredOne.InspiredNations.Economy.Nodes.ItemNode;
import com.github.InspiredOne.InspiredNations.Economy.Nodes.Node;
import com.github.InspiredOne.InspiredNations.Economy.Nodes.PerfectCompNode;
import com.github.InspiredOne.InspiredNations.Economy.Nodes.PerfectSubNode;

public class NodeRef {
	HashMap<Integer, Node> ref = new HashMap<Integer, Node>();
	InspiredNations plugin;
	ItemRefTrees item;
	
	public Node Begin;
	Node Smelt;
	
	public NodeRef() {
		item = new ItemRefTrees();
		
		Begin = new CobDugNode( new double[] {1,1,2}, new Node[] {
			// { Armor
				item.armor,
			// }
			// { Tools
				item.tools,
			// }
			// { Weapons
				// { Potions
						
				// }
				// { Hand-held Weapons

				// }
			// }
			// { Food
				
			// }
		});
	}
	
	public void allocateMoney(NPC npc) { 
		BigDecimal money = npc.getTotalUnallocatedMoney(npc.getCurrency()).divide(new BigDecimal(5));
		Debug.print(money.toString() + npc.getCurrency());
		Debug.print("///////////////////////////New NPC\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
		this.Begin.buy(money, npc.getCurrency(), npc);
	}
	
	public Node get(int id) {
		return ref.get(id);
	}
	
	public void put(int id, Node node) {
		ref.put(id, node);
	}
}
