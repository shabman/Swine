/*
 * Copyright (C) 2022, Mustafa Malik (avia.shabbyman@gmail.com)
 */
package com.swine.runnable;

import javax.swing.JOptionPane;

import com.swine.engine.Window;
import com.swine.engine.constants.InputAction;
import com.swine.engine.events.Child;
import com.swine.engine.events.InputListener;

/**
 * 
 * @author Mustafa Malik
 * @version 1.0
 */
public class Run implements Child {
	/**
	 * Entry point to program
	 * @param args Command Line Arguments
	 */
	public static void main(String[] args) {
		Window window = Window.build();
		System.out.println(window.toString());
		try {
			window.run();
			InputListener.add(new Run());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Swine Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void create() {
		
	}

	@Override
	public void pause() {

	}

	@Override
	public void destroy() {
	
	}

	@Override
	public void inputRequested(InputAction e, Object args) {
		System.out.println(e);
		System.out.println(args);
	}

	@Override
	public boolean isActive() {
		return false;
	}
}
