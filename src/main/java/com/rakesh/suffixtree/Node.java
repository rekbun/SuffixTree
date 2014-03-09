package main.java.com.rakesh.suffixtree;

import java.util.*;

public class Node {
	private int[] data;
	private int lastIdx;
	HashMap<Character, Edge> next = new HashMap<Character, Edge>();
	private Node suffixLink;

	public Node() {
		int INITIAL_CAPACITY = 0;
		this.data = new int[INITIAL_CAPACITY];
		lastIdx = 0;
	}

	public Collection<Integer> getData() {
		return getData(Integer.MAX_VALUE);
	}

	public Collection<Integer> getData(int limit) {
		Set<Integer> ret = new HashSet<Integer>();
		for (int d=0;d<lastIdx;d++) {
			if (ret.size() < limit) {
				ret.add(data[d]);
			}
		}

		for (Edge edge : next.values()) {
			if (ret.size() < limit) {
				ret.addAll(edge.getDest().getData(limit - ret.size()));
			}
		}
		return ret;
	}

	public void setData(int data) {
		ensureCapacity();
		this.data[lastIdx++] = data;
	}

	private void ensureCapacity() {
		if (data.length == lastIdx) {
			data = Arrays.copyOf(data, 2 * data.length + 2);
		}
	}

	public HashMap<Character, Edge> getNext() {
		return next;
	}

	public void setNext(HashMap<Character, Edge> next) {
		this.next = next;
	}

	public Node getSuffixLink() {
		return suffixLink;
	}

	public void setSuffixLink(Node suffixLink) {
		this.suffixLink = suffixLink;
	}
}