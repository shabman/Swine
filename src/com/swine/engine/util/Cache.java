package com.swine.engine.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Cache {
	
	private static Cache instance;
	
	protected Cache() { }

	
	public static Cache get() {
		if (instance == null)
			instance = new Cache();
		return instance;
	}
	
	public File get(String fn) {
		if (Files.exists(Paths.get(fn)))
			return new File(fn);
		else
			return null;
	}
	
	public void writeTo(File file, String data) throws IOException {
		FileWriter writer = new FileWriter(file);
		writer.write(data);
		writer.close();
	}
}
