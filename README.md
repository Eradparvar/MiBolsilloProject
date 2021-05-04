# MiBolsilloProject


Instruction to run code:
1. Download to the code
2. Open the terminal where the project was download. (Assumption: The Java path variable is set)
``` 
javac *.java
java Main.java 
```
3. The 10 test cases will run and the results will be displayed in the terminal. 

The following methods can be used to find routing data from the `Algorithms` class

```
calculateDistance(new Node[] { nodeA, nodeB, nodeC })
  * finds the distance from a specific route as a Node array
   
 numberOfTripsMax(Node nodeA, Node nodeB, int numStops)
  * finds the number of trips within the max number of stops using a breadth first search
   
 numberOfTripsExactStops(Node nodeA, Node nodeB, int numStops)
  * finds the number of trips within an exact number of stops using a depth first search
   
shortestRoute(Graph graph, Node nodeA, Node nodeB) 
  * finds the shortest route between two stations using Dijkstra's Algorithm
   
 amountOfTripsWithinDistance(Node nodeA, Node nodeB, int distance)
  * finds all routes from start to end under a certain distance. A recursive algorithm
