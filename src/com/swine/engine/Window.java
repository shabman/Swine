package com.swine.engine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.swine.engine.awtmod.WindowListenerMod;
import com.swine.engine.events.EngineListener;
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
	
	public JFrame frame;
	
	private Window() { }
	
	public static Window build() {
		if (window == null)
			window = new Window();
		os = OS.getOS();
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
			frame = new JFrame((title != null) ? title : "Window", (gd != null) ? gd.getDefaultConfiguration() : null);
			frame.setLocation((loc != null) ? loc : new Point(0, 0));
			frame.setSize((size != null) ? size : new Dimension(halfSize.width / 2, halfSize.height / 2));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.addWindowListener(new WindowListenerMod() {
				@Override
				public void windowClosing(WindowEvent e) {
					EngineListener.fireEvent("destroy");
				}
			});
			
			EngineListener.fireEvent("create");
			
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
	
	private void loop() {
		// WARNING: Do NOT use JOptionPane as this class YIELDS the thread.
		spawn.setCallback(() -> {
			while (run) {
				double loopStartTime = time.getTime();
				double elapsed = loopStartTime - previous;
				previous = loopStartTime;
				steps += elapsed;
				
				// INPUT
				
				while (steps >= FPS) {
					// UPDATE GAME STATE
					ColorUtil.paint(frame.getContentPane(), new Color(i, i, i));
					i++;
					if (i == 255) i = 0;
					steps -= FPS;
					System.out.println(Runtime.getRuntime().freeMemory() / 1048576 + "MB /" + Runtime.getRuntime().totalMemory() / 1048576 + "MB");
				}
				sync(time.getTime());
			}
		});
		spawn.spawnThread().start();
	}
	
	// Prevents the engine from being resource intensive to the CPU and/or GPU
	private void sync(double loopStartTime) {
		float loopSlot = 1f / 50;
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
