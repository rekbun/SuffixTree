public class Edge {
	private String label;
	private Node dest;

	public Edge(String substring) {
		label = substring;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Node getDest() {
		return dest;
	}

	public void setDest(Node dest) {
		this.dest = dest;
	}

	public int getLength() {
		return label.length();
	}
}
