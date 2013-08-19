/*******************************************************************************
 * Copyright (c) 2013 InspiredOne.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     InspiredOne - initial API and implementation
 ******************************************************************************/
package com.github.InspiredOne.InspiredNations;


import java.util.HashMap;

import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Map;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.ContextData;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class ConversationBuilder {
	
	// Grabbing the instance of the plugin.
	InspiredNations plugin;
	PlayerData PDI;
	HashMap<Object, Object> initSessionData = new HashMap<Object, Object>();
	public ConversationBuilder(PlayerData PDI) {
		plugin = InspiredNations.plugin;
		this.PDI = PDI;
		this.initSessionData.put(ContextData.Error, MenuError.NO_ERROR());
	}
	
	public Conversation HudConvo() {
		ConversationFactory HudConvo = new ConversationFactory(plugin)
		.withModality(true)
		.withEscapeSequence("exit")
		.withFirstPrompt(new MainHud(PDI))
		.withLocalEcho(false)
		.withInitialSessionData(this.initSessionData);
		//.withTimeout(180);
		return HudConvo.buildConversation((Conversable) plugin.getServer().getPlayerExact(PDI.getName()));
	}
	
	public Conversation MapConvo() {
		ConversationFactory MapConvo = new ConversationFactory(plugin)
		.withModality(true)
		.withEscapeSequence("exit")
		.withFirstPrompt(new Map(PDI))
		.withLocalEcho(false)
		.withInitialSessionData(initSessionData);
		return MapConvo.buildConversation((Conversable) plugin.getServer().getPlayerExact(PDI.getName()));
	}
	
	/*public Conversation CountryClaim(Player player) {
		ConversationFactory CountryClaim = new ConversationFactory(plugin)
		.withModality(true) 
		//.withEscapeSequence("exit")
		.withFirstPrompt(new ClaimLand(plugin, player, 0))
		.withLocalEcho(false);
		//.withTimeout(180);
		return CountryClaim.buildConversation((Conversable) player);
	}
	
	public Conversation ManageCountry(Player player) {
		ConversationFactory ManageCountry = new ConversationFactory(plugin)
		.withModality(true)
		.withEscapeSequence("exit")
		.withFirstPrompt(new ManageCountry(plugin, player, 0))
		.withLocalEcho(false);
		//.withTimeout(180);
		return ManageCountry.buildConversation((Conversable) player);
	}*/
}