/*
 * Copyright (C) 2022, Mustafa Malik (avia.shabbyman@gmail.com)
 */
package com.swine.engine.events;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Mustafa Malik
 * @version 1.0
 */
public class EngineListener {
	
	private static List<Child> file = new ArrayList<>();
	
	protected EngineListener() { }

	public static void add(Child clazz) {
		file.add(clazz);
	}
	
	public static void remove(Child clazz) {
		file.remove(clazz);
	}
	
	public static void remove(int i) {
		file.remove(i);
	}
	
	public static void fireEvent(String event) {
		switch (event.toLowerCase()) {
			case "create" -> file.forEach(c -> {
				if (c.isActive()) 
					c.create();
			});
			case "pause" -> file.forEach(c -> c.pause());
			case "destroy" -> file.forEach(c -> c.destroy());
		}
	}
	
	public static void fireEventOne(String event, Child prop) {
		switch (event.toLowerCase()) {
			case "create" -> {
				if (prop.isActive())
					prop.create();
			}
			case "pause" -> prop.pause();
			case "destroy" -> prop.destroy();
		}
	}
}
