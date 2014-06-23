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

		
		Begin = new CobDugNode( new double[] {1,1}, new Node[] {
			// { Armor
			new CobDugNode( new double[] {1.25,2,1.75,1}, new Node[] { // Armor
				// { Helmet
				new PerfectSubNode( new double[] {1,3,4,6,10}, new Node[] { // Helmet
						new ItemNode( new ItemStack(Material.LEATHER_HELMET, 1, (short) 0), new Node[] { // Leather Helmet
							new PerfectCompNode( new double[] {5}, new Node[] {
								new ItemNode( new ItemStack(Material.LEATHER, 1, (short) 0)) // Leather
							})
						}),
						new ItemNode( new ItemStack(Material.CHAINMAIL_HELMET, 1, (short) 0), new Node[] { // Chain Helmet
							new PerfectCompNode( new double[] {5}, new Node[] {
								new ItemNode( new ItemStack(Material.FIRE, 1, (short) 0)) // Fire
							})
						}),
						new ItemNode( new ItemStack(Material.GOLD_HELMET, 1, (short) 0), new Node[] { // Gold Helmet
							new PerfectCompNode( new double[] {5}, new Node[] {
									item.goldIngot
							})
						}),
						new ItemNode( new ItemStack(Material.IRON_HELMET, 1, (short) 0), new Node[] { // Iron Helmet
							new PerfectCompNode( new double[] {5}, new Node[] {
									item.ironIngot
							})
						}),
						new ItemNode( new ItemStack(Material.DIAMOND_HELMET, 1, (short) 0), new Node[] { // Diamond Helmet
							new PerfectCompNode( new double[] {5}, new Node[] {
								item.diamond
							})
						})
				}),
				// }
				// { ChestPlate
				new PerfectSubNode( new double[] {1,3,4,6,10}, new Node[] { // Chestplate
						new ItemNode( new ItemStack(Material.LEATHER_CHESTPLATE, 1, (short) 0), new Node[] { // Leather Chestplate
							new PerfectCompNode( new double[] {8}, new Node[] {
								new ItemNode( new ItemStack(Material.LEATHER, 1, (short) 0)) // Leather
							})
						}),
						new ItemNode( new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1, (short) 0), new Node[] { // Chain Chestplate
							new PerfectCompNode( new double[] {8}, new Node[] {
								new ItemNode( new ItemStack(Material.FIRE, 1, (short) 0)) // Fire
							})
						}),
						new ItemNode( new ItemStack(Material.GOLD_CHESTPLATE, 1, (short) 0), new Node[] { // Gold Chestplate
							new PerfectCompNode( new double[] {8}, new Node[] {
									item.goldIngot
							})
						}),
						new ItemNode( new ItemStack(Material.IRON_CHESTPLATE, 1, (short) 0), new Node[] { // Iron Chestplate
							new PerfectCompNode( new double[] {8}, new Node[] {
									item.ironIngot
							})
						}),
						new ItemNode( new ItemStack(Material.DIAMOND_CHESTPLATE, 1, (short) 0), new Node[] { // Diamond Chestplate
							new PerfectCompNode( new double[] {8}, new Node[] {
								item.diamond
							})
						})
				}),
				// }
				// { Leggings
				new PerfectSubNode( new double[] {1,3,4,6,10}, new Node[] { // Leggings
						new ItemNode( new ItemStack(Material.LEATHER_LEGGINGS, 1, (short) 0), new Node[] { // Leather Leggings
							new PerfectCompNode( new double[] {7}, new Node[] {
								new ItemNode( new ItemStack(Material.LEATHER, 1, (short) 0)) // Leather
							})
						}),
						new ItemNode( new ItemStack(Material.CHAINMAIL_LEGGINGS, 1, (short) 0), new Node[] { // Chain Leggings
							new PerfectCompNode( new double[] {7}, new Node[] {
								new ItemNode( new ItemStack(Material.FIRE, 1, (short) 0)) // Fire
							})
						}),
						new ItemNode( new ItemStack(Material.GOLD_LEGGINGS, 1, (short) 0), new Node[] { // Gold Leggings
							new PerfectCompNode( new double[] {7}, new Node[] {
									item.goldIngot
							})
						}),
						new ItemNode( new ItemStack(Material.IRON_LEGGINGS, 1, (short) 0), new Node[] { // Iron Leggings
							new PerfectCompNode( new double[] {7}, new Node[] {
									item.ironIngot
							})
						}),
						new ItemNode( new ItemStack(Material.DIAMOND_LEGGINGS, 1, (short) 0), new Node[] { // Diamond Leggings
							new PerfectCompNode( new double[] {7}, new Node[] {
								item.diamond
							})
						})
				}),
				// }
				// { Boots
				new PerfectSubNode( new double[] {1,3,4,6,10}, new Node[] { // Boots
						new ItemNode( new ItemStack(Material.LEATHER_BOOTS, 1, (short) 0), new Node[] { // Leather Boots
							new PerfectCompNode( new double[] {4}, new Node[] {
								new ItemNode( new ItemStack(Material.LEATHER, 1, (short) 0)) // Leather
							})
						}),
						new ItemNode( new ItemStack(Material.CHAINMAIL_BOOTS, 1, (short) 0), new Node[] { // Chain Boots
							new PerfectCompNode( new double[] {4}, new Node[] {
								new ItemNode( new ItemStack(Material.FIRE, 1, (short) 0)) // Fire
							})
						}),
						new ItemNode( new ItemStack(Material.GOLD_BOOTS, 1, (short) 0), new Node[] { // Gold Boots
							new PerfectCompNode( new double[] {4}, new Node[] {
									item.goldIngot
							})
						}),
						new ItemNode( new ItemStack(Material.IRON_BOOTS, 1, (short) 0), new Node[] { // Iron Boots
							new PerfectCompNode( new double[] {4}, new Node[] {
									item.ironIngot
							})
						}),
						new ItemNode( new ItemStack(Material.DIAMOND_BOOTS, 1, (short) 0), new Node[] { // Diamond Boots
							new PerfectCompNode( new double[] {4}, new Node[] {
								item.diamond
							})
						})
				})
				// }
			}),
			// }
			// { Tools
			new CobDugNode( new double[] {5,4,3.5,2,1}, new Node[] { // Tools
					// { Pickaxe
					new PerfectSubNode( new double[] {1,2,2.5,5,7}, new Node[] { // Pickaxe
							new ItemNode( new ItemStack(Material.WOOD_PICKAXE, 1, (short) 0), new Node[] { // Wooden Pickaxe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										item.plank
								})
							}),
							new ItemNode( new ItemStack(Material.STONE_PICKAXE, 1, (short) 0), new Node[] { // Stone Pickaxe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										new ItemNode( new ItemStack(Material.COBBLESTONE, 1, (short) 0)) // Cobblestone
								})
							}),
							new ItemNode( new ItemStack(Material.GOLD_PICKAXE, 1, (short) 0), new Node[] { // Gold Pickaxe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										item.goldIngot
								})
							}),
							new ItemNode( new ItemStack(Material.IRON_PICKAXE, 1, (short) 0), new Node[] { // Iron Pickaxe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										item.ironIngot
								})
							}),
							new ItemNode( new ItemStack(Material.DIAMOND_PICKAXE, 1, (short) 0), new Node[] { // Diamond Pickaxe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										item.diamond
								})
							})
					}),
					// }
					// { Shovel
					new PerfectSubNode( new double[] {1,2,4,5,7}, new Node[] { // Shovel
							new ItemNode( new ItemStack(Material.WOOD_SPADE, 1, (short) 0), new Node[] { // Wooden Shovel
								new PerfectCompNode( new double[] {2,1}, new Node[] {
										item.stick,
										item.plank
								})
							}),
							new ItemNode( new ItemStack(Material.STONE_SPADE, 1, (short) 0), new Node[] { // Stone Shovel
								new PerfectCompNode( new double[] {2,1}, new Node[] {
										item.stick,
										new ItemNode( new ItemStack(Material.COBBLESTONE, 1, (short) 0)) // Cobblestone
								})
							}),
							new ItemNode( new ItemStack(Material.GOLD_SPADE, 1, (short) 0), new Node[] { // Gold Shovel
								new PerfectCompNode( new double[] {2,1}, new Node[] {
										item.stick,
										item.goldIngot
								})
							}),
							new ItemNode( new ItemStack(Material.IRON_SPADE, 1, (short) 0), new Node[] { // Iron Shovel
								new PerfectCompNode( new double[] {2,1}, new Node[] {
										item.stick,
										item.ironIngot
								})
							}),
							new ItemNode( new ItemStack(Material.DIAMOND_SPADE, 1, (short) 0), new Node[] { // Diamond Shovel
								new PerfectCompNode( new double[] {2,1}, new Node[] {
										item.stick,
										item.diamond
								})
							})
					}),
					// }
					// { Axe
					new PerfectSubNode( new double[] {1,2,4,5,7}, new Node[] { // Axe
							new ItemNode( new ItemStack(Material.WOOD_AXE, 1, (short) 0), new Node[] { // Wooden Axe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										item.plank
								})
							}),
							new ItemNode( new ItemStack(Material.STONE_AXE, 1, (short) 0), new Node[] { // Stone Axe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										new ItemNode( new ItemStack(Material.COBBLESTONE, 1, (short) 0)) // Cobblestone
								})
							}),
							new ItemNode( new ItemStack(Material.GOLD_AXE, 1, (short) 0), new Node[] { // Gold Axe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										item.goldIngot
								})
							}),
							new ItemNode( new ItemStack(Material.IRON_AXE, 1, (short) 0), new Node[] { // Iron Axe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										item.ironIngot
								})
							}),
							new ItemNode( new ItemStack(Material.DIAMOND_AXE, 1, (short) 0), new Node[] { // Diamond Axe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										item.diamond
								})
							})
					}),
					// }
					// { Shears
					new ItemNode( new ItemStack(Material.SHEARS, 1, (short) 0), new Node[] { // Shears
						new PerfectCompNode( new double[] {2}, new Node[] {
								item.ironIngot
						})
					}),
					// }
					// { Hoe
					new PerfectSubNode( new double[] {1,2,2.3,2.3,2.4}, new Node[] { // Hoe
							new ItemNode( new ItemStack(Material.WOOD_HOE, 1, (short) 0), new Node[] { // Wooden Hoe
								new PerfectCompNode( new double[] {2,2}, new Node[] {
										item.stick,
										item.plank
								})
							}),
							new ItemNode( new ItemStack(Material.STONE_HOE, 1, (short) 0), new Node[] { // Stone Hoe
								new PerfectCompNode( new double[] {2,2}, new Node[] {
										item.stick,
										new ItemNode( new ItemStack(Material.COBBLESTONE, 1, (short) 0)) // Cobblestone
								})
							}),
							new ItemNode( new ItemStack(Material.GOLD_HOE, 1, (short) 0), new Node[] { // Gold Hoe
								new PerfectCompNode( new double[] {2,2}, new Node[] {
										item.stick,
										item.goldIngot
								})
							}),
							new ItemNode( new ItemStack(Material.IRON_HOE, 1, (short) 0), new Node[] { // Iron Hoe
								new PerfectCompNode( new double[] {2,2}, new Node[] {
										item.stick,
										item.ironIngot
								})
							}),
							new ItemNode( new ItemStack(Material.DIAMOND_HOE, 1, (short) 0), new Node[] { // Diamond Hoe
								new PerfectCompNode( new double[] {2,2}, new Node[] {
										item.stick,
										item.diamond
								})
							}),
					})
					// }
			})
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
