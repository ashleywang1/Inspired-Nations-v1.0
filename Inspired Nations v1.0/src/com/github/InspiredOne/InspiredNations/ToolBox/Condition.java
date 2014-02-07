package com.github.InspiredOne.InspiredNations.ToolBox;

public interface Condition<T> {
	public boolean rulePass(T test);
}
