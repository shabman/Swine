/*
 * Copyright (C) 2022, Mustafa Malik (avia.shabbyman@gmail.com)
 */
package com.swine.engine.events;

import java.util.ArrayList;
import java.util.List;

import com.swine.engine.constants.InputAction;

/**
 * 
 * @author Mustafa Malik
 * @version 1.0
 */
public class InputListener {
	
	private static final List<Child> files = new ArrayList<>();
	
	public InputListener()  {}
	
	public static void add(Child c) {
		files.add(c);
	}
	
	public static void remove(Child c) {
		files.remove(c);
	}
	
	public static void remove(int i) {
		files.remove(i);
	}
	
	public static void fireEvent(InputAction code, Object data) {
		files.forEach(c -> c.inputRequested(code, data));
	}
	
	public static void fireEventOne(Child c, InputAction code, Object data) {
		c.inputRequested(code, data);
	}
}
