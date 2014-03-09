import java.util.Collection;

public class GeneralizedSuffixTree {
	Node[] nodes;
	char[] text;

	private int root,position=-1;
	private int currentNode;
	private int needSuffixLink;

	private int remainder;
	private int activeLength,activeNode,activeEdge;

	private int capacity=16;

	public GeneralizedSuffixTree() {
		nodes=new Node[capacity];
		text=new char[capacity];
	}
	public GeneralizedSuffixTree(int capacity) {
		nodes= new Node[capacity];
		text=new char[capacity];
	 	root=activeNode=newNode();
	}

	private int newNode() {
		nodes[++currentNode]=new Node();
		return currentNode;
	}

	public void put(String inp,int dataIndex) {
		reset();
		if(inp==null||inp.isEmpty()) {
			return;
		}
		Node lastNode = null;
		for(int i=0;i<inp.length();i++) {
			lastNode=addChar(inp.charAt(i),inp);
		}
		assert lastNode != null;
		lastNode.setData(dataIndex);
	}

	private void reset() {

	}

	private Node addChar(char c,String string) {
		text[++position]=c;
		needSuffixLink=-1;
		remainder++;
		while (remainder>0) {
			if(activeLength==0) {
				activeEdge=position;
			}
			if(!nodes[activeNode].next.containsKey(text[activeEdge])) {
				Edge newEdge=new Edge(string.substring(position));
				Node newNode=new Node();
				newEdge.setDest(newNode);
				newNode.next.put(c,)
			}

		}
	}

	public Collection<Integer> search(String word) {

	}


	public Collection<Integer> search(String word,int limit) {

	}


}
