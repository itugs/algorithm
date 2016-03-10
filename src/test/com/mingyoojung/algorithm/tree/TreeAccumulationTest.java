package com.mingyoojung.algorithm.tree;

import static org.junit.Assert.*;

import org.junit.Test;

public class TreeAccumulationTest {

	@Test
	public void addTest() throws Exception {

		TreeNode root = new TreeNode('+');
		root.children.add(new TreeNode(26));
		root.children.add(new TreeNode(10));

		assertEquals(36, TreeAccumulation.eval(root));
	}

	@Test
	public void complexTest() throws Exception {

		TreeNode root = new TreeNode('*');
		root.addChild(new TreeNode('+')
				.addChild(new TreeNode(2))
					.addChild(new TreeNode('*')
							.addChild(new TreeNode(4))
							.addChild(new TreeNode(6))));
		root.addChild(new TreeNode('+')
				.addChild(new TreeNode(3))
				.addChild(new TreeNode(5))
				.addChild(new TreeNode(7)));

		assertEquals(390, TreeAccumulation.eval(root));
	}
}
