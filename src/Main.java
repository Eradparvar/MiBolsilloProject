import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	static Scanner k;

	public static void main(String[] args) throws IOException {
		k = new Scanner(System.in);
		System.out.println("Enter 1 to read input from keyboard");
		System.out.println("Enter 2 to read input from text file");
		String inputType = k.nextLine();
		if (inputType.equals("2")) {
			k = new Scanner(new File("textInput.txt"));
		}
		int input = 0;
		System.out.println("Enter 1 to run test graph");
		System.out.println("Enter 2 input a graph to run algorithms on");
		input = Integer.parseInt(k.nextLine());
		switch (input) {
		case 1: {
			runTest();
			break;
		}
		case 2: {
			boolean run = true;
			Graph graph = createCustomGraph();
			while (run) {
				showAlgorithms(graph);
				System.out.println("Do you want to run more algorithms on the graph y/n");
				inputType = k.nextLine();
				if (inputType.equals("n")) {
					run = false;
				}
			}
			break;

		}

		}
		System.out.println("Exiting program");

	}

	private static Graph createCustomGraph() {
		String graphInput = "";
		Graph graph = new Graph();
		while (!graphInput.contentEquals("done")) {
			System.out.println("Enter an edge followed by a weight for example: AB5");
			System.out.println("Enter the word done when finished");
			graphInput = k.nextLine();
			graph.addEdge(graphInput);
		}
		graph.finish();
		return graph;
	}

	private static void showAlgorithms(Graph graph) {
		String graphInput = "";
		System.out.println("Which test do you want to run");
		System.out.println("Calculate Distance: Enter 1");
		System.out.println("Number Of Trips Max: Enter 2");
		System.out.println("Number Of Trips Exact Stops: Enter 3");
		System.out.println("Shortest Route Distance: Enter 4");
		System.out.println("Amount Of Trips Within Distance: Enter 5");
		Algorithms algorithms = new Algorithms(graph);
		switch (Integer.parseInt(k.nextLine())) {
		case 1: {
			System.out.println(
					"Enter the cities you would like to calculate the distance separated by a space. Example: A B C");
			graphInput = k.nextLine();
			graphInput = graphInput.replaceAll("\\s", "");

			Node[] calculateDistance = new Node[graphInput.length()];
			for (int i = 0; i < graphInput.length(); i++) {
				Node node = graph.strToNode(Character.toString(graphInput.charAt(i)));
				calculateDistance[i] = node;
			}
			String result = algorithms.calculateDistance(calculateDistance);
			System.out.println("The distance is " + result);
			break;

		}
		case 2: {
			System.out.println("Enter starting, ending and max number of trips. Example CC3");
			graphInput = k.nextLine();
			Node startingNode = graph.strToNode(Character.toString(graphInput.charAt(0)));
			Node destinationNode = graph.strToNode(Character.toString(graphInput.charAt(1)));
			int numStops = Integer.parseInt(graphInput.replaceAll("[^0-9]+", ""));
			int result = algorithms.numberOfTripsMax(startingNode, startingNode, numStops);
			System.out.println("The number of max trips is " + result);
			break;
		}
		case 3: {
			System.out.println("Enter starting, ending and exact number of trips. Example AA4");
			graphInput = k.nextLine();
			Node startingNode = graph.strToNode(Character.toString(graphInput.charAt(0)).toUpperCase());
			Node destinationNode = graph.strToNode(Character.toString(graphInput.charAt(1)).toUpperCase());
			int numStops = Integer.parseInt(graphInput.replaceAll("[^0-9]+", ""));
			System.out.println(startingNode + "" + destinationNode + numStops);
			int result = algorithms.numberOfTripsExactStops(startingNode, destinationNode, numStops);
			System.out.println("The number of exact stops are " + result);
			break;
		}
		case 4: {
			System.out.println("Enter two cities to find the shortest route. Example AC");
			graphInput = k.nextLine();
			Node startingNode = graph.strToNode(Character.toString(graphInput.charAt(0)).toUpperCase());
			Node destinationNode = graph.strToNode(Character.toString(graphInput.charAt(1)).toUpperCase());
			int result = algorithms.shortestRoute(graph, startingNode, destinationNode);
			System.out.println("The length of the shortest route is " + result);
			break;
		}
		case 5: {
			System.out.println("Enter two cities followed the number of trips within distance. Example CC30");
			graphInput = k.nextLine();
			Node startingNode = graph.strToNode(Character.toString(graphInput.charAt(0)));
			Node destinationNode = graph.strToNode(Character.toString(graphInput.charAt(1)));
			int distanceLessThan = Integer.parseInt(graphInput.replaceAll("[^0-9]+", ""));
			int result = algorithms.amountOfTripsWithinDistance(startingNode, destinationNode, distanceLessThan);

			System.out.println("The length of the shortest route is " + result);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + graphInput);
		}
	}

	public static void runTest() {
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
