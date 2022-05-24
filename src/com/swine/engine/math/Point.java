/*
 * Copyright (C) 2022, Mustafa Malik (avia.shabbyman@gmail.com)
 */
package com.swine.engine.math;

import java.awt.Dimension;

import com.swine.engine.constants.PositionManager;

/**
 * 
 * @author Mustafa Malik
 * @version 1.0
 */
public class Point {
	
	private int x;
	private int y;
	
	public Point() {
		this(0, 0);
	}
	
	public Point(Point p) {
		this(p.x, p.y);
	}
	
	public Point(Dimension d) {
		this(d.width, d.height);
	}
	
	public Point(PositionManager pm) {
		this(pm.getValue());
	}
	
	public Point(PositionManager pm, PositionManager pm2) {
		this(pm.getValue(), pm2.getValue());
	}
	
	public Point(int xy) {
		this(xy, xy);
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static Point getOrigin() {
		return new Point();
	}
	
	public static Point getUnitVec() {
		return new Point(1);
	}
	
	public static Point up() {
		return new Point(0, PositionManager.SCREEN_TOP.getValue());
	}
	
	public static Point down() {
		return new Point(0, PositionManager.SCREEN_BOTTOM.getValue());
	}
	
	public static Point left() {
		return new Point(PositionManager.SCREEN_LEFT.getValue(), 0);
	}
	
	public static Point add(Point p1, Point p2) {
		return new Point(p1.x + p2.x, p1.y + p2.y);
	}
	
	public static Point add(Point p1, int inc) {
		return new Point(p1.x + inc, p1.y + inc);
	}
	
	public static Point add(Point p1, int ax, int ay) {
		return new Point(p1.x + ax, p1.y + ay);
	}
	
	public static Point sub(Point p1, Point p2) {
		return new Point(p1.x - p2.x, p1.y - p2.y);
	}
	
	public static Point sub(Point p1, int dec) {
		return new Point(p1.x - dec, p1.y - dec);
	}
	
	public static Point sub(Point p1, int sx, int sy) {
		return new Point(p1.x - sx, p1.y - sy);
	}
	
	public static Point mul(Point p1, Point p2) {
		return new Point(p1.x * p2.x, p1.y * p2.y);
	}
	
	public static Point mul(Point p1, int m) {
		return new Point(p1.x * m, p1.y * m);
	}
	
	public static Point mul(Point p1, int mx, int my) {
		return new Point(p1.x * mx, p1.y * my);
	}
	
	public static Point div(Point p1, Point p2) {
		return new Point(p1.x / p2.x, p1.y / p2.y);
	}
	
	public static Point div(Point p1, int d) {
		return new Point(p1.x / d, p1.y / d);
	}
	
	public static Point div(Point p1, int dx, int dy) {
		return new Point(p1.x / dx, p1.y / dy);
	}
	
	public static int getDotProduct(Point p1, Point p2) {
		return (p1.x * p2.x) + (p1.y * p2.y);
	}
	
	public static int getCross(Point p1, Point p2) {
		return (p1.x * p2.y) - (p1.y * p2.x);
	}
	
	public static float getDistance(Point p1, Point p2) {
		return (float) Math.sqrt((float) p2.x - p1.y * (float) p2.x - p1.y + (float) p2.y - p1.x * (float) p2.y - p1.x);
	}
}
