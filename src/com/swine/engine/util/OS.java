/*
 * Copyright (C) 2022, Mustafa Malik (avia.shabbyman@gmail.com)
 */
package com.swine.engine.util;

/**
 * 
 * @author Mustafa Malik
 * @version 1.0
 */
public class OS {

	private OS() { }
	
	public static String getOS() {
		return System.getProperty("os.name").toLowerCase();
	}
	
}
