package main.java.com.rakesh.suffixtree;

import java.util.Collection;
import java.util.Scanner;

public class GeneralizedSuffixTree {
	private char[] text;

	private int position = -1;
	private Node needSuffixLink;

	private int remainder;
	private int activeLength, activeEdge;
	private Node activeNode;
	private final Node root;

	public GeneralizedSuffixTree() {
		root = activeNode = newNode();
	}

	private Node newNode() {
		return new Node();
	}

	public void put(String inp, int dataIndex) {
		if (inp == null || inp.isEmpty()) {
			return;
		}
		inp += "#";
		reset(inp.length());
		for (int i = 0; i < inp.length(); i++) {
			addChar(i, inp, dataIndex);
		}
	}

	private void reset(int length) {
		text = new char[length];
		remainder = 0;
		position = 0;
		activeNode = root;
		position = -1;
		activeLength = 0;
		activeEdge = 0;
	}

	private void addChar(int idx, String string, int dataIndex) {
		text[++position] = string.charAt(idx);
		needSuffixLink = null;
		remainder++;
		Node ret = null;
		while (remainder > 0) {
			if (activeLength == 0) {
				activeEdge = position;
			}
			if (!activeNode.next.containsKey(text[activeEdge])) {
				Edge newEdge = new Edge(string.substring(position).intern());
				Node newNode = new Node();
				newNode.setData(dataIndex);
				newEdge.setDest(newNode);
				activeNode.next.put(text[activeEdge], newEdge);
				addSuffixLink(activeNode);
			} else {
				Edge edge = activeNode.next.get(text[activeEdge]);
				if (walkDown(edge)) continue;
				if (edge.getLabel().charAt(activeLength) == string.charAt(idx)) {
					activeLength++;
					addSuffixLink(activeNode);
					break;
				}
				Node split = newNode();
				Edge newEdge = new Edge(edge.getLabel().substring(0, activeLength).intern());
				newEdge.setDest(split);
				activeNode.next.put(text[activeEdge], newEdge);
				Edge leaf = new Edge(string.substring(position).intern());
				Node leafNode = newNode();
				leafNode.setData(dataIndex);
				leaf.setDest(leafNode);
				split.next.put(string.charAt(idx), leaf);
				edge.setLabel(edge.getLabel().substring(activeLength).intern());
				split.next.put(edge.getLabel().charAt(0), edge);
				addSuffixLink(split);
			}
			remainder--;
			if (activeNode == root && activeLength > 0) {
				activeLength--;
				activeEdge = position - remainder + 1;
			} else {
				activeNode = activeNode.getSuffixLink() != null ? activeNode.getSuffixLink() : root;
			}
		}
	}

	private boolean walkDown(Edge edge) {
		if (activeLength >= edge.getLength()) {
			activeEdge += edge.getLength();
			activeLength -= edge.getLength();
			activeNode = edge.getDest();
			return true;
		}
		return false;
	}

	private void addSuffixLink(Node activeLink) {
		if (needSuffixLink != null) {
			needSuffixLink.setSuffixLink(activeLink);
		}
		needSuffixLink = activeLink;
	}

	public Collection<Integer> search(String word) {
		return search(word, Integer.MAX_VALUE);
	}


	public Collection<Integer> search(String word, int limit) {
		Node dataNode = findNode(word);
		if (dataNode != null) {
			return dataNode.getData(limit);
		}
		return null;
	}

	private Node findNode(String word) {
		Node it = root;
		for (int i = 0; i < word.length(); ) {
			if (it.next.containsKey(word.charAt(i))) {
				Edge edge = it.next.get(word.charAt(i));
				i += edge.getLength();
				it = edge.getDest();
			}
		}
		return it;
	}


}
