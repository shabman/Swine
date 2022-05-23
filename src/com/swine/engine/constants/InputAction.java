/*
 * Copyright (C) 2022, Mustafa Malik (avia.shabbyman@gmail.com)
 */
package com.swine.engine.constants;

/**
 * 
 * @author Mustafa Malik
 * @version 1.0
 */
public enum InputAction {

	ACTION_MOUSE_CLICKED(0),
	
	ACTION_MOUSE_PRESSED(1),
	
	ACTION_MOUSE_RELEASED(2),
	
	ACTION_MOUSE_ENTERED(3),
	
	ACTION_MOUSE_EXITED(4),
	
	ACTION_MOUSE_DRAGGED(5),
	
	ACTION_MOUSE_MOVED(6),
	
	ACTION_MOUSE_WHEEL(7),
	
	ACTION_KEY_PRESSED(8),
	
	ACTION_KEY_TYPED(9),
	
	ACTION_KEY_RELEASED(10);
	
	InputAction(int i) { }
}
