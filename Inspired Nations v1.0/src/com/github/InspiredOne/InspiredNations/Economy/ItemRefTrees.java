package com.github.InspiredOne.InspiredNations.Economy;

import org.bukkit.inventory.ItemStack;

import com.github.InspiredOne.InspiredNations.Economy.Nodes.ItemNode;
import com.github.InspiredOne.InspiredNations.Economy.Nodes.Node;
import com.github.InspiredOne.InspiredNations.Economy.Nodes.PerfectCompNode;
import com.github.InspiredOne.InspiredNations.Economy.Nodes.PerfectSubNode;

public class ItemRefTrees {

	public Node smelt;
	public Node goldIngot;
	public Node ironIngot;
	public Node diamond;
	public Node stick;
	public Node plank;
	
	public ItemRefTrees() {
		
		plank = new PerfectSubNode( new double[] {1,1,1,1,1./3.,1,1,1,1}, new Node[] {
				new ItemNode( new ItemStack(5,1,(short) 0)), // Oak Plank
				new ItemNode( new ItemStack(5,1,(short) 1)), // Spruce Plank
				new ItemNode( new ItemStack(5,1,(short) 2)), // Birch Plank
				new ItemNode( new ItemStack(5,1,(short) 3)), // Jungle Plank
				new ItemNode( new ItemStack(333, 1, (short) 0)), // Boat
				new PerfectCompNode( new double[] {1./4.}, new Node[] {
						new ItemNode( new ItemStack(17,1,(short) 0)) // Oak Log
				}),
				new PerfectCompNode( new double[] {1./4.}, new Node[] {
						new ItemNode( new ItemStack(17,1,(short) 1)) // Spruce Log
				}),
				new PerfectCompNode( new double[] {1./4.}, new Node[] {
						new ItemNode( new ItemStack(17,1,(short) 2)) // Birch Log
				}),
				new PerfectCompNode( new double[] {1./4.}, new Node[] {
						new ItemNode( new ItemStack(17,1,(short) 3)) // Jungle Log
				})
				
		});
		diamond = new PerfectSubNode( new double[] {1,1,1}, new Node[] {
				new PerfectCompNode( new double[] {1}, new Node[] {
					new ItemNode( new ItemStack(264, 1, (short) 0)) // Diamond
				}),
				new PerfectCompNode( new double[] {1}, new Node[] {
					new ItemNode( new ItemStack(56, 1, (short) 0)) // Diamond Ore
				}),
				new PerfectCompNode( new double[] {1./9.}, new Node[] {
					new ItemNode( new ItemStack(57, 1, (short) 0)) // Diamond Block
				})
			});
		stick = new PerfectSubNode( new double[] {1, 1./2.}, new Node[] {
				new ItemNode( new ItemStack(280, 1, (short) 0), new Node[] {
					new PerfectCompNode( new double[] {2}, new Node[] {
							this.plank
					})
				}),
				new ItemNode( new ItemStack(333, 1, (short) 0))
		});
		
		smelt = new PerfectSubNode( new double[] {1./8., 1./8., 1./72., 1, 1, 2, 2./3.}, new Node[] {
					new ItemNode( new ItemStack(263, 1, (short) 0)), // Coal
					new ItemNode( new ItemStack(263, 1, (short) 1)), // Charcoal
					new ItemNode( new ItemStack(173, 1, (short) 0)), // Coal Block
					new ItemNode( new ItemStack(270, 1, (short) 0), new Node[] { // Wooden Pickaxe
						new PerfectCompNode( new double[] {2,3}, new Node[] {
								this.stick,
								this.plank
						})
					}),
					new ItemNode( new ItemStack(269, 1, (short) 0), new Node[] { // Wooden Shovel
						new PerfectCompNode( new double[] {2,1}, new Node[] {
								this.stick,
								this.plank
						})
					}),
					new ItemNode( new ItemStack(271, 1, (short) 0), new Node[] { // Wooden Axe
						new PerfectCompNode( new double[] {2,3}, new Node[] {
								this.stick,
								this.plank
						})
					}),
					new ItemNode( new ItemStack(290, 1, (short) 0), new Node[] { // Wooden Hoe
						new PerfectCompNode( new double[] {2,2}, new Node[] {
								this.stick,
								this.plank
						})
					}),
					this.stick,
					this.plank
				});
		goldIngot = new PerfectSubNode( new double[] {1,1,1,1}, new Node[] {
				new ItemNode( new ItemStack(266, 1, (short) 0)), // Gold Ingot
				new PerfectCompNode( new double[] {9}, new Node[] {
					new ItemNode( new ItemStack(371, 1, (short) 0)) // Gold Nugget	
				}),
				new PerfectCompNode( new double[] {1, 1}, new Node[] {
					new ItemNode( new ItemStack(14, 1, (short) 0)), // Gold Ore
					smelt
				}),
				new PerfectCompNode( new double[] {1./9.}, new Node[] {
					new ItemNode( new ItemStack(41, 1, (short) 0)) // Gold Block
				})
			});

		ironIngot = new PerfectSubNode( new double[] {1,1,1}, new Node[] {
				new PerfectCompNode( new double[] {1}, new Node[] {
					new ItemNode( new ItemStack(265, 1, (short) 0)) // Iron Ingot
				}),
				new PerfectCompNode( new double[] {1, 1./8.}, new Node[] {
					new ItemNode( new ItemStack(15, 1, (short) 0)), // Iron Ore
					smelt
				}),
				new PerfectCompNode( new double[] {1/9}, new Node[] {
					new ItemNode( new ItemStack(42, 1, (short) 0)) // Iron Block
				})
			});
		

		

	}
	
}
