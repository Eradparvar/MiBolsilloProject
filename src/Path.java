import java.util.LinkedList;

public class Path {
	private LinkedList<Node> path = new LinkedList<Node>();
	private int totalPathDistance = 0;

	public Path() {

	}

	public Path(LinkedList<Node> path, int totalPathDistance) {
		LinkedList<Node> copyPath = new LinkedList<>();
		path.forEach(n -> copyPath.add(n));
		this.path = copyPath;
		this.totalPathDistance = totalPathDistance;
	}

	public Path(Path origPath) {
		this(origPath.getPath(), origPath.getTotalPathDistance());
	}

	public int getTotalPathDistance() {
		return totalPathDistance;
	}

	public void setTotalPathDistance(int totalPathDistance) {
		this.totalPathDistance = totalPathDistance;
	}

	public void addToTotalDistance(int addPathDistance) {
		totalPathDistance += addPathDistance;

	}

	public LinkedList<Node> getPath() {
		return path;
	}

	public void setPath(LinkedList<Node> path) {
		this.path = path;
	}

	public boolean addToPath(Node node) {
		if (path.peekLast() != null) {
			if (path.peekLast().equals(node)) {
				return false;
			}
		}
		path.addLast(node);
		return true;
	}

	public void addNodeToPathAndUpdateTotalPathDistance(Node nodeToAdd, int distanceFromTwoNodes, int maxDistance) {
		if ((distanceFromTwoNodes + this.totalPathDistance) < maxDistance) {
			if (path.peekLast() != null) {
				if (path.peekLast().equals(nodeToAdd)) {
				} else {
					this.path.addLast(nodeToAdd);
				}
			}
			this.addToTotalDistance(distanceFromTwoNodes);
		}
	}

	@Override
	public String toString() {
		return path.toString();
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Path)) {
			return false;
		}

		Path pathB = (Path) object;
		boolean pathEqual = true;
		for (Node node : path) {
			for (Node node2 : pathB.path) {
				if (node.getName().equals(node2.getName())) {
				} else {
					pathEqual = false;
				}
			}
		}
		return (pathEqual && this.totalPathDistance == pathB.totalPathDistance);

	}

}
