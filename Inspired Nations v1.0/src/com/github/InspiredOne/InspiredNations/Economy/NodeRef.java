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
						new ItemNode( new ItemStack(298, 1, (short) 0), new Node[] { // Leather Helmet
							new PerfectCompNode( new double[] {5}, new Node[] {
								new ItemNode( new ItemStack(334, 1, (short) 0)) // Leather
							})
						}),
						new ItemNode( new ItemStack(302, 1, (short) 0), new Node[] { // Chain Helmet
							new PerfectCompNode( new double[] {5}, new Node[] {
								new ItemNode( new ItemStack(51, 1, (short) 0)) // Fire
							})
						}),
						new ItemNode( new ItemStack(314, 1, (short) 0), new Node[] { // Gold Helmet
							new PerfectCompNode( new double[] {5}, new Node[] {
									item.goldIngot
							})
						}),
						new ItemNode( new ItemStack(306, 1, (short) 0), new Node[] { // Iron Helmet
							new PerfectCompNode( new double[] {5}, new Node[] {
									item.ironIngot
							})
						}),
						new ItemNode( new ItemStack(310, 1, (short) 0), new Node[] { // Diamond Helmet
							new PerfectCompNode( new double[] {5}, new Node[] {
								item.diamond
							})
						})
				}),
				// }
				// { ChestPlate
				new PerfectSubNode( new double[] {1,3,4,6,10}, new Node[] { // Chestplate
						new ItemNode( new ItemStack(299, 1, (short) 0), new Node[] { // Leather Chestplate
							new PerfectCompNode( new double[] {8}, new Node[] {
								new ItemNode( new ItemStack(334, 1, (short) 0)) // Leather
							})
						}),
						new ItemNode( new ItemStack(303, 1, (short) 0), new Node[] { // Chain Chestplate
							new PerfectCompNode( new double[] {8}, new Node[] {
								new ItemNode( new ItemStack(51, 1, (short) 0)) // Fire
							})
						}),
						new ItemNode( new ItemStack(315, 1, (short) 0), new Node[] { // Gold Chestplate
							new PerfectCompNode( new double[] {8}, new Node[] {
									item.goldIngot
							})
						}),
						new ItemNode( new ItemStack(307, 1, (short) 0), new Node[] { // Iron Chestplate
							new PerfectCompNode( new double[] {8}, new Node[] {
									item.ironIngot
							})
						}),
						new ItemNode( new ItemStack(311, 1, (short) 0), new Node[] { // Diamond Chestplate
							new PerfectCompNode( new double[] {8}, new Node[] {
								item.diamond
							})
						})
				}),
				// }
				// { Leggings
				new PerfectSubNode( new double[] {1,3,4,6,10}, new Node[] { // Leggings
						new ItemNode( new ItemStack(300, 1, (short) 0), new Node[] { // Leather Leggings
							new PerfectCompNode( new double[] {7}, new Node[] {
								new ItemNode( new ItemStack(334, 1, (short) 0)) // Leather
							})
						}),
						new ItemNode( new ItemStack(304, 1, (short) 0), new Node[] { // Chain Leggings
							new PerfectCompNode( new double[] {7}, new Node[] {
								new ItemNode( new ItemStack(51, 1, (short) 0)) // Fire
							})
						}),
						new ItemNode( new ItemStack(316, 1, (short) 0), new Node[] { // Gold Leggings
							new PerfectCompNode( new double[] {7}, new Node[] {
									item.goldIngot
							})
						}),
						new ItemNode( new ItemStack(308, 1, (short) 0), new Node[] { // Iron Leggings
							new PerfectCompNode( new double[] {7}, new Node[] {
									item.ironIngot
							})
						}),
						new ItemNode( new ItemStack(312, 1, (short) 0), new Node[] { // Diamond Leggings
							new PerfectCompNode( new double[] {7}, new Node[] {
								item.diamond
							})
						})
				}),
				// }
				// { Boots
				new PerfectSubNode( new double[] {1,3,4,6,10}, new Node[] { // Boots
						new ItemNode( new ItemStack(301, 1, (short) 0), new Node[] { // Leather Leggings
							new PerfectCompNode( new double[] {4}, new Node[] {
								new ItemNode( new ItemStack(334, 1, (short) 0)) // Leather
							})
						}),
						new ItemNode( new ItemStack(305, 1, (short) 0), new Node[] { // Chain Leggings
							new PerfectCompNode( new double[] {4}, new Node[] {
								new ItemNode( new ItemStack(51, 1, (short) 0)) // Fire
							})
						}),
						new ItemNode( new ItemStack(317, 1, (short) 0), new Node[] { // Gold Leggings
							new PerfectCompNode( new double[] {4}, new Node[] {
									item.goldIngot
							})
						}),
						new ItemNode( new ItemStack(309, 1, (short) 0), new Node[] { // Iron Leggings
							new PerfectCompNode( new double[] {4}, new Node[] {
									item.ironIngot
							})
						}),
						new ItemNode( new ItemStack(313, 1, (short) 0), new Node[] { // Diamond Leggings
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
							new ItemNode( new ItemStack(270, 1, (short) 0), new Node[] { // Wooden Pickaxe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										item.plank
								})
							}),
							new ItemNode( new ItemStack(274, 1, (short) 0), new Node[] { // Stone Pickaxe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										new ItemNode( new ItemStack(Material.COBBLESTONE, 1, (short) 0)) // Cobblestone
								})
							}),
							new ItemNode( new ItemStack(285, 1, (short) 0), new Node[] { // Gold Pickaxe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										item.goldIngot
								})
							}),
							new ItemNode( new ItemStack(257, 1, (short) 0), new Node[] { // Iron Pickaxe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										item.ironIngot
								})
							}),
							new ItemNode( new ItemStack(278, 1, (short) 0), new Node[] { // Diamond Pickaxe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										item.diamond
								})
							})
					}),
					// }
					// { Shovel
					new PerfectSubNode( new double[] {1,2,4,5,7}, new Node[] { // Shovel
							new ItemNode( new ItemStack(269, 1, (short) 0), new Node[] { // Wooden Shovel
								new PerfectCompNode( new double[] {2,1}, new Node[] {
										item.stick,
										item.plank
								})
							}),
							new ItemNode( new ItemStack(273, 1, (short) 0), new Node[] { // Stone Shovel
								new PerfectCompNode( new double[] {2,1}, new Node[] {
										item.stick,
										new ItemNode( new ItemStack(4, 1, (short) 0)) // Cobblestone
								})
							}),
							new ItemNode( new ItemStack(284, 1, (short) 0), new Node[] { // Gold Shovel
								new PerfectCompNode( new double[] {2,1}, new Node[] {
										item.stick,
										item.goldIngot
								})
							}),
							new ItemNode( new ItemStack(256, 1, (short) 0), new Node[] { // Iron Shovel
								new PerfectCompNode( new double[] {2,1}, new Node[] {
										item.stick,
										item.ironIngot
								})
							}),
							new ItemNode( new ItemStack(277, 1, (short) 0), new Node[] { // Diamond Shovel
								new PerfectCompNode( new double[] {2,1}, new Node[] {
										item.stick,
										item.diamond
								})
							})
					}),
					// }
					// { Axe
					new PerfectSubNode( new double[] {1,2,4,5,7}, new Node[] { // Axe
							new ItemNode( new ItemStack(271, 1, (short) 0), new Node[] { // Wooden Axe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										item.plank
								})
							}),
							new ItemNode( new ItemStack(275, 1, (short) 0), new Node[] { // Stone Axe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										new ItemNode( new ItemStack(4, 1, (short) 0)) // Cobblestone
								})
							}),
							new ItemNode( new ItemStack(286, 1, (short) 0), new Node[] { // Gold Axe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										item.goldIngot
								})
							}),
							new ItemNode( new ItemStack(258, 1, (short) 0), new Node[] { // Iron Axe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										item.ironIngot
								})
							}),
							new ItemNode( new ItemStack(279, 1, (short) 0), new Node[] { // Diamond Axe
								new PerfectCompNode( new double[] {2,3}, new Node[] {
										item.stick,
										item.diamond
								})
							})
					}),
					// }
					// { Shears
					new ItemNode( new ItemStack(359, 1, (short) 0), new Node[] { // Shears
						new PerfectCompNode( new double[] {2}, new Node[] {
								item.ironIngot
						})
					}),
					// }
					// { Hoe
					new PerfectSubNode( new double[] {1,2,2.3,2.3,2.4}, new Node[] { // Hoe
							new ItemNode( new ItemStack(290, 1, (short) 0), new Node[] { // Wooden Hoe
								new PerfectCompNode( new double[] {2,2}, new Node[] {
										item.stick,
										item.plank
								})
							}),
							new ItemNode( new ItemStack(291, 1, (short) 0), new Node[] { // Stone Hoe
								new PerfectCompNode( new double[] {2,2}, new Node[] {
										item.stick,
										new ItemNode( new ItemStack(4, 1, (short) 0)) // Cobblestone
								})
							}),
							new ItemNode( new ItemStack(294, 1, (short) 0), new Node[] { // Gold Hoe
								new PerfectCompNode( new double[] {2,2}, new Node[] {
										item.stick,
										item.goldIngot
								})
							}),
							new ItemNode( new ItemStack(292, 1, (short) 0), new Node[] { // Iron Hoe
								new PerfectCompNode( new double[] {2,2}, new Node[] {
										item.stick,
										item.ironIngot
								})
							}),
							new ItemNode( new ItemStack(293, 1, (short) 0), new Node[] { // Diamond Hoe
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
		BigDecimal money = npc.getTotalUnallocatedMoney(npc.getCurrency()).divide(new BigDecimal(50));
		Debug.print(money.toString());
		Debug.print("///////////////////////////New NPC\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
		this.Begin.buy(npc.getTotalUnallocatedMoney(npc.getCurrency()).divide(new BigDecimal(50)), npc.getCurrency(), npc);
	}
	
	public Node get(int id) {
		return ref.get(id);
	}
	
	public void put(int id, Node node) {
		ref.put(id, node);
	}
}
