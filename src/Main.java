public class Main {

	public static void main(String[] args) {
		System.out.println("Start");
		runTest();

		System.out.println("Done");

	}

	public static void runTest() {
//		AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
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
		String output1 = algorithms.calculateDistance(new Node[] { nodeA, nodeB, nodeC });
		String output2 = algorithms.calculateDistance(new Node[] { nodeA, nodeD });
		String output3 = algorithms.calculateDistance(new Node[] { nodeA, nodeD, nodeC });
		String output4 = algorithms.calculateDistance(new Node[] { nodeA, nodeE, nodeB, nodeC, nodeD });
		String output5 = algorithms.calculateDistance(new Node[] { nodeA, nodeE, nodeD });
		int output6 = algorithms.numberOfTripsMax(nodeC, nodeC, 3);
		int output7 = algorithms.numberOfTripsExactStops(nodeA, nodeC, 4);
		int output8 = algorithms.shortestRoute(graph, nodeA, nodeC);
		int output9 = algorithms.shortestRoute(graph, nodeB, nodeB);
		int output10 = algorithms.amountOfTripsWithinDistance(nodeC, nodeC, 30);
		System.out.println("Output #1 " + output1);
		System.out.println("Output #2 " + output2);
		System.out.println("Output #3 " + output3);
		System.out.println("Output #4 " + output4);
		System.out.println("Output #5 " + output5);
		System.out.println("Output #6 " + output6);
		System.out.println("Output #7 " + output7);
		System.out.println("Output #8 " + output8);
		System.out.println("Output #9 " + output9);
		System.out.println("Output #10 " + output10);

	}
}
