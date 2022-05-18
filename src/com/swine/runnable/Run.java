package com.swine.runnable;

import javax.swing.JOptionPane;

import com.swine.engine.Window;

/**
 * 
 * @author Mustafa Malik
 * @version 1.0
 */
public class Run {
	/**
	 * Entry point to program
	 * @param args Command Line Arguments
	 */
	public static void main(String[] args) {
		Window window = Window.build();
		System.out.println(window.toString());
		try {
			window.run();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Swine Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
