package com.swine.engine.events;

/**
 * 
 * @author Mustafa Malik
 * @version 1.0
 */
public interface Child {

	void create();
	
	void pause();
	
	void destroy();
	
	boolean isActive();
}
