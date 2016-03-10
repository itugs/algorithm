package com.mingyoojung.algorithm.tree;

public class Point {
	private final int x;
	private final int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int distanceFrom(Point p) {
		return Math.abs(x - p.getX()) + Math.abs(y-p.getY());
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Point)) return false;
		Point p2 = (Point)obj;
		return x == p2.x && y == p2.y;
	}

	@Override
	public String toString() {
		return String.format("(%d, %d)", x, y);
	}
}
