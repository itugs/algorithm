package com.mingyoojung.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NQueens {

	/**
	 * N-Queens solution by stream op inspired by SICP
	 */
	public static List<List<Integer>> nqueens(int n) {
		return _nqueens(n, n);
	}

	private static List<List<Integer>> _nqueens(int n, int depth) {
		if(depth == 0) return Collections.singletonList(new ArrayList<>());

		return _nqueens(n, depth - 1).stream()
				.map(restOfQueens ->
						IntStream.range(1, n + 1)
								.mapToObj(newRow -> {
									LinkedList<Integer> r = new LinkedList<>(restOfQueens);
									r.add(0, newRow);
									return r;
								})
								.collect(Collectors.toList()))
				.flatMap(Collection::stream)
				.filter(positions -> {
					int cur = positions.get(0);
					int l = cur - 1;
					int r = cur + 1;
					for(int i = 1; i < positions.size(); i++, l--, r++) {
						int x = positions.get(i);
						if(x == cur || x == l || x == r) return false;
					}
					return true;
				})
				.collect(Collectors.toList());
	}
}
