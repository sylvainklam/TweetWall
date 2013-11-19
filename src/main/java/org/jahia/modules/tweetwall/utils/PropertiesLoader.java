package org.jahia.modules.tweetwall.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
	public static Properties load(String filename) throws IOException, FileNotFoundException {
		Properties properties = new Properties();
		InputStream fileInputStream = PropertiesLoader.class.getResourceAsStream(filename);
		properties.load(fileInputStream);
		return properties;
	}
}
