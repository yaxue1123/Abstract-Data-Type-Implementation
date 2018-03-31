package DiGraph_A5;

public class DiGraphPlayground {

	public static void main(String[] args) {

		// thorough testing is your responsibility
		//
		// you may wish to create methods like
		// -- print
		// -- sort
		// -- random fill
		// -- etc.
		// in order to convince yourself your code is producing
		// the correct behavior
		exTest();
	}

	public static void exTest() {
		DiGraph d = new DiGraph();
	
		d.addNode(0, "1");
		d.addNode(1, "2");
		d.addNode(2, "3");
		d.addNode(3, "4");
		d.addNode(4, "5");
		d.addNode(5, "6");
		d.addNode(6, "7");
		d.addNode(7, "8");
		d.addEdge(0, "1", "3", 0, null);
		d.addEdge(1, "3", "5", 0, null);
		d.addEdge(2, "1", "5", 0, null);
		d.addEdge(3, "6", "8", 0, null);
		d.addEdge(4, "6", "2", 0, null);
		d.addEdge(5, "8", "7", 0, null);
		d.addEdge(6, "7", "4", 0, null);
		d.addEdge(7, "2", "4", 0, null);
		d.addEdge(8, "4", "8", 0, null);

		System.out.println("numEdges: " + d.numEdges());
		System.out.println("numNodes: " + d.numNodes());
		printTOPO(d.topoSort());

	}

	public static void printTOPO(String[] toPrint) {
		System.out.print("TOPO Sort: ");
		for (String string : toPrint) {
			System.out.print(string + " ");
		}
		System.out.println();
	}

}
