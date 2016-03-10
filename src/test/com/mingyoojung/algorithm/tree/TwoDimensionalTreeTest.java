package com.mingyoojung.algorithm.tree;

import org.junit.Test;

import static org.junit.Assert.*;

public class TwoDimensionalTreeTest {

	@Test
	public void buildTest() throws Exception {

		TwoDimensionalTree tree = new TwoDimensionalTree();
		tree.add(new Point(7,2));
		tree.add(new Point(5,4));
		tree.add(new Point(9,6));
		tree.add(new Point(2,3));
		tree.add(new Point(4,7));
		tree.add(new Point(8,1));

		System.out.println(tree.root);
	}

	@Test
	public void NNQueryTest() throws Exception {

		TwoDimensionalTree tree = new TwoDimensionalTree();
		tree.add(new Point(1, 2));
		tree.add(new Point(4, 6));

		System.out.println(tree.root);

		for(int y = 0 ; y < 7; y++) {
			for(int x = 0 ; x < 5; x++) {
				Point p = new Point(x, y);
				System.out.print(tree.nn(p).distanceFrom(p));
			}
			System.out.println();
		}
	}
}
