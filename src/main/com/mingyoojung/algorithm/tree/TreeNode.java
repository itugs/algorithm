package com.mingyoojung.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	char op = '0';
	int value;
	List<TreeNode> children = new ArrayList<>();

	public TreeNode(char op) {
		this.op = op;
	}

	public TreeNode(int value) {
		this.value = value;
	}

	public TreeNode addChild(TreeNode child) {
		children.add(child);
		return this;
	}
}
