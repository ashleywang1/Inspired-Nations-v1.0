package com.github.InspiredOne.InspiredNations.Hud;



import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.conversations.Prompt;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.ContextData;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public abstract class Menu extends MessagePrompt {

	private static final String footer = MenuTools.addDivider("") + TextColor.ENDINSTRU + "Type 'exit' to leave, 'say' to chat, or 'back' to go back.";
	public PlayerData PDI;
	public InspiredNations plugin;
	
	public Menu(PlayerData PDI) {
		this.PDI = PDI;
		System.out.println("here5");
		this.plugin = InspiredNations.plugin;
		System.out.println("here6");
	}
	
	/**
	 * 
	 * @return	the <code>String</code> of the prompt text as it would appear exactly
	 */
	public String getPromptText() {
		String space = MenuTools.space();
		String main = MenuTools.header(getHeader());
		String filler = this.getFiller();
		String end = footer;
		String errmsg = this.getError();
		
		System.out.println("getPrompttext()");
		
		return space + main + filler + end + errmsg;
	}
	
	public abstract boolean passBy();
	
	public abstract Menu getPassTo();
	
	@Override
	public final boolean blocksForInput(ConversationContext arg0) {
		return !this.passBy();
	}
	@Override
	public final Prompt getNextPrompt(ConversationContext arg0) {
		return this.getPassTo();
	}
	@Override
	public final String getPromptText(ConversationContext arg0) {
		this.register();
		System.out.println("getPrompttext(arg0)");
		return this.getPromptText();
	}
	@Override
	public final Prompt acceptInput(ConversationContext arg0, String arg) {
		if(arg == null) {
			return this.getNextPrompt(arg0);
		}
		if (arg.startsWith("/")) {
			arg = arg.substring(1);
		}
		if (arg.equalsIgnoreCase("back")) {
			return this.checkBack();
		}
		String[] args = arg.split(" ");
		if (args[0].equalsIgnoreCase("say"))  {
			if(args.length > 1) {
				//TODO send the chat message here
			}
			return this.getSelf();
		}
		
		return this.getNextMenu(arg);
	}
	/**
	 * 
	 * @return	the <code>String</code> to be used for the error in the menu
	 */
	protected String getError() {
		String output = (String) this.getContext().getSessionData(ContextData.Error);
		this.setError(MenuError.NO_ERROR());
		return output;
	}
	/**
	 * Returns a new instance of itself. Used for user input errors.
	 * @return	the <code>Menu</code> of itself
	 */
	public final Menu getSelf() {
		return this;
	}
	/**
	 * 
	 * @return the <code>ConversationContext</code> of the player using this menu
	 */
	public final ConversationContext getContext() {
		this.PDI.getCon();
		return this.PDI.getCon().getContext();
	}
	/**
	 * 
	 * @param error	the <code>MenuError</code> to be used as the error
	 */
	public final void setError(String error) {
		this.getContext().setSessionData(ContextData.Error, error);
	}
	/**
	 * Looks at previous menu and determines if it should be skipped or not
	 * @return	the actual previous menu rather than just the one before this one
	 * in the menu graph
	 */
	private final Menu checkBack() {
		Menu previous = this.getPreviousMenu();
		if(!previous.passBy()) {
			return previous;
		}
		else {
			return previous.checkBack();
		}
	}
	/**
	 * Looks at next menu and determines if it should be skipped or not
	 * @param input	the <code>String</code> input by the player
	 * @return		the actual next Menu rather than just the one after this one
	 * in the menu graph
	 */
//	private final Menu checkNext(String input) {
//		Menu next = this.getNextMenu(input);
//		return next;
//		if(next.blocksForInput()) {
//			return next;
//		}
//		else {
//			return next.checkNext(input);
//		}
//	}
	/**
	 * 
	 * @return	the <code>String</code> to be used for the header of the menu
	 */
	public abstract String getHeader();
	/**
	 * 
	 * @return	the <code>String</code> to be used for the filler of the menu
	 */
	public abstract String getFiller();
	/**
	 * A method that allows events to be registered by a superclass.
	 */
	public abstract void register();
	/**
	 * Returns the prompt to go to when player uses "back"
	 * @return the <code>Prompt</code> that lead to this menu
	 * 
	 */
	public abstract Menu getPreviousMenu();
	/**
	 * 
	 * @param input	the <code>String</code> used to process the next <code>Prompt</code>
	 * @return		the next <code>Prompt</code> to be used in this conversation
	 */
	public abstract Menu getNextMenu(String input);



	
	

}
