import java.util.HashMap;

public class Node {
	private int[] data;
	private int lastIdx;
	HashMap<Character,Edge> next=new HashMap<Character, Edge>();
	private Node suffixLink;
	private final int capacity=8;

	public Node() {
		this.data = new int[capacity];
		lastIdx=0;
	}

	public int[] getData() {
		return data;
	}

	public void setData(int data) {
		this.data[lastIdx++] = data;
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