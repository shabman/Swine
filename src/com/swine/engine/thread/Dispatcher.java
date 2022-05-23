/*
 * Copyright (C) 2022, Mustafa Malik (avia.shabbyman@gmail.com)
 */
package com.swine.engine.thread;

import javax.swing.SwingUtilities;

/**
 * 
 * @author Mustafa Malik
 * @version 1.0
 */
public class Dispatcher {
	
	private Dispatcher() { }
	
	public static void dispatch(DispatchC callback) {
		SwingUtilities.invokeLater(() -> callback.c());
	}
}
