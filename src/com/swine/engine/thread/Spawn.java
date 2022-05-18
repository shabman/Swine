package com.swine.engine.thread;

/**
 * 
 * @author Mustafa Malik
 * @version 1.0
 */
public class Spawn implements Runnable {
	
	private DispatchC c;
	
	public Spawn() { }

	@Override
	public void run() {
		c.c();
	}
	
	public Thread spawnThread() {
		return new Thread() {
			@Override
			public void run() {
				c.c();
			}
		};
	}
	
	public void setCallback(DispatchC c) {
		this.c = c;
	}
}
