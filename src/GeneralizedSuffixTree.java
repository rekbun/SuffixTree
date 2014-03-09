import java.util.Collection;
import java.util.Scanner;

public class GeneralizedSuffixTree {
	Node[] nodes;
	char[] text;

	private int position=-1;
	private int currentNode;
	private Node needSuffixLink;

	private int remainder;
	private int activeLength,activeEdge;
	private Node activeNode;
	private Node root;
	private static final int capacity=16;

	public GeneralizedSuffixTree() {
		this(capacity);
	}
	public GeneralizedSuffixTree(int capacity) {
		nodes= new Node[capacity];
		text=new char[capacity];
	 	root=activeNode=newNode();
	}

	private Node newNode() {
		Node node=new Node();
		return node;
	}

	public void put(String inp,int dataIndex) {
		reset(inp.length());
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

	private void reset(int length) {
		text=new char[length];
		remainder=0;
		position=0;
		activeNode=root;
		position=-1;
		activeLength=0;
		activeEdge=0;
	}

	private Node addChar(char c,String string) {
		text[++position]=c;
		needSuffixLink=null;
		remainder++;
		Node ret=null;
		while (remainder>0) {
			if(activeLength==0) {
				activeEdge=position;
			}
			if(!activeNode.next.containsKey(text[activeEdge])) {
				Edge newEdge=new Edge(string.substring(position));
				Node newNode=new Node();
				newEdge.setDest(newNode);
				activeNode.next.put(text[activeEdge],newEdge);
				addSuffixLink(newEdge);
			}else {
				Edge edge=activeNode.next.get(text[activeEdge]);
				if(walkDown(edge)) continue;
				if(edge.getLabel().charAt(activeLength)==c) {
					activeLength++;
					addSuffixLink(edge);
					break;
				}
				Node split=newNode();
				Edge newEdge=new Edge(edge.getLabel().substring(0,activeLength));
				newEdge.setDest(split);
				activeNode.next.put(text[activeEdge],newEdge);
				Edge leaf=new Edge(string.substring(position));
				Node leafNode=newNode();
				ret=leafNode;
				leaf.setDest(leafNode);
				split.next.put(c, leaf);
				edge.setLabel(edge.getLabel().substring(activeLength+1));
				split.next.put(edge.getLabel().charAt(0),edge);
				addSuffixLink(newEdge);
			}
			remainder--;
			if(activeNode==root && activeLength>0) {
				activeLength--;
				activeEdge=position-remainder+1;
			}else {
				activeNode=activeNode.getSuffixLink()!=null?activeNode.getSuffixLink().getDest():root;
			}
		}
		return ret;
	}

	private boolean walkDown(Edge edge) {
		if(activeLength>=edge.getLength()) {
			activeEdge+=edge.getLength();
			activeLength-=edge.getLength();
			activeNode=edge.getDest();
			return true;
		}
		return false;
	}

	private void addSuffixLink(Edge activeLink) {
		if(needSuffixLink!=null) {
			needSuffixLink.setSuffixLink(activeLink);
		}
		needSuffixLink=activeNode;
	}

	public Collection<Integer> search(String word) {
		return null;
	}


	public Collection<Integer> search(String word,int limit) {
		return null;
	}

	public static void main(String... args) {
		GeneralizedSuffixTree generalizedSuffixTree=new GeneralizedSuffixTree();
		generalizedSuffixTree.put("abcabxabcd",0);
		int y;
		Scanner scanner=new Scanner(System.in);
		scanner.next();

	}

}
