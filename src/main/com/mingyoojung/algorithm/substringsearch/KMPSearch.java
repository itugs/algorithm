package com.mingyoojung.algorithm.substringsearch;

import static org.junit.Assert.assertEquals;

/**
 * KMP substring search by DFA
 */
public class KMPSearch {

	private int R = 256;
	private int[][] dfa;

	public KMPSearch(String pattern) {
		char[] pt = pattern.toCharArray();
		dfa = new int[R][pt.length];
		dfa[pt[0]][0] = 1;

		for (int x = 0, j = 1; j < pt.length; j++) {
			for (int c = 0; c < R; c++) {
				dfa[c][j] = dfa[c][x];
			}
			dfa[pt[j]][j] = j + 1;
			x = dfa[pt[j]][x];
		}
	}

	public int search(String text) {
		int i = 0, j = 0;
		for (; i < text.length() && j < dfa[0].length; i++) {
			j = dfa[text.charAt(i)][j];
		}

		return j == dfa[0].length ? i - dfa[0].length : -1;
	}

	public static void main(String[] args) throws Exception {

		String pattern = "CDEFAB";
		KMPSearch cut = new KMPSearch(pattern);

		assertEquals(1, cut.search("ACDEFAB"));
		assertEquals(-1, cut.search("ACBF"));
	}
}
