package com.github.InspiredOne.InspiredNations.ToolBox;

import org.bukkit.ChatColor;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.NameAlreadyTakenException;

public class Theme {

	private String HEADER = ChatColor.BLUE + "" + ChatColor.BOLD;
	private String SUBHEADER = ChatColor.YELLOW + "" + ChatColor.ITALIC + "" + ChatColor.BOLD;
	private String LABEL = ChatColor.RED + "";
	private String VALUE = ChatColor.GOLD + "";
	private String VALUEDESCRI = ChatColor.RED + "";
	private String DIVIDER = ChatColor.DARK_AQUA + "";
	private String OPTION = ChatColor.DARK_GREEN + "";
	private String OPTIONNUMBER = ChatColor.YELLOW + "";
	private String OPTIONDESCRI = ChatColor.GRAY + "";
	private String UNAVAIL = ChatColor.DARK_GRAY + "";
	private String UNAVAILREASON = ChatColor.GRAY + "";
	private String INSTRUCTION = ChatColor.YELLOW + "";
	private String ERROR = ChatColor.RED + "";
	private String ALERT = ChatColor.YELLOW + "";
	private String UNIT = ChatColor.YELLOW + "";
	private String ENDINSTRUCT = ChatColor.AQUA + "";
	
	
	public Theme() {
		
	}
	
	public String HEADER() {
		return HEADER;
	}

	public void setHEADER(String hEADER) {
		HEADER = hEADER;
	}
<<<<<<< Upstream, based on jeds_version/master

	public String SUBHEADER() {
		return SUBHEADER;
	}

	public void setSUBHEADER(String sUBHEADER) {
		SUBHEADER = sUBHEADER;
	}

	public String LABEL() {
		return LABEL;
	}

	public void setLABEL(String lABEL) {
		LABEL = lABEL;
	}

	public String VALUE() {
		return VALUE;
	}

	public void setVALUE(String vALUE) {
		VALUE = vALUE;
	}

	public String VALUEDESCRI() {
		return VALUEDESCRI;
	}

	public void setVALUEDESCRI(String vALUEDESCRI) {
		VALUEDESCRI = vALUEDESCRI;
	}

	public String DIVIDER() {
		return DIVIDER;
	}

	public void setDIVIDER(String dIVIDER) {
		DIVIDER = dIVIDER;
	}

	public String OPTION() {
		return OPTION;
	}

	public void setOPTION(String oPTION) {
		OPTION = oPTION;
	}

	public String OPTIONNUMBER() {
		return OPTIONNUMBER;
	}

	public void setOPTIONNUMBER(String oPTIONNUMBER) {
		OPTIONNUMBER = oPTIONNUMBER;
	}

	public String OPTIONDESCRI() {
		return OPTIONDESCRI;
	}

	public void setOPTIONDESCRI(String oPTIONDESCRI) {
		OPTIONDESCRI = oPTIONDESCRI;
	}

	public String UNAVAILABLE() {
		return UNAVAIL;
	}

	public void setUNAVAILABLE(String uNAVAIL) {
		UNAVAIL = uNAVAIL;
	}

	public String UNAVAILREASON() {
		return UNAVAILREASON;
	}

	public void setUNAVAILREASON(String uNAVAILREASON) {
		UNAVAILREASON = uNAVAILREASON;
	}

	public String INSTRUCTION() {
		return INSTRUCTION;
	}

	public void setINSTRUCTION(String iNSTRUCTION) {
		INSTRUCTION = iNSTRUCTION;
	}

	public String ERROR() {
		return ERROR;
	}

	public void setERROR(String eRROR) {
		ERROR = eRROR;
	}

	public String ALERT() {
		return ALERT;
	}

	public void setALERT(String aLERT) {
		ALERT = aLERT;
	}

	public String UNIT() {
		return UNIT;
	}

	public void setUNIT(String uNIT) {
		UNIT = uNIT;
	}

	public String ENDINSTRUCT() {
		return ENDINSTRUCT;
	}

	public void setENDINSTRUCT(String eNDINSTRUCT) {
		ENDINSTRUCT = eNDINSTRUCT;
	}
=======
	
	/**
	 * Header
	 */
	public abstract String HEADER();
	/**
	 * Subheader
	 */
	public abstract String SUBHEADER();
	/**
	 * Used to say what the proceeding value is.
	 */
	public abstract String LABEL();
	/**
	 * Anything that is calculated information displayed for the viewer 
	 */
	public abstract String VALUE();
	/**
	 * Additional text describing the value
	 */
	public abstract String VALUEDESCRI();
	/**
	 * Used for the divider between sections of the HUD
	 */
	public abstract String DIVIDER();
	
	/**
	 * The color of the options
	 */
	public abstract String OPTION();
	/**
	 * The color of the number correlating to the option
	 */
	public abstract String OPTIONNUMBER();
	/**
	 * Used for text that describes an option
	 */
	public abstract String OPTIONDESCRIP();
	/**
	 * Color to be used for an unavailable option
	 */
	public abstract String UNAVAILABLE();
	/**
	 * Color of text next to unavailable option describing why it's unavailable
	 */
	public abstract String UNAVAILREASON();
	/**
	 * Used to give instructions
	 */
	public abstract String INSTRUCTION();
	/**
	 * Used for error messages through out the plugin
	 */
	public abstract String ERROR();
	/**
	 * Used for alert messages through out the plugin
	 */
	public abstract String ALERT();
	/**
	 * Used for all units mentioned in the HUD
	 */
	public abstract String UNIT();
	/**
	 * Used for the line of text below the HUD
	 */
	public abstract String ENDINSTRU();

>>>>>>> eec44e3 Color Options
}
