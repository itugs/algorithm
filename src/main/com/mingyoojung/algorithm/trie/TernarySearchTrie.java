package com.mingyoojung.algorithm.trie;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * TST implementation. From https://class.coursera.org/algs4partII-006
 */
public class TernarySearchTrie<V> {

	private Node<V> root;
	private int size;

	static class Node<V> {
		char key;
		V value;
		Node<V> left, middle, right;

		public Node(char key) {
			this.key = key;
		}

		@Override
		public String toString() {
			return "[" + key + "] : [" + value + "]";
		}
	}

	public int size() {
		return size;
	}

	public void put(String key, V value) {
		root = put(root, key, value, 0);
	}

	private Node<V> put(Node<V> node, String key, V value, int d) {
		char c = key.charAt(d);
		if (node == null) node = new Node<>(c);
		if (key.length() - 1 == d) {
			if (node.value == null) size++;
			node.value = value;
			return node;
		}

		if (node.key > c) node.left = put(node.left, key, value, d);
		else if (node.key < c) node.right = put(node.right, key, value, d);
		else node.middle = put(node.middle, key, value, d + 1);

		return node;
	}

	public V get(String key) {
		Node<V> node = get(root, key, 0);
		return node == null ? null : node.value;
	}

	private Node<V> get(Node<V> node, String key, int d) {
		if (node == null) return null;

		char c = key.charAt(d);
		if (node.key > c) return get(node.left, key, d);
		else if (node.key < c) return get(node.right, key, d);
		else if (key.length() - 1 > d) return get(node.middle, key, d + 1);
		else return node;
	}

	public Collection<String> keys() {
		Queue<String> queue = new LinkedList<>();
		collect(root, "", queue);
		return queue;
	}

	public Collection<String> keysWithPrefix(String prefix) {
		Queue<String> queue = new LinkedList<>();
		Node<V> node = get(root, prefix, 0);
		if (node != null) collect(node, prefix, queue);
		return queue;
	}

	private void collect(Node<V> node, String prefix, Queue<String> queue) {
		if (node == null) return;

		collect(node.left, prefix, queue);

		if (node.value != null) queue.add(prefix + node.key);

		collect(node.middle, prefix + node.key, queue);
		collect(node.right, prefix, queue);
	}

	public String longestPrefixOf(String prefix) {
		if (prefix.length() == 0) return "";
		int length = longestPrefixOf(root, prefix, 0, 0);
		return prefix.substring(0, length);
	}

	private int longestPrefixOf(Node<V> node, String prefix, int d, int length) {
		if (node == null) return length;
		if (prefix.length() == d) return length;

		char c = prefix.charAt(d);
		if(node.key > c) return longestPrefixOf(node.left, prefix, d, length);
		else if(node.key < c) return longestPrefixOf(node.right, prefix, d, length);
		else {
			if (node.value != null) length = d+1;
			return longestPrefixOf(node.middle, prefix, d+1, length);
		}
	}

	public static void main(String[] args) throws Exception {
		TernarySearchTrie<Integer> tst = new TernarySearchTrie<>();
		tst.put("book", 0);
		tst.put("boy", 1);
		tst.put("beach", 2);
		tst.put("she", 3);
		tst.put("shell", 4);
		tst.put("shell", 5);
		tst.put("shells", 6);

		assertEquals(0, tst.get("book").intValue());
		assertEquals(5, tst.get("shell").intValue());
		assertEquals(6, tst.get("shells").intValue());

		assertEquals(6, tst.size());

		assertNull(tst.get("shel"));
		assertNull(tst.get("ab"));

		tst.put("ab", 7);
		assertEquals(7, tst.get("ab").intValue());
		assertEquals(7, tst.size());

		// keys
		Collection<String> keys = tst.keys();

		assertEquals(tst.size(), keys.size());
		for(String key : keys) {
			assertNotNull(tst.get(key));
		}

		// prefix match
		Collection<String> prefixKeys = tst.keysWithPrefix("sh");
		assertEquals(3, prefixKeys.size());

		prefixKeys = tst.keysWithPrefix("x");
		assertEquals(0, prefixKeys.size());

		// longestPrefixOf
		assertEquals("shell", tst.longestPrefixOf("shell"));
		assertEquals("she", tst.longestPrefixOf("shel"));
		assertEquals("she", tst.longestPrefixOf("shea"));
		assertEquals("", tst.longestPrefixOf("x"));
	}
}
