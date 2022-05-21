package com.swine.engine.events;

import java.util.ArrayList;
import java.util.List;

import com.swine.engine.constants.InputAction;

public class InputListener {
	
	private static final List<Child> files = new ArrayList<>();
	
	public InputListener()  {}
	
	public static void add(Child c) {
		files.add(c);
	}
	
	public static void remove(Child c) {
		files.remove(c);
	}
	
	public static void remove(int i) {
		files.remove(i);
	}
	
	public static void fireEvent(InputAction code, Object data) {
		switch (code) {
			case ACTION_MOUSE -> files.forEach(c -> c.inputRequested(InputAction.ACTION_MOUSE, data));
			case ACTION_MOUSE_MOVE -> files.forEach(c -> c.inputRequested(InputAction.ACTION_MOUSE_MOVE, data));
			case ACTION_MOUSE_SCROLL -> files.forEach(c -> c.inputRequested(InputAction.ACTION_MOUSE_SCROLL, data));
			case ACTION_KEY_PRESS -> files.forEach(c -> c.inputRequested(InputAction.ACTION_KEY_PRESS, data));
		}
	}
	
	public static void fireEventOne(Child c, InputAction code, Object data) {
		switch (code) {
			case ACTION_MOUSE -> c.inputRequested(InputAction.ACTION_MOUSE, data);
			case ACTION_MOUSE_MOVE -> c.inputRequested(InputAction.ACTION_MOUSE_MOVE, data);
			case ACTION_MOUSE_SCROLL -> c.inputRequested(InputAction.ACTION_MOUSE_SCROLL, data);
			case ACTION_KEY_PRESS -> c.inputRequested(InputAction.ACTION_KEY_PRESS, data);
		}
	}
}
