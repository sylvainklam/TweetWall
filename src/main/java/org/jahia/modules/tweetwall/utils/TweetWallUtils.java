package org.jahia.modules.tweetwall.utils;

import java.util.StringTokenizer;

public class TweetWallUtils {
	private static final String SEPARATOR = ",";

	private static TweetWallUtils instance = null;

	public static synchronized TweetWallUtils getInstance() {
		if (instance == null)
			instance = new TweetWallUtils();
		return instance;
	}

	public String[] convert2Array(String s) {
		StringTokenizer stringTokenizer = new StringTokenizer(s, SEPARATOR);
		String[] sTab = new String[stringTokenizer.countTokens()];
		int i = 0;
		while (stringTokenizer.hasMoreTokens()) {
			sTab[i] = stringTokenizer.nextToken();
			i++;
		}
		return sTab;
	}

	public String printArrayValues(String[] tab) {
		StringBuffer arrayToPrint = new StringBuffer("{");
		for (int i = 0; i < tab.length; i++) {
			arrayToPrint.append(tab[i]).append(SEPARATOR);
		}
		return arrayToPrint.toString().substring(0, arrayToPrint.length() - 1).concat("}");
	}
}
