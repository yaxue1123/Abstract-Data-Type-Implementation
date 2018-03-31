package A6_Dijkstra;

import java.util.HashMap;

public class Vertex {
	long idNum;
	String label;
	HashMap<String, Edge> inEdges; // store all source nodes' label information
	HashMap<String, Edge> outEdges; // store all destination nodes' label information
	public int indegree;

	boolean known; //if cheapest path known, true
	long dv; //currentCheapestPath
	Vertex pv; // previous node
	
	public Vertex(long idNum, String label)
	{
		 	this.idNum = idNum;
		    this.label = label;
		    this.inEdges = new HashMap<String, Edge>();
		    this.outEdges = new HashMap<String, Edge>();
		    this.indegree = 0;
		    this.known = false;
		    this.dv = 0;
		    this.pv = null;
	}

	public void removeInEdge(Edge e)
	{
		this.inEdges.remove(e);
	}
	
	public void removeOutEdge(Edge e)
	{
		this.outEdges.remove(e);
	}
	
	public long getIdNum()
	{
		return idNum;
	}
}
