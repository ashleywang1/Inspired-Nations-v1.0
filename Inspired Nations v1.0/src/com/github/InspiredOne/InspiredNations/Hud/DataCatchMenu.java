package com.github.InspiredOne.InspiredNations.Hud;

import java.util.logging.Level;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;

/**
 * Menu to be used at the end of any generalized chain of menus to catch the data that is collected within them.
 * @author Jedidiah E. Phillips
 *
 * @param <T>	the type of data to be caught
 */
public abstract class DataCatchMenu<T> extends Menu implements DataStorage<T> {

	private T data;
	private Menu previous;

	public DataCatchMenu(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public String getHeader() {
		return "If you see this, something is broken";
	}

	@Override
	public String getFiller() {
		return "Tell your server's owner that something is wrong with the DataCatchMenu class";
	}

	@Override
	public void register() {

	}

	@Override
	public Menu getPreviousMenu() {
		if(this.previous == null) {
			InspiredNations.plugin.logger.log(Level.SEVERE, "Previous menu not set by HUD: contact plugin developer");
			return null;
		}
		else {
			return previous;
		}
	}

	@Override
	public Menu getNextMenu(String input) {
		return nextMenu();
	}
	
	public abstract Menu nextMenu();

	@Override
	public boolean getPassBy() {
		return true;
	}

	@Override
	public Menu getPassTo() {
		return nextMenu();
	}

	@Override
	public void init() {

	}

	@Override
	public T getData() {
		return this.data;
	}

	@Override
	public void setData(T data) {
		this.data = data;
	}

}
