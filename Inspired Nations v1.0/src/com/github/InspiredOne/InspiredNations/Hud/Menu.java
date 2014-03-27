package com.github.InspiredOne.InspiredNations.Hud;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.conversations.Prompt;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public abstract class Menu extends MessagePrompt {

	// Conversation Persistent
	private static final String footer = MenuTools.addDivider("") + TextColor.ENDINSTRU + "Type 'exit' to leave, 'say' to chat, or 'back'/'hud' to go back.";
	public PlayerData PDI;
	public InspiredNations plugin;
	// Menu Persistent: Only initialized once for this menu instance.
	private boolean loaded = false;
	
	// Non-Persistent: Refreshed for every return back to this menu
	
	
	public Menu(PlayerData PDI) {
		this.PDI = PDI;
		this.plugin = InspiredNations.plugin;
	}
	
	public final void Initialize() {
		Debug.print("Inside Initialize 1");
		// Initialize the Non-Persistent Variables To be used for Prompt Text
		this.loadNonPersistent();
		Debug.print("Inside Initialize 2");
		// Initialize the Menu Persistent Variables To be used for Prompt Text
		this.loadMenuPersistent();
		
		Debug.print("Inside Initialize 3");

	}
	private final void loadMenuPersistent() {
		if(!loaded) {
			this.menuPersistent();
			loaded = true;
		}
	}
	private final void unloadMenuPersistent() {
		this.unloadPersist();
		loaded = false;
	}
	private final void loadNonPersistent() {
		Debug.print("Inside loadNonPersistent 1");
		this.unloadNonPersist();
		Debug.print("Inside loadNonPersistent 2");
		this.nonPersistent();
		Debug.print("Inside loadNonPersistent 3");
	}
	/**
	 * Used to unload the Menu, clearing all Non-Persistent Variables and unregistering events.
	 */
	public abstract void unloadNonPersist();
	/**
	 * Used to unload the Menu, clearing both Non-Persistent and Persistent Variables. 
	 */
	public abstract void unloadPersist();
	/**
	 * Used to set up menuPersistent variables such as ActionManagers
	 */
	public abstract void menuPersistent();
	/**
	 * Used to set up nonPersistent variables such as options
	 */
	public abstract void nonPersistent();
	
	public Menu getSelfPersist() {
		return this;
	}
	/**
	 * Unloads Menu Persistent Variables so that menu is completely refreshed.
	 * @return
	 */
	public Menu getNewSelf() {
		this.unloadNonPersist();
		this.unloadMenuPersistent();
		return this;
	}
	
	public final boolean passBy() {
		this.Initialize();
		return this.getPassBy();
	}
	
	@Override
	public final boolean blocksForInput(ConversationContext arg0) {
		this.Initialize();
		return !this.passBy();
	}
	
	@Override
	public final String getPromptText(ConversationContext arg0) {
		this.Initialize();
		return this.getPromptText();
	}
	
	/**
	 * 
	 * @return	the <code>String</code> of the prompt text as it would appear exactly
	 */
	public final String getPromptText() {
		String space = MenuTools.space();
		String main = MenuTools.header(this.getHeader() + " " + InspiredNations.taxTimer.getTimeLeftReadout());
		String filler = this.getFiller();
		String end = footer;
		String errmsg = this.getError();
		
		return space + main + filler + end + errmsg;
	}

	@Override
	protected final Prompt getNextPrompt(ConversationContext arg0) {
		return this.getPassTo();
	}
	
	@Override
	public final Prompt acceptInput(ConversationContext arg0, String arg) {
		Debug.print("inside Accept Input");
		this.setError(MenuError.NO_ERROR());
		if(arg == null) {
			this.unloadNonPersist();
			return this.getNextPrompt(arg0);
		}
		Debug.print("inside Accept Input 2");
		if (arg.startsWith("/")) {
			arg = arg.substring(1);
		}
		Debug.print("inside Accept Input 3");
		if (arg.equalsIgnoreCase("back")) {
			this.unloadNonPersist();
			return this.checkBack();
		}
		Debug.print("inside Accept Input 4");
		if (arg.equalsIgnoreCase("hud")) {
			this.unloadNonPersist();
			return new MainHud(PDI);
		}
		Debug.print("inside Accept Input 5");
		if (arg.equalsIgnoreCase("exit")) {
			this.unloadNonPersist();
			return Menu.END_OF_CONVERSATION;
		}
		Debug.print("inside Accept Input 6");
		String[] args = arg.split(" ");
		if (args[0].equalsIgnoreCase("say"))  {
			if(args.length > 1) {
				PDI.getMsg().sendChatMessage(arg.substring(4));
			}
			this.unloadNonPersist();
			return this.getSelfPersist();
		}
		Debug.print("inside Accept Input 7");
		return this.checkNext(arg);
	}
	/**
	 * Looks at previous menu and determines if it should be skipped or not
	 * @return	the actual previous menu rather than just the one before this one
	 * in the menu graph
	 */
	private final Menu checkBack() {
		Menu previous = this.getPreviousMenu();
		Debug.print("In CheckBack();");
		if(!previous.passBy()) {
			Debug.print("In CheckBack() return previous;");
			previous.PDI = this.PDI;
			return previous;
		}
		else {
			Debug.print("In CheckBack() return previous.checkback;");
			return previous.checkBack();
		}
	}
	/**
	 * Looks at next menu and determines if it should be skipped or not
	 * @param input	the <code>String</code> input by the player
	 * @return		the actual next Menu rather than just the one after this one
	 * in the menu graph
	 */
	private final Menu checkNext(String input) {
		Debug.print("in checkNext");
		Menu next = this.getNextMenu(input);
		Debug.print("in checkNext 2");
		this.unloadNonPersist();
		PDI.getCon().getContext();
		Debug.print("in checkNext 3");
		while(next.passBy()) {
			Debug.print("in checkNext 5");
			next = (Menu) next.getNextPrompt(PDI.getCon().getContext());
		}
		Debug.print("in checkNext 4");
		next.PDI = this.PDI;
		return next;
	}
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
	/**
	 * Determines if this menu should be passed by or not
	 * @return	<code>true</code> if it will be passed by
	 */
	public abstract boolean getPassBy();
	/**
	 * Get's the menu that will be used if getPassBy() returns <code>true</code>
	 * @return	the menu passed to
	 */
	public abstract Menu getPassTo();
	
	/**
	 * 
	 * @return	the <code>String</code> to be used for the error in the menu
	 */
	private final String getError() {
		String output = this.PDI.getMsg().getNotif();		
		return output;
	}

	/**
	 * 
	 * @return	the <code>String</code> to be used for the non-persistent header of the menu.
	 */
	public abstract String getHeader();
	/**
	 * 
	 * @return	the <code>String</code> to be used for the non-persistent filler of the menu
	 */
	public abstract String getFiller();
	
	
	// Shortcut methods. Not essential for Menu, but useful to do things easily.
	/**
	 * 
	 * @param error	the <code>MenuError</code> to be used as the error
	 */
	public final Menu setError(String error) {
		this.PDI.getMsg().receiveError(error);
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
	
	public final PlayerData getPlayerData() {
		return PDI;
	}
}
