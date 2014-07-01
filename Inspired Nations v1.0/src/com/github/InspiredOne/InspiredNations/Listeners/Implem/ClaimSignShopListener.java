package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.BalanceOutOfBoundsException;
import com.github.InspiredOne.InspiredNations.Exceptions.InspiredGovTooStrongException;
import com.github.InspiredOne.InspiredNations.Exceptions.InsufficientRefundAccountBalanceException;
import com.github.InspiredOne.InspiredNations.Exceptions.RegionOutOfEncapsulationBoundsException;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Player.PlayerID;
import com.github.InspiredOne.InspiredNations.Listeners.InspiredListener;
import com.github.InspiredOne.InspiredNations.Regions.Implem.SignShopLand;
import com.github.InspiredOne.InspiredNations.Regions.Implem.SignShopRegion;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools;

public class ClaimSignShopListener extends InspiredListener<ClaimSignShopManager> {

	public SignShopLand land = new SignShopLand();
	public SignShopRegion region = new SignShopRegion();
	PlayerData PDI;
	public ClaimSignShopListener(ClaimSignShopManager manager) {
		super(manager);
		PDI = manager.getActionMenu().PDI;
	}

	@EventHandler
	public void onSignPlace(BlockPlaceEvent event) {
		if(!this.getPlayerData().getPlayerID().equals(new PlayerID(event.getPlayer()))) {
			return;
		}
		if(event.getBlock().getType().equals(Material.SIGN)) {
			region.loca = new Point3D(event.getBlock().getLocation());
			
			try {
				this.manager.getActionMenu().shop.setLand(region);
			} catch (BalanceOutOfBoundsException e) {
				this.manager.getActionMenu().setError(MenuError.NOT_ENOUGH_MONEY(PDI));
			} catch (InspiredGovTooStrongException e) {
				this.manager.getActionMenu().setError(MenuError.GOV_TOO_STRONG(e.gov, PDI));
			} catch (RegionOutOfEncapsulationBoundsException e) {
				this.manager.getActionMenu().setError(MenuError.CLAIM_OUT_OF_BOUNDS(e.gov, PDI));
			} catch (InsufficientRefundAccountBalanceException e) {
				this.manager.getActionMenu().setError(MenuError.NOT_ENOUGH_MONEY(PDI));
			}
			Sign sign = (Sign) event.getBlock().getState();
			sign.setLine(0, manager.getActionMenu().shop.getName());
			sign.setLine(1, manager.getActionMenu().getData().getName());
			sign.setLine(2, manager.getActionMenu().getData().getItem().getAmount() + "");
			sign.setLine(3, Tools.cut(manager.getActionMenu().getData().getPrice(PDI.getCurrency(), region.getCharacteristicPoint())).toString());
		}
		manager.Update();
	}
}
