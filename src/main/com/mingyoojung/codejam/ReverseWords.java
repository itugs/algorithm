package com.mingyoojung.codejam;

import java.util.Scanner;

public class ReverseWords {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = Integer.valueOf(sc.nextLine());
		for(int i = 1; i <= N; i++) {
			String words = sc.nextLine();
			System.out.printf("Case #%d: %s\n", i, reverse(words));
		}
	}

	public static String reverse(String words) {
		StringBuilder sb = new StringBuilder(words.length());
		int end = words.length();
		for(int i = end-1; i >= 0; i--) {
			if(words.charAt(i) == ' ') {
				sb.append(words.substring(i+1, end)).append(' ');
				end = i;
			}
		}
		sb.append(words.substring(0, end));
		return sb.toString();
	}
}
