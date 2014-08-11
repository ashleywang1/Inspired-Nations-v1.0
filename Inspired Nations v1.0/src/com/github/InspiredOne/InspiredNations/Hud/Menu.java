package com.github.InspiredOne.InspiredNations.Hud;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.MessagePrompt;
import org.bukkit.conversations.Prompt;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.PlayerOfflineException;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.MainHud;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools;
import com.github.InspiredOne.InspiredNations.ToolBox.Messaging.Alert;
import com.github.InspiredOne.InspiredNations.ToolBox.Tools.TextColor;

public abstract class Menu extends MessagePrompt {

	// Conversation Persistent
	private String footer;
	public PlayerData PDI;
	public InspiredNations plugin;
	// Menu Persistent: Only initialized once for this menu instance.
	private boolean loaded = false;
	public HelpMenu help;
	
	// Non-Persistent: Refreshed for every return back to this menu
	
	
	public Menu(PlayerData PDI) {
		this.PDI = PDI;
		this.plugin = InspiredNations.plugin;
		this.footer = MenuTools.addDivider("",PDI) + TextColor.ENDINSTRU(PDI) + "Keywords: exit, chat, say, help, back, hud.";
	}
	/**
	 * When the menu is initialized, all options, tab options, tab selects, and text is
	 * written up. Menu persistent variables (such as action managers and state data) is loaded
	 * first. Non-persistent variables like options and tab selects and text is loaded second because
	 * they rely on valid persistent variables
	 */
	public final void Initialize() {
		// Initialize the Menu Persistent Variables To be used for Prompt Text
		this.loadMenuPersistent();
		// Initialize the Non-Persistent Variables To be used for Prompt Text
		this.loadNonPersistent();

	}
	/**
	 * Loads menu persistent variabls suce as Acton Managers and state data.
	 */
	private final void loadMenuPersistent() {
		if(!loaded) {
			//this.PDI.getMsg().clearMenuVisible();
			this.menuPersistent();
			loaded = true;
		}
	}
	private final void unloadMenuPersistent() {
		this.unloadPersist();
		loaded = false;
	}
	private final void loadNonPersistent() {
		this.unloadNonPersist();
		this.nonPersistent();
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
/*	public Menu getNewSelf() {
		this.unloadNonPersist();
		this.unloadMenuPersistent();
		return this;
	}*/
	
	public final boolean passBy() {
		this.Initialize();
		return this.getPassBy();
	}
	
	@Override
	public final boolean blocksForInput(ConversationContext arg0) {
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
		String main = MenuTools.header(this.getHeader() + " " + InspiredNations.taxTimer.getTimeLeftReadout(), PDI);
		String filler = this.getFiller();
		String end = footer + " {" + PDI.getMsg().missedSize + "}";
		String errmsg = this.PDI.getMsg().pushMessageContent();
		if(!errmsg.isEmpty()) {
			end = end.concat("\n");
		}
		return space + main + filler + end + errmsg;
	}

	@Override
	protected final Prompt getNextPrompt(ConversationContext arg0) {
		return this.getPassTo();
	}
	
	@Override
	public final Prompt acceptInput(ConversationContext arg0, String arg) {
		Debug.print("Input accepted: " + arg);
		if(PDI.kill) {
			PDI.kill = false;
			return Menu.END_OF_CONVERSATION;
		}
		if(arg == null) {
			Menu output = this.getPassTo();
			this.unloadNonPersist();
			return output;
		}
		String[] args = arg.split(" ");
		if (args[0].equalsIgnoreCase("say"))  {
			if(args.length > 1) {
				PDI.getMsg().sendChatMessage(arg.substring(4));
			}
			this.unloadNonPersist();
			return this.getSelfPersist();
		}
		this.PDI.getMsg().clearMenuVisible();
		if (arg.startsWith("/")) {
			arg = arg.substring(1);
		}
		if (arg.equalsIgnoreCase("back")) {
			return this.checkBack();
		}
		if (arg.equalsIgnoreCase("help")) {
			Debug.print("return help page");
			return this.getHelp();
		}
		if (arg.equalsIgnoreCase("hud")) {
			this.unloadNonPersist();
			this.unloadMenuPersistent();
			return new MainHud(PDI);
		}
		if (arg.equalsIgnoreCase("chat")) {
			this.unloadNonPersist();
			this.unloadMenuPersistent();
			return new Chat(PDI, this.getSelfPersist());
		}
		if (arg.equalsIgnoreCase("exit")) {
			try {
				this.PDI.getMsg().missedSize = 0;
				for(Alert alert:PDI.getMsg().messages) {
					this.PDI.getPlayer().sendRawMessage(alert.getDisplayName(PDI));
				}
				this.PDI.getPlayer().sendRawMessage(MenuTools.space());
			} catch (PlayerOfflineException e) {
				e.printStackTrace();
			}
			this.unloadNonPersist();
			this.unloadMenuPersistent();
			return Menu.END_OF_CONVERSATION;
		}
		return this.checkNext(arg);
	}
	/**
	 * Looks at previous menu and determines if it should be skipped or not
	 * @return	the actual previous menu rather than just the one before this one
	 * in the menu graph
	 */
	private final Menu checkBack() {
		Menu previous = this.getPreviousMenu().getSelfPersist();
		this.unloadMenuPersistent();
		if(!previous.passBy()) {
			//previous.PDI = this.PDI; What is this I don't even.
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
	private final Menu checkNext(String input) {
		Menu next = this.getNextMenu(input);
		this.unloadNonPersist();
		if(this != next) {
			this.unloadMenuPersistent();
		}
		while(next.passBy()) {
			next = (Menu) next.getPassTo();
		}
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
		return this.getSelfPersist();
	}
	public final Menu setAlert(Alert alert) {
		this.PDI.getMsg().receiveAlert(alert, false);
		return this.getSelfPersist();
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
	public HelpMenu getHelp() {
		return help;
	}
}
