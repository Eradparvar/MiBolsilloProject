import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestCases {
	@Test
	void testCalculateDistanceValid() {
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");
//		AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
		nodeA.addDestination(nodeB, 5);
		nodeB.addDestination(nodeC, 4);
		nodeC.addDestination(nodeD, 8);
		nodeD.addDestination(nodeC, 8);
		nodeD.addDestination(nodeE, 6);
		nodeA.addDestination(nodeD, 5);
		nodeC.addDestination(nodeE, 2);
		nodeE.addDestination(nodeB, 3);
		nodeA.addDestination(nodeE, 7);

		Graph graph = new Graph();

		graph.addNode(nodeA);
		graph.addNode(nodeB);
		graph.addNode(nodeC);
		graph.addNode(nodeD);
		graph.addNode(nodeE);
		Algorithms algorithms = new Algorithms(graph);
		Node[] calculateDistance01 = { nodeA, nodeE, nodeB, nodeC, nodeD };
		assertEquals("22", algorithms.calculateDistance(calculateDistance01));
	}

	void testCalculateDistanceNoSuchRoute() {
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");
		nodeA.addDestination(nodeB, 5);
		nodeB.addDestination(nodeC, 4);
		nodeC.addDestination(nodeD, 8);
		nodeD.addDestination(nodeC, 8);
		nodeD.addDestination(nodeE, 6);
		nodeA.addDestination(nodeD, 5);
		nodeC.addDestination(nodeE, 2);
		nodeE.addDestination(nodeB, 3);
		nodeA.addDestination(nodeE, 7);
		Graph graph = new Graph();
		graph.addNode(nodeA);
		graph.addNode(nodeB);
		graph.addNode(nodeC);
		graph.addNode(nodeD);
		graph.addNode(nodeE);
		Algorithms algorithms = new Algorithms(graph);
		Node[] calculateDistance = { nodeA, nodeE, nodeD };

		assertEquals("NO SUCH ROUTE", algorithms.calculateDistance(calculateDistance));

	}

	@Test
	void testNumberOfTripsMax() {
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");

		nodeA.addDestination(nodeB, 5);
		nodeB.addDestination(nodeC, 4);
		nodeC.addDestination(nodeD, 8);
		nodeD.addDestination(nodeC, 8);
		nodeD.addDestination(nodeE, 6);
		nodeA.addDestination(nodeD, 5);
		nodeC.addDestination(nodeE, 2);
		nodeE.addDestination(nodeB, 3);
		nodeA.addDestination(nodeE, 7);

		Graph graph = new Graph();

		graph.addNode(nodeA);
		graph.addNode(nodeB);
		graph.addNode(nodeC);
		graph.addNode(nodeD);
		graph.addNode(nodeE);

		Algorithms algorithms = new Algorithms(graph);
		assertEquals(2, algorithms.numberOfTripsMax(nodeC, nodeC, 3));
	}

	@Test
	void testNumberOfTripsExactStops() {
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");

		nodeA.addDestination(nodeB, 5);
		nodeB.addDestination(nodeC, 4);
		nodeC.addDestination(nodeD, 8);
		nodeD.addDestination(nodeC, 8);
		nodeD.addDestination(nodeE, 6);
		nodeA.addDestination(nodeD, 5);
		nodeC.addDestination(nodeE, 2);
		nodeE.addDestination(nodeB, 3);
		nodeA.addDestination(nodeE, 7);

		Graph graph = new Graph();

		graph.addNode(nodeA);
		graph.addNode(nodeB);
		graph.addNode(nodeC);
		graph.addNode(nodeD);
		graph.addNode(nodeE);

		Algorithms algorithms = new Algorithms(graph);
		assertEquals(3, algorithms.numberOfTripsExactStops(nodeA, nodeC, 4));
	}

	@Test
	void testDijkstra() {
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");

		nodeA.addDestination(nodeB, 5);
		nodeB.addDestination(nodeC, 4);
		nodeC.addDestination(nodeD, 8);
		nodeD.addDestination(nodeC, 8);
		nodeD.addDestination(nodeE, 6);
		nodeA.addDestination(nodeD, 5);
		nodeC.addDestination(nodeE, 2);
		nodeE.addDestination(nodeB, 3);
		nodeA.addDestination(nodeE, 7);

		Graph graph = new Graph();

		graph.addNode(nodeA);
		graph.addNode(nodeB);
		graph.addNode(nodeC);
		graph.addNode(nodeD);
		graph.addNode(nodeE);

		Algorithms algorithms = new Algorithms(graph);
		assertEquals(9, algorithms.shortestRoute(graph, nodeA, nodeC));
	}

	@Test
	void testDijkstraLoop() {
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");

		nodeA.addDestination(nodeB, 5);
		nodeB.addDestination(nodeC, 4);
		nodeC.addDestination(nodeD, 8);
		nodeD.addDestination(nodeC, 8);
		nodeD.addDestination(nodeE, 6);
		nodeA.addDestination(nodeD, 5);
		nodeC.addDestination(nodeE, 2);
		nodeE.addDestination(nodeB, 3);
		nodeA.addDestination(nodeE, 7);

		Graph graph = new Graph();

		graph.addNode(nodeA);
		graph.addNode(nodeB);
		graph.addNode(nodeC);
		graph.addNode(nodeD);
		graph.addNode(nodeE);

		Algorithms algorithms = new Algorithms(graph);
		assertEquals(9, algorithms.shortestRoute(graph, nodeB, nodeB));
	}

	@Test
	void testCalculateDistance() {
		Node nodeA = new Node("A");
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");
		Node nodeD = new Node("D");
		Node nodeE = new Node("E");
//		AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
		nodeA.addDestination(nodeB, 5);
		nodeB.addDestination(nodeC, 4);
		nodeC.addDestination(nodeD, 8);
		nodeD.addDestination(nodeC, 8);
		nodeD.addDestination(nodeE, 6);
		nodeA.addDestination(nodeD, 5);
		nodeC.addDestination(nodeE, 2);
		nodeE.addDestination(nodeB, 3);
		nodeA.addDestination(nodeE, 7);

		Graph graph = new Graph();

		graph.addNode(nodeA);
		graph.addNode(nodeB);
		graph.addNode(nodeC);
		graph.addNode(nodeD);
		graph.addNode(nodeE);
		Algorithms algorithms = new Algorithms(graph);

		assertEquals(7, algorithms.amountOfTripsWithinDistance(nodeC, nodeC, 30));

	}

}