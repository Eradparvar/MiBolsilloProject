import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Node {

	private String name;

	private LinkedList<Node> shortestPath = new LinkedList<>();

	private Integer distance = Integer.MAX_VALUE;

	private Map<Node, Integer> adjacentNodes = new HashMap<>();

	public Node(String name) {
		this.name = name;
	}

	public Node(Node node) {
		this(node.getName(), node.getShortestPath(), node.getDistance(), node.getAdjacentNodes());
	}

	public Node(String name, LinkedList<Node> shortestPath, Integer distance, Map<Node, Integer> adjacentNodes) {
		Map<Node, Integer> copyAdjacentNodes = new HashMap<>();
		LinkedList<Node> copyShortestPath = new LinkedList<>();
		adjacentNodes.forEach((k, v) -> copyAdjacentNodes.put(k, v));
		shortestPath.forEach(n -> copyShortestPath.add(n));
		this.name = name;
		this.shortestPath = shortestPath;
		this.distance = distance;
		this.adjacentNodes = adjacentNodes;

	}

	public void addDestination(Node destination, int distance) {
		adjacentNodes.put(destination, distance);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<Node, Integer> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public LinkedList<Node> getShortestPath() {
		return shortestPath;
	}

	public void setShortestPath(LinkedList<Node> shortestPath) {
		this.shortestPath = shortestPath;
	}

	@Override
	public String toString() {
		return name;
	}

	public boolean equals(Node node) {
		if (this.name.equals(node.name)) {
			return true;
		}
		return false;
	}

}