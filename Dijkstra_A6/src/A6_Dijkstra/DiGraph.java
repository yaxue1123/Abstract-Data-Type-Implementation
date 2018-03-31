package A6_Dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class DiGraph implements DiGraph_Interface {

	// in here go all your data and methods for the graph
	// and the topo sort operation
	
	HashMap<String, Vertex> nodes; // key: vertex label; value: Vertex
	HashSet<Long> edges;// store edge's id and make sure it's unique
	HashSet<Long> nodeIDs; // store node's id and make sure it's unique

	public DiGraph() { // default constructor
		// explicitly include this
		// we need to have the default constructor
		// if you then write others, this one will still be there
		this.nodes = new HashMap<String, Vertex>();
		this.edges = new HashSet<Long>();
		this.nodeIDs = new HashSet<Long>();
	}

	@Override
	// Add a vertex to the graph. If the vertex already exists, return false and do
	// nothing else.
	public boolean addNode(long idNum, String label) {
		// TODO Auto-generated method stub
	    //returns false if node number is not unique, or less than 0
        //returns false if label is not unique (or is null)
		if (label == null || nodes.containsKey(label))
			return false;
		if (idNum < 0L || nodeIDs.contains(idNum))
			return false;

		Vertex v = new Vertex(idNum, label);
		this.nodes.put(label, v);
		this.nodeIDs.add(idNum);
		return true;

	}

	@Override
	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		// TODO Auto-generated method stub
		Vertex sVertex = nodes.get(sLabel);
		Vertex dVertex = nodes.get(dLabel);

		Edge e = new Edge(idNum, sVertex, dVertex, eLabel, weight);

		// returns false if edge number is not unique or less than 0
		// returns false if source node is not in graph
		// returns false if destination node is not in graph
		// returns false is there already is an edge between these 2 nodes
		if (sVertex == null || dVertex == null || idNum < 0 || edges.contains(idNum))
			return false;
		if (sVertex.outEdges.containsKey(dVertex.label) || dVertex.inEdges.containsKey(sVertex.label))
			return false;

		edges.add(idNum);
		sVertex.outEdges.put(dLabel, e);
		dVertex.inEdges.put(sLabel, e);
		//modify destination vertex's indegree
		dVertex.indegree++;
		return true;

	}

	@Override
	public boolean delNode(String label) {
		// TODO Auto-generated method stub

		if (!nodes.containsKey(label))
			return false;
		else {
			Vertex n = nodes.get(label);
			//traverse the deleted vertex's related vertices, including in/out ones
			for (Vertex v : this.nodes.values()) {
				if (v.inEdges.containsKey(label))
				{//remember to modify the indegree
					nodes.get(label).indegree--;
					v.inEdges.remove(label);}

				if (v.outEdges.containsKey(label)) 
					v.outEdges.remove(label);
				
			}

			// clear all this vertex's related info, including delete it from the nodes,
			// delete related vertexs' in/out Edge, and nodeId
			n.inEdges.clear();
			n.outEdges.clear();
			this.nodes.remove(label);
			nodeIDs.remove(n.getIdNum());
			return true;
		}
	}


	@Override
	public boolean delEdge(String sLabel, String dLabel) {
		// TODO Auto-generated method stub
		// check if sV and dV exist in Nodes
		if (!this.nodes.containsKey(sLabel) || !this.nodes.containsKey(dLabel))
			return false;

		Vertex sV = nodes.get(sLabel);
		Vertex dV = nodes.get(dLabel);

		// check if edge exists in sV's outEdges and dV's inEdges
		if (!sV.outEdges.containsKey(dLabel) || (!dV.inEdges.containsKey(sLabel)))
			return false;

		Edge e = sV.outEdges.get(dLabel);
		long l = e.getIdNum();
		//modify the destination vertex's indegree
		dV.indegree--;
		edges.remove(l);
		sV.outEdges.remove(dLabel);
		dV.inEdges.remove(sLabel);
		return true;

	}

	@Override
	public long numNodes() {
		// TODO Auto-generated method stub
		return (long) this.nodes.size();
	}

	@Override
	public long numEdges() {
		// TODO Auto-generated method stub
		return (long) this.edges.size();
	}

	@Override
	// do not need do recursion
	public String[] topoSort() {
		// TODO Auto-generated method stub
		// As the toposort, each time deque a node whose indegree is 0, delete this node
		// from nodes, too.
		int size = (int) this.numNodes();
		String[] toposort = new String[size];// store the return values
		Queue<Vertex> vertices = new LinkedList(); // store the vertices whose indegree is 0

		int iteration = -1;
		//traverse all the vertices and store the indegree=0 ones into the queue first
		for (Vertex v : this.nodes.values()) {
			v.indegree = v.inEdges.size();
			if (v.indegree == 0) {
				vertices.add(v);
			}

		}

		if (vertices.size() == 0) // if the whole graph is a circle
			return null;

		while (!vertices.isEmpty()) {
			iteration++;
			toposort[iteration] = vertices.peek().label; // toposort: get one vertex from the queue and store it into
															// array

			if (toposort[iteration].equals(null))
				return null;

			// about to delete the vertex
			Vertex temp = vertices.poll();
			Vertex p;
			for (Edge e : temp.outEdges.values()) // Edge type
			{
				p = (Vertex) this.nodes.get(e.dV.label); // store destination vertex
				p.indegree--;
				// find whether this vertex's indegree is 0
				if (p.indegree == 0 || p.inEdges.size() == 0) {
					vertices.add(p); // add p to the queue
				}
			}
		}

		// judge whether there is a cycle besides the non-cycle part
		int countNotNull = 0;
		for (int i = 0; i < toposort.length; i++) {
			if (toposort[i] != null)
				countNotNull++;
		}
		if (countNotNull == size)
			return toposort;
		else
			return null;
	}
	// rest of your code to implement the various operations

	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		// TODO Auto-generated method stub
		Vertex s = this.nodes.get(label);
		s.dv = 0;
		s.pv = null;
		
		Vertex n;
		long d;
		
		int size = (int) (this.numNodes());
		ShortestPathInfo[] dijkstra = new ShortestPathInfo[size]; // why cannot be long?
		
		//Put start s node in table with dist 0, no need for a real table, just change the attributes
		
		//Initially, Put (0,s) on priority queue PQ
		MinBinHeap PQ = new MinBinHeap();
		EntryPair ep = new EntryPair(s,0);
		PQ.insert(ep);
		int i = 0;
		
		//Loop until PQ is empty
		while(PQ.size()!=0)
		{
			n = PQ.getMin().v;
			d = PQ.getMin().dist;
			
			if(n.known == true)
				{
				PQ.delMin();
				continue;
				}
			
			PQ.delMin();
			n.known = true; //Mark n as known
			dijkstra[i] = new ShortestPathInfo(n.label, n.dv);
			i++;
			
			//For each unknown node a adjacent to n, update distance
			for (Edge e : n.outEdges.values()) { // find all out nodes through out edges
					
					if(e.dV.pv==null)
					{
						e.dV.pv = n;
						e.dV.dv = d + e.weight;
						ep = new EntryPair(e.dV, e.dV.dv);
						PQ.insert(ep);
					}
				
					if( e.dV.pv!=null && e.dV.dv > d + e.weight)
						{
						e.dV.dv = d + e.weight;
						e.dV.pv = n;
						ep = new EntryPair(e.dV, e.dV.dv);
						PQ.insert(ep);
						}
				}
			
		}
		
		//For unconnected graphs, need mark other nodes' cheapest path as -1.
		for (Vertex v : this.nodes.values())
		{
			if(v.known==false)
				{
					v.dv = -1;
					dijkstra[i] = new ShortestPathInfo(v.label, v.dv);
					i++;
				}
		}
		
		//Store all nodes into an array
		
	
		return dijkstra;
	}
}