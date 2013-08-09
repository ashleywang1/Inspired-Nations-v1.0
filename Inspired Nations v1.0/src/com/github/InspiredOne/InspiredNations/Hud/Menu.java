package com.github.InspiredOne.InspiredNations.Hud;



import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public abstract class Menu extends StringPrompt {

	private static final String footer = MenuTools.addDivider("") + TextColor.ENDINSTRU + "Type 'exit' to leave, 'say' to chat, or 'back' to go back.";
	public InspiredNations plugin;
	public PlayerData PDI;
	
	public Menu(InspiredNations instance, PlayerData PDI) {
		this.plugin = instance;
		this.PDI = PDI;
	}
	
	/**
	 * 
	 * @return	the <code>String</code> of the prompt text as it would appear exactly
	 */
	public String getPromptText() {
		String space = MenuTools.space(plugin);
		String main = MenuTools.header(getHeader());
		String filler = this.getFiller();
		String end = footer;
		String errmsg = this.getError();
		
		return space + main + filler + end + errmsg;
	}
	
	@Override
	public String getPromptText(ConversationContext arg0) {
		return this.getPromptText();
	}
	
	@Override
	public Prompt acceptInput(ConversationContext arg0, String arg) {
		if (arg.startsWith("/")) {
			arg = arg.substring(1);
		}
		if (arg.equalsIgnoreCase("back")) {
			return this.getPreviousPrompt();
		}
		String[] args = arg.split(" ");
		if (args[0].equalsIgnoreCase("say"))  {
			if(args.length > 1) {
				//TODO send the chat message here
			}
			try {
				return this.getClass().getConstructor(InspiredNations.class, PlayerData.class).newInstance(plugin,PDI);
			} catch (Exception e) {
				
			}
		}
		
		return this.getNextPrompt(arg);
	}
	
	public String getError() {
		String output = (String) PDI.getCon().getContext().getSessionData("Error");
		if(output == null) return "";
		else {
			PDI.getCon().getContext().setSessionData("Error", null);
			return output;
		}
	}
	/**
	 * Returns a new instance of itself. Used for user input errors.
	 * @return	the <code>Menu</code> of itself
	 */
	public Menu getSelf() {
		return MenuTools.getMenuInstance(plugin, PDI, this.getClass());
	}
	
	public ConversationContext getContext() {
		return this.PDI.getCon().getContext();
	}
	
	public abstract String getHeader();
	
	public abstract String getFiller();
	
	/**
	 * Returns the prompt to go to when player uses "back"
	 * @return the <code>Prompt</code> that lead to this menu
	 * 
	 */
	public abstract Prompt getPreviousPrompt();
	
	/**
	 * 
	 * @param input	the <code>String</code> used to process the next <code>Prompt</code>
	 * @return		the next <code>Prompt</code> to be used in this conversation
	 */
	public abstract Prompt getNextPrompt(String input);
	/**Returns the next prompt based on input doing any actions as needed*/



	
	

}
