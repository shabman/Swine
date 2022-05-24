/*
 * Copyright (C) 2022, Mustafa Malik (avia.shabbyman@gmail.com)
 */
package com.swine.engine.constants;

/**
 * 
 * @author Mustafa Malik
 * @version 1.0
 */
public enum PositionManager {
	
	SCREEN_CENTER(0),
	
	SCREEN_TOP(-1),
	
	SCREEN_BOTTOM(1),
	
	SCREEN_LEFT(-1),
	
	SCREEN_RIGHT(1);
	
	private int value;
	
	PositionManager(int i) {
		value = i;
	}
	
	public int getValue() {
		return value;
	}
}
