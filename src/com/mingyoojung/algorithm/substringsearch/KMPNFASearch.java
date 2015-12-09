package com.mingyoojung.algorithm.substringsearch;

import static org.junit.Assert.assertEquals;

/**
 * KMP substring search by prefix matching NFA
 */
public class KMPNFASearch {

	final String pattern;
	final int[] pi;

	public KMPNFASearch(String pattern) {
		this.pattern = pattern;
		pi = new int[pattern.length()];
		pi[0] = 0;

		for (int j = 0, i = 1; i < pattern.length(); i++) {
			if (pattern.charAt(j) == pattern.charAt(i)) {
				pi[i] = ++j;
			} else {
				while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
					j = pi[j - 1];
				}
				pi[i] = j;
			}
		}
	}

	public int search(String text) {
		int j = 0;
		int i = 0;
		while (i < text.length() && j < pattern.length()) {
			if (pattern.charAt(j) == text.charAt(i)) {
				i++;
				j++;
			} else {
				if (j == 0) i++;
				else j = pi[j - 1];
			}
		}

		return j == pattern.length() ? i - pattern.length() : -1;
	}

	public static void main(String[] args) throws Exception {

		KMPNFASearch cut = new KMPNFASearch("abcaby");
		assertEquals(6, cut.search("abxabcabcaby"));
	}
}
