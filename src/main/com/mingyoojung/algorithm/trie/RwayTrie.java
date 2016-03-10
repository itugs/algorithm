package com.mingyoojung.algorithm.trie;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 256-way trie. Just for ascii text.
 */
public class RwayTrie<V> {

	private Node<V> root;
	private int size;

	static class Node<V> {
		V value;
		Node<V>[] bucket = (Node<V>[])new Node[256];

		@Override
		public String toString() {
			return "[" + value + "] : " + Arrays.toString(bucket);
		}
	}

	public void add(String book, V value) {
		root = add(root, book, 0, value);
	}

	private Node<V> add(Node<V> node, String key, int d, V value) {
		if(node == null) node = new Node<>();
		if(key.length() == d) {
			if(node.value == null) size++;
			node.value = value;
			return node;
		}

		node.bucket[key.charAt(d)] = add(node.bucket[key.charAt(d)], key, d+1, value);
		return node;
	}

	public V get(String key) {
		Node<V> node = get(root, key, 0);
		return node == null ? null : node.value;
	}

	private Node<V> get(Node<V> node, String key, int d) {
		if (node == null) return null;
		if (key.length() == d) return node;
		return get(node.bucket[key.charAt(d)], key, d + 1);
	}

	public int size() {
		return size;
	}

	public Collection<String> keys() {
		return keyWithPrefix("");
	}

	public Collection<String> keyWithPrefix(String prefix) {
		Queue<String> queue = new LinkedList<>();
		Node node = get(root, prefix, 0);
		collect(node, new StringBuilder(prefix), queue);
		return queue;
	}

	private void collect(Node<V> node, StringBuilder sb, Queue<String> queue) {
		if(node == null) return ;
		if(node.value != null) queue.add(sb.toString());
		for(char i = 0; i < node.bucket.length; i++) {
			if(node.bucket[i] == null) {
				continue;
			}
			sb.append(i);
			collect(node.bucket[i], sb, queue);
			sb.deleteCharAt(sb.length()-1);
		}
	}

	public String longestPrefixOf(String pattern) {
		int length = longestPrefixOf(root, pattern, 0, 0);
		return pattern.substring(0, length);
	}

	/*
		Should separately tracking d and length because length is valid when there is a valid node in depth d.
	 */
	private int longestPrefixOf(Node<V> node, String pattern, int d, int length) {
		if(node == null) return length;
		if(node.value != null) length = d;
		if(pattern.length() == d) return length;
		char c = pattern.charAt(d);
		return longestPrefixOf(node.bucket[c], pattern, d+1, length);
	}

	public static void main(String[] args) throws Exception {

		RwayTrie<Integer> trie = new RwayTrie<>();
		trie.add("book", 0);
		trie.add("boy", 1);
		trie.add("beach", 2);
		trie.add("she", 3);
		trie.add("shell", 4);
		trie.add("shell", 5);
		trie.add("shells", 6);

		assertEquals(0, trie.get("book").intValue());
		assertEquals(5, trie.get("shell").intValue());
		assertEquals(6, trie.get("shells").intValue());

		assertEquals(6, trie.size());

		assertEquals(null, trie.get("shel"));
		assertEquals(null, trie.get("ab"));

		trie.add("ab", 7);
		assertEquals(7, trie.get("ab").intValue());
		assertEquals(7, trie.size());

		// keys
		Collection<String> keys = trie.keys();
		assertEquals(trie.size(), keys.size());
		for(String key : keys) {
			assertNotNull(trie.get(key));
		}

		// prefix match
		Collection<String> prefixKeys = trie.keyWithPrefix("sh");
		assertEquals(3, prefixKeys.size());

		// longestPrefixOf
		assertEquals("shell", trie.longestPrefixOf("shell"));
		assertEquals("she", trie.longestPrefixOf("shel"));
		assertEquals("she", trie.longestPrefixOf("shea"));
		assertEquals("", trie.longestPrefixOf("x"));
	}
}
