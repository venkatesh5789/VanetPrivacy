package edu.cmu.wireless;

import java.util.ArrayList;

public class Timestep {
	private ArrayList<Edge> edges;
	double time;
	
	public Timestep() {
		super();
		edges = new ArrayList<Edge>();
		time = 0.00;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}
	
	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}
	
	public Edge getNthEdge (int edgeNumber) {
		int numberOfEdges = this.edges.size();
		
		if(edgeNumber < numberOfEdges) 
			return this.edges.get(edgeNumber);
		
		return null;		
	}
}
