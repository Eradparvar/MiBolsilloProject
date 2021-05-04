import java.util.HashMap;
import java.util.LinkedHashMap;

public class Graph {
	private LinkedHashMap<Node, Node> nodes = new LinkedHashMap<>();
	private HashMap<String, Node> strNodeMap = new HashMap<>();

	public void addNode(Node nodeA) {
		nodes.put(nodeA, nodeA);
	}

	public LinkedHashMap<Node, Node> getNodes() {
		return nodes;
	}

	public void addEdge(String edge) {
//		AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
		System.out.println(edge);
		String startNodeString = Character.toString(edge.toUpperCase().charAt(0));
		String destinationNodeString = Character.toString(edge.toUpperCase().charAt(1));
		int distance = Character.getNumericValue(edge.charAt(2));
		Node nodeA;
		Node nodeB;

		if (strNodeMap.containsKey(startNodeString) && strNodeMap.containsKey(destinationNodeString)) {
			nodeA = strNodeMap.get(startNodeString);
			nodeB = strNodeMap.get(destinationNodeString);
			nodeA.addDestination(nodeB, distance);

		} else if (strNodeMap.containsKey(startNodeString) && !strNodeMap.containsKey(destinationNodeString)) {
			nodeA = strNodeMap.get(startNodeString);
			nodeB = new Node(destinationNodeString);
			nodeA.addDestination(nodeB, distance);
			strNodeMap.put(startNodeString, nodeA);
			strNodeMap.put(destinationNodeString, nodeB);

		} else if (!strNodeMap.containsKey(startNodeString) && strNodeMap.containsKey(destinationNodeString)) {
			nodeA = new Node(startNodeString);
			nodeB = strNodeMap.get(destinationNodeString);
			nodeA.addDestination(nodeB, distance);
			strNodeMap.put(startNodeString, nodeA);
			strNodeMap.put(destinationNodeString, nodeB);

		} else if (!strNodeMap.containsKey(startNodeString) && !strNodeMap.containsKey(destinationNodeString)) {
			nodeA = new Node(startNodeString);
			nodeB = new Node(destinationNodeString);
			nodeA.addDestination(nodeB, distance);
			strNodeMap.put(startNodeString, nodeA);
			strNodeMap.put(destinationNodeString, nodeB);
		} else {
			System.out.println("ERROR--------");
		}

	}

	public Node strToNode(String s) {
		return strNodeMap.get(s);
	}

	public void finish() {
		strNodeMap.forEach((k, v) -> nodes.put(v, v));
	}

}
