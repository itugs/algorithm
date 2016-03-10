package com.mingyoojung.algorithm.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Two Dimensional Tree of kd tree
 */
public class TwoDimensionalTree {

	class Node {
		private final Point point;
		private Node left, right;

		public Node(Point p) {
			this.point = p;
		}

		@Override
		public String toString() {
			return "[" + left + " - " + point + " - " + right + "]";
		}
	}

	Node root = null;

	public void add(Point point) {
		if (root == null) {
			root = new Node(point);
		} else {
			add(root, point, 0);
		}
	}

	private void add(Node current, Point point, int level) {
		// duplicated
		if (current.point.equals(point)) {
			return;
		}

		int pa, pb;
		if (level % 2 == 0) {
			// vertical separation
			pa = current.point.getX();
			pb = point.getX();
		} else {
			// horizontal separation
			pa = current.point.getY();
			pb = point.getY();
		}

		if (pa > pb) {
			if (current.left == null) {
				current.left = new Node(point);
			} else {
				add(current.left, point, level+1);
			}
		} else {
			if (current.right == null) {
				current.right = new Node(point);
			} else {
				add(current.right, point, level+1);
			}
		}
	}

	public Point nn(Point point) {
		if(root == null) throw new IllegalStateException();

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		Node candidates = root;
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			if(current == null) continue;

			if(current.point.equals(point)) {
				candidates = current;
				break;
			}

			int candidateDistance = candidates.point.distanceFrom(point);
			int currentDistance = current.point.distanceFrom(point);
			if(currentDistance <= candidateDistance) {
				candidates = current;
				queue.add(current.left);
				queue.add(current.right);
			}
		}

		return candidates.point;
	}
}
