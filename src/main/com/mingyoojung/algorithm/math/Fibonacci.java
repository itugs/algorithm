package com.mingyoojung.algorithm.math;

public class Fibonacci {

	public static long fibr(int n) {
		if(n == 0) return 0;
		if(n == 1) return 1;
		return fibr(n-1) + fibr(n-2);
	}

	public static long fibi(int n) {
		long a = 1, b = 0, t;
		while(--n > 0) {
			t = a + b;
			b = a;
			a = t;
		}
		return a;
	}
}
