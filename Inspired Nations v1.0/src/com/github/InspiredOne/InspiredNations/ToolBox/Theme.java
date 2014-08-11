package com.github.InspiredOne.InspiredNations.ToolBox;

import java.io.Serializable;

import org.bukkit.ChatColor;

public class Theme implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4217128928399815106L;
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
	private String INSTRUCTION = ChatColor.GREEN + "";
	private String ERROR = ChatColor.RED + "";
	private String ALERT = ChatColor.YELLOW + "";
	private String UNIT = ChatColor.YELLOW + "";
	private String ENDINSTRUCT = ChatColor.AQUA + "";
	private String ENEMY = ChatColor.RED + "";
	private String NEUTRAL = ChatColor.YELLOW + "";
	private String ALLY = ChatColor.LIGHT_PURPLE + "";
	
	
	public Theme() {
		
	}
	
	public String HEADER() {
		return HEADER;
	}

	public void setHEADER(String hEADER) {
		HEADER = hEADER;
	}
	
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

	public String ENEMY() {
		return ENEMY;
	}

	public void setENEMY(String eNEMY) {
		ENEMY = eNEMY;
	}

	public String NEUTRAL() {
		return NEUTRAL;
	}

	public void setNEUTRAL(String nEUTRAL) {
		NEUTRAL = nEUTRAL;
	}

	public String ALLY() {
		return ALLY;
	}

	public void setALLY(String aLLY) {
		ALLY = aLLY;
	}

}
