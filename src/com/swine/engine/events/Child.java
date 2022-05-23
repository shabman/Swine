/*
 * Copyright (C) 2022, Mustafa Malik (avia.shabbyman@gmail.com)
 */
package com.swine.engine.events;

import com.swine.engine.constants.InputAction;

/**
 * 
 * @author Mustafa Malik
 * @version 1.0
 */
public interface Child {

	void create();
	
	void pause();
	
	void destroy();
	
	void inputRequested(InputAction e, Object args);
	
	boolean isActive();
}
