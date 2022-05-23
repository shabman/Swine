/*
 * Copyright (C) 2022, Mustafa Malik (avia.shabbyman@gmail.com)
 */
package com.swine.engine.rendering;

import java.awt.Component;

import javax.swing.JComponent;

/**
 * 
 * @author Mustafa Malik
 * @version 1.0
 */
public class Render {

	private RenderC r;
	
	public Render() { }
	
	public void blend() {
		this.getRenderCallback().c();
	}
	
	public void blend(JComponent comp) {
		comp.repaint();
		this.getRenderCallback().c();
	}
	
	public void blend(Component comp) {
		comp.repaint();
		this.getRenderCallback().c();
	}
	
	public void setRenderCallback(RenderC r) {
		this.r = r;
	}
	
	public RenderC getRenderCallback() {
		return r;
	}
}
