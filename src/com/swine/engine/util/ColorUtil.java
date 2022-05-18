package com.swine.engine.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComponent;

/**
 * 
 * @author Mustafa Malik
 * @version 1.0
 */
public class ColorUtil {
	
	public ColorUtil() { }
	
	public static void paint(Component comp, Color c) {
		comp.setBackground(c);
		comp.repaint();
	}
	
	public static void paint(JComponent comp, Color c) {
		comp.setBackground(c);
		comp.repaint();
	}
}
