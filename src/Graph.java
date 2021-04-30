import java.util.LinkedHashMap;

public class Graph {
	private LinkedHashMap<Node, Node> nodes = new LinkedHashMap<>();

	public void addNode(Node nodeA) {
		nodes.put(nodeA, nodeA);
	}

	public LinkedHashMap<Node, Node> getNodes() {
		return nodes;
	}
}
