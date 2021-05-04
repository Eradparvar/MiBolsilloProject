import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

public class Algorithms {
	private LinkedHashMap<Node, Node> nodes;
	private Graph graph;

	public Algorithms(Graph graph) {
		this.graph = graph;
		nodes = graph.getNodes();
	}

//	calculate Distance Between Two Nodes
	private int calculateDistanceBetweenTwoNodes(Node startingNode, Node destinationNode) {
		if (!nodes.containsKey(startingNode)) {
			return -1;
		}

		Map<Node, Integer> startingNodeAdjacentNodesList = nodes.get(startingNode).getAdjacentNodes();
		Iterator<Entry<Node, Integer>> iterator = startingNodeAdjacentNodesList.entrySet().iterator();

		while (iterator.hasNext()) {
			Entry<Node, Integer> entry = iterator.next();
			Node currentNode = entry.getKey();
			int currentNodeCost = entry.getValue();
			if (currentNode.equals(destinationNode)) {
				return currentNodeCost;
			}
		}
		return -1;
	}

	public String calculateDistance(Node[] nodes) {
		int totalWeight = 0;
		for (int index = 0; index < nodes.length - 1; index++) {
			int distanceBetweenTwoNodes = calculateDistanceBetweenTwoNodes(nodes[index], nodes[index + 1]);
			if (distanceBetweenTwoNodes < 0) {
				return "NO SUCH ROUTE";
			}
			totalWeight += distanceBetweenTwoNodes;
		}
		return Integer.toString(totalWeight);
	}

//	number Of Trips Max
	public int numberOfTripsMax(Node start, Node end, int max) {
		int totalTrips = 0;
		Queue<Node> queue = new ArrayDeque<Node>();
		Queue<Node> parentQueue = new ArrayDeque<Node>();
		Node currentParent = start;
		queue.addAll(start.getAdjacentNodes().keySet());
		int currentDepth = 0;
		int counter = 0;
		while (currentDepth < max) {
			counter++;
			if (counter == 5) {
				System.out.println();
			}
			Node neighbor = queue.remove();
			parentQueue.add(neighbor);
			if (neighbor.equals(end)) {
				totalTrips += 1;
			} else {
				queue.addAll(neighbor.getAdjacentNodes().keySet());
			}
			if (currentParent.getAdjacentNodes().get(neighbor) == null) {
				currentParent = parentQueue.remove();
				currentDepth += 1;
			}
		}
		return totalTrips;
	}

//	Number Of Trips Exact Stops
	private int trips;

	public int numberOfTripsExactStops(Node start, Node end, int exact) {
		LinkedList<Node> visited = new LinkedList<Node>();
		trips = 0;
		visited.add(start);
		numberOfTripsExactStops(end, visited, exact);
		return trips;

	}

	private void numberOfTripsExactStops(Node end, LinkedList<Node> visited, int exact) {
		LinkedList<Node> nodes = new LinkedList<>(visited.getLast().getAdjacentNodes().keySet());
		for (Node node : nodes) {
			if (visited.size() > exact) {
				continue;
			}
			if (node.equals(end)) {
				if (visited.size() == (exact)) {
					trips += 1;
				}
				visited.add(node);
				visited.removeLast();
				break;
			}
		}
		for (Node node : nodes) {
			if (visited.contains(node) && node.getAdjacentNodes().containsKey(end) == false) {
				continue;
			}
			visited.addLast(node);
			numberOfTripsExactStops(end, visited, exact);
			visited.removeLast();
		}
	}

//	Dijkstra's Algorithm
	private boolean sameNode = false;

	public int shortestRoute(Graph graph, Node source, Node dest) {
		if (source.equals(dest)) {
			sameNode = true;
		}
		return calculateShortestPathFromSource(graph, source).getNodes().get(dest).getDistance();
	}

	private Graph calculateShortestPathFromSource(Graph graph, Node source) {
		source.setDistance(0);
		Set<Node> settledNodes = new HashSet<>();
		Set<Node> unsettledNodes = new HashSet<>();
		unsettledNodes.add(source);

		while (unsettledNodes.size() != 0) {
			Node currentNode = getLowestDistanceNode(unsettledNodes);
			unsettledNodes.remove(currentNode);
			for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
				Node adjacentNode = adjacencyPair.getKey();
				Integer edgeWeigh = adjacencyPair.getValue();
				if (!settledNodes.contains(adjacentNode)) {
					CalculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
					unsettledNodes.add(adjacentNode);
				}
			}
			settledNodes.add(currentNode);
			if (sameNode) {
				sameNode = false;
				currentNode.setDistance(Integer.MAX_VALUE);
				settledNodes.remove(currentNode);
				unsettledNodes.add(currentNode);
			}
		}
		return graph;
	}

	private void CalculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
		Integer sourceDistance = sourceNode.getDistance();
		if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
			evaluationNode.setDistance(sourceDistance + edgeWeigh);
			LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode);
			evaluationNode.setShortestPath(shortestPath);
		}
	}

	private Node getLowestDistanceNode(Set<Node> unsettledNodes) {
		Node lowestDistanceNode = null;
		int lowestDistance = Integer.MAX_VALUE;
		for (Node node : unsettledNodes) {
			int nodeDistance = node.getDistance();
			if (nodeDistance < lowestDistance) {
				lowestDistance = nodeDistance;
				lowestDistanceNode = node;
			}
		}
		return lowestDistanceNode;
	}

//	Amount Of Trips Within Distance
	public int amountOfTripsWithinDistance(Node start, Node end, int maxDistance) {
		Set<Path> solutions = new LinkedHashSet<Path>();
		LinkedList<Path> activePaths = new LinkedList<Path>();
		Path currentPath = new Path();
		currentPath.addToPath(start);
		activePaths.add(currentPath);
		while (!activePaths.isEmpty()) {
			currentPath = activePaths.getFirst();
			Node currentNode = currentPath.getPath().getLast();
			for (Iterator<Node> itr = currentNode.getAdjacentNodes().keySet().iterator(); itr.hasNext();) {

				Node adjacentNodeIndex = (Node) itr.next();
				if (currentPath.getTotalPathDistance() < maxDistance) {
					if (currentPath.getPath().getLast().equals(end) && currentPath.getPath().size() > 1) {
						solutions.add(currentPath);
					}
				}
				int distanceFromTwoNodes = currentNode.getAdjacentNodes().get(adjacentNodeIndex);
				if (currentPath.getTotalPathDistance() + distanceFromTwoNodes < maxDistance) {
					Path newAppendedPath = appendPath(currentNode, adjacentNodeIndex, currentPath, maxDistance);
					activePaths.addLast(newAppendedPath);
				}
			}

			activePaths.remove();

		}
		return solutions.size();
	}

	private Path appendPath(Node currentNode, Node adjacentNodeIndex, Path currentPath, int maxDistance) {
		Path currentPathCopy = new Path(currentPath);

		int distanceFromTwoNodes = currentNode.getAdjacentNodes().get(adjacentNodeIndex);
		currentPathCopy.addNodeToPathAndUpdateTotalPathDistance(adjacentNodeIndex, distanceFromTwoNodes, maxDistance);

		return currentPathCopy;

	}
}