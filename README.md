# MiBolsilloProject


Instruction to run code:
1. Download to the code
2. Open the terminal where the project was download. (Assumption: The path Java variable is set)
``` 
javac *.java
java Main.java 
```
3. The 10 test cases will run and the results will be displayed in the terminal. 

The following methods can be used to find routing data from the `Algorithms` class

```
calculateDistance(new Node[] { nodeA, nodeB, nodeC })
  * finds the distance from a specific route as a Node array
   
 numberOfTripsMax(nodeC, nodeC, 3)
  * finds the number of trips within the max number of stops using a breadth first search
   
 numberOfTripsExactStops(nodeA, nodeC, 4)
  * finds the number of trips within an exact number of stops using a depth first search
   
shortestRoute(graph, nodeA, nodeC) 
  * finds the shortest route between two stations using Dijkstra's Algorithm
   
 amountOfTripsWithinDistance(nodeC, nodeC, 30)
  * finds all routes from start to end under a certain distance. A recursive algorithm
