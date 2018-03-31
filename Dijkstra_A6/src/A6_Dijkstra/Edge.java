package A6_Dijkstra;

import java.util.HashSet;

public class Edge {
	public long idNum;
	public String eLabel;
	public Vertex sV;
	public Vertex dV;
	public long weight;
	
	public Edge( long idNum, Vertex sourceVertex, Vertex destinationVertex, String eLabel, long weight){
		this.idNum = idNum;
		this.eLabel = eLabel;
		this.sV = sourceVertex;
		this.dV = destinationVertex;
		this.weight = weight;
	}
	

	
	public long getIdNum() {
		return idNum;
	}
	

	public void setIdNum(long idNum) {
		this.idNum = idNum;
	}



	public String geteLabel() {
		return eLabel;
	}



	public void seteLabel(String eLabel) {
		this.eLabel = eLabel;
	}



	public Vertex getsV() {
		return sV;
	}



	public void setsV(Vertex sV) {
		this.sV = sV;
	}



	public Vertex getdV() {
		return dV;
	}



	public void setdV(Vertex dV) {
		this.dV = dV;
	}



	public long getWeight() {
		return weight;
	}



	public void setWeight(long weight) {
		this.weight = weight;
	}


}
