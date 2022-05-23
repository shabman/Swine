/*
 * Copyright (C) 2022, Mustafa Malik (avia.shabbyman@gmail.com)
 */
package com.swine.runnable;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import com.swine.engine.Window;
import com.swine.engine.constants.InputAction;
import com.swine.engine.events.Child;
import com.swine.engine.events.InputListener;
import com.swine.engine.util.OS;

/**
 * 
 * @author Mustafa Malik
 * @version 1.0
 */
public class Run implements Child {
	
    private static String currentOSPath;
	
    private static final String OS_HOME     = System.getProperty("user.home");
    private static final String CACHE_NAME  = "Swine";
    
    private static final String WIN_CACHE   = OS_HOME + "\\AppData\\Local\\" + CACHE_NAME;
    private static final String OSX_CACHE   = OS_HOME + "/Library/Caches/" + CACHE_NAME;
    private static final String UNIX_CACHE  = OS_HOME + "/.cache/" + CACHE_NAME;
	
	/**
	 * Entry point to program
	 * @param args Command Line Arguments
	 */
	public static void main(String[] args) {
		
	    String os = OS.getOS();
	    
	    if (os.startsWith("mac")) {
	        currentOSPath = OSX_CACHE;
	        System.setProperty("apple.laf.useScreenMenuBar", "true");
	        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Swine Engine"); 
	    } else if (os.startsWith("win")) {
	        currentOSPath = WIN_CACHE;
	    } else if (os.startsWith("unix") || os.startsWith("linux")) {
	        currentOSPath = UNIX_CACHE;
	    } else {
	        JOptionPane.showMessageDialog(null, "Cannot determine the cache path", "Swine Engine", JOptionPane.ERROR_MESSAGE);
	        System.exit(1);
	    }
	    
        Path cache = Paths.get(currentOSPath);
        if (!Files.exists(cache)) {
            File dir = new File(currentOSPath);
            dir.mkdirs();
        }
		
		Window window = Window.build(currentOSPath);
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
