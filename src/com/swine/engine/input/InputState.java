package com.swine.engine.input;

import java.awt.Component;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;

public abstract class InputState implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
	
	public InputState(JComponent comp) {
		comp.addMouseListener(this);
		comp.addMouseMotionListener(this);
		comp.addMouseWheelListener(this);
		comp.addKeyListener(this);
	}
	
	public InputState(Component comp) {
		comp.addMouseListener(this);
		comp.addMouseMotionListener(this);
		comp.addMouseWheelListener(this);
		comp.addKeyListener(this);
	}
}
