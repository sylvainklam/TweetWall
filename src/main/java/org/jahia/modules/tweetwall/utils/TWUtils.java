package org.jahia.modules.tweetwall.utils;

import com.ibm.icu.util.StringTokenizer;

public class TWUtils {
	private static final String SEPARATOR = ",";

	public static String[] convert2Array(String s) {
		StringTokenizer stringTokenizer = new StringTokenizer(s, SEPARATOR);
		String[] sTab = new String[stringTokenizer.countTokens()];
		int i = 0;
		while (stringTokenizer.hasMoreTokens()) {
			sTab[i] = stringTokenizer.nextToken();
			i++;
		}
		return sTab;
	}

	public static String printArrayValues(String[] tab) {
		StringBuffer arrayToPrint = new StringBuffer("{");
		for (int i = 0; i < tab.length; i++) {
			arrayToPrint.append(tab[i]).append(",");
		}
		return arrayToPrint.toString().substring(0, arrayToPrint.length() - 1).concat("}");
	}
}
