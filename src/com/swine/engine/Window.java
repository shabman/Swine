/*
 * Copyright (C) 2022, Mustafa Malik (avia.shabbyman@gmail.com)
 */
package com.swine.engine;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.swine.engine.awtmod.WindowListenerMod;
import com.swine.engine.constants.InputAction;
import com.swine.engine.events.EngineListener;
import com.swine.engine.events.InputListener;
import com.swine.engine.rendering.Render;
import com.swine.engine.thread.Dispatcher;
import com.swine.engine.thread.Spawn;
import com.swine.engine.util.ColorUtil;
import com.swine.engine.util.OS;
import com.swine.engine.util.Version;
import com.swine.exceptions.CheckFailedException;

/**
 * 
 * @author Mustafa Malik
 * @version 1.0
 */
public final class Window {

	private static Window window = null;
	private static String os;
	private static String path;
	private static double version;
	private static final Spawn spawn = new Spawn();
	private static final Time time = new Time();
	
	private String title = null;
	private boolean run = true;
	
	private GraphicsDevice gd = null;
	private Point loc = null;
	private Dimension size = null;
	private Dimension halfSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private int i = 0;
	private final double FPS = 1.0d / 60.0d;
	private double previous = time.getTime();
	private double steps = 0.0;

	private final Render render = new Render();
	
	public JFrame frame;
	
	private Window() { }
	
	public static Window build(String paf) {
		if (window == null)
			window = new Window();
		os = OS.getOS();
		path = paf;
		version = Version.getVersion();
	
		return window;
	}
	
	public void run() throws Exception {
		if (!check()) {
			throw new CheckFailedException(
					"\n\tPlease check if OS is compatible"
					+ "\n\t Avoid using old versions"
			);
		}
		Dispatcher.dispatch(() -> {
			frame = new JFrame((title != null) ? title : "Swine 2D Engine", (gd != null) ? gd.getDefaultConfiguration() : null);
			frame.setSize((size != null) ? size : new Dimension(halfSize.width / 2, halfSize.height / 2));
			if (loc != null)
				frame.setLocation(loc);
			else
				frame.setLocationRelativeTo(frame);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.addWindowListener(new WindowListenerMod() {
				@Override
				public void windowClosing(WindowEvent e) {
					EngineListener.fireEvent("destroy");
				}
			});
			frame.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					InputListener.fireEvent(InputAction.ACTION_MOUSE_CLICKED, e);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					InputListener.fireEvent(InputAction.ACTION_MOUSE_PRESSED, e);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					InputListener.fireEvent(InputAction.ACTION_MOUSE_RELEASED, e);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					InputListener.fireEvent(InputAction.ACTION_MOUSE_ENTERED, e);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					InputListener.fireEvent(InputAction.ACTION_MOUSE_EXITED, e);
				}
				
			});
			frame.addMouseMotionListener(new MouseMotionListener() {
				@Override
				public void mouseDragged(MouseEvent e) {
					InputListener.fireEvent(InputAction.ACTION_MOUSE_DRAGGED, e);
				}

				@Override
				public void mouseMoved(MouseEvent e) {
					InputListener.fireEvent(InputAction.ACTION_MOUSE_MOVED, e);
				}
				
			});
			frame.addMouseWheelListener(new MouseWheelListener() {
				@Override
				public void mouseWheelMoved(MouseWheelEvent e) {
					InputListener.fireEvent(InputAction.ACTION_MOUSE_WHEEL, e);
				}				
			});
			frame.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					InputListener.fireEvent(InputAction.ACTION_KEY_TYPED, e);
				}

				@Override
				public void keyPressed(KeyEvent e) {
					InputListener.fireEvent(InputAction.ACTION_KEY_PRESSED, e);
				}

				@Override
				public void keyReleased(KeyEvent e) {
					InputListener.fireEvent(InputAction.ACTION_KEY_RELEASED, e);		
				}
			});
			
			EngineListener.fireEvent("create");
			
			
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image image;
			image = toolkit.getImage(path + "/cursor-arrow1.png");
			Cursor c = toolkit.createCustomCursor(image , new Point(frame.getContentPane().getX(), 
					frame.getContentPane().getY()), "img");
			frame.getContentPane().setCursor (c);
	
			frame.setVisible(true);
			
			loop();
		});
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				run = false;
			}
		});
	}
	
	
	public void loop() {
		// WARNING: Do NOT use JOptionPane as this class YIELDS the thread.
		spawn.setCallback(() -> {
			while (run) {
				double loopStartTime = time.getTime();
				double elapsed = loopStartTime - previous;
				previous = loopStartTime;
				steps += elapsed;
				
				while (steps >= FPS) {
					// UPDATE GAME STATE
					render.setRenderCallback(() -> {
						ColorUtil.paint(frame.getContentPane(), new Color(i, i, i));
						i = 1 - -i;
						if (i == 255) i = 0;
					});
					render.blend();
					steps -= FPS;
					//System.out.println(Runtime.getRuntime().freeMemory() / 1048576 + "MB /" + Runtime.getRuntime().totalMemory() / 1048576 + "MB");
				}
				sync(time.getTime());
			}
		});
		spawn.spawnThread().start();
	}
	
	// Prevents the engine from being resource intensive to the GPU
	private void sync(double loopStartTime) {
		float loopSlot = 1f / 60;
		double endTime = loopStartTime + loopSlot; 
		while(time.getTime() < endTime) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException ie) {}
		}
	}
	
	private boolean check() {
		return (os.startsWith("mac") || os.startsWith("win") || os.startsWith("linux")) && version >= 1.0d;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setGraphicsDevice(GraphicsDevice gd) {
		this.gd = gd;
	}
	
	public void setLocation(Point p) {
		loc = p;
	}
	
	public void setSize(Dimension d) {
		size = d;
	}
	
	@Override
	public String toString() {
		return String.valueOf(version);
	}
	
	public JFrame getFrame() {
		return frame;
	}
}
