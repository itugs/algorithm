package com.mingyoojung.algorithm.math;

import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciTest {

	@Test
	public void fibrTest() throws Exception {
		System.out.println(Fibonacci.fibr(40));
	}

	@Test
	public void fibiTest() throws Exception {
		System.out.println(Fibonacci.fibi(40));
	}

	@Test
	public void compareTest() throws Exception {
		for(int i = 1; i <= 20; i++) {
			assertEquals(Fibonacci.fibr(i), Fibonacci.fibi(i));
		}
	}
}
