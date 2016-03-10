package com.mingyoojung.algorithm.tree;

import java.util.function.IntBinaryOperator;

public class TreeAccumulation {

	static IntBinaryOperator A = (a, b) -> a + b;
	static IntBinaryOperator S = (a, b) -> a - b;
	static IntBinaryOperator M = (a, b) -> a * b;
	static IntBinaryOperator D = (a, b) -> a / b;

	public static int eval(TreeNode node) {
		if (node.op == '0') return node.value;

		IntBinaryOperator func;

		switch(node.op) {
			case '+': func = A; break;
			case '-': func = S; break;
			case '*': func = M; break;
			case '/': func = D; break;
			default: throw new IllegalStateException();
		}

		return node.children.stream()
				.mapToInt(TreeAccumulation::eval)
				.reduce(func)
				.getAsInt();
	}
}
