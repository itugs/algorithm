package com.mingyoojung.codejam;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StoreCredit {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int N = Integer.valueOf(sc.nextLine());
		for(int i = 1; i <= N; i++) {
			int C = sc.nextInt();
			int I = sc.nextInt();
			int[] prices = new int[I];
			for(int j = 0; j < I; j++) {
				prices[j] = sc.nextInt();
			}
			int[] items = solve(C, prices);
			System.out.printf("Case #%d: %d %d\n", i, items[0], items[1]);
		}
	}

	private static int[] solve(int c, int[] prices) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 1; i <= prices.length; i++) {
			int p = prices[i-1];
			if(!map.containsKey(c-p)) map.put(p, i);
			else {
				return new int[]{map.get(c-p), i};
			}
		}
		throw new IllegalArgumentException();
	}
}
