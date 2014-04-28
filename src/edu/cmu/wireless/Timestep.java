package edu.cmu.wireless;

import java.util.ArrayList;

public class Timestep {
	private ArrayList<Edge> edges;
	private double time;
	
	public Timestep() {
		super();
		edges = new ArrayList<Edge>();
		setTime(0.00);
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}
	
	public Timestep(double time) {
		super();
		this.time = time;
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

	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}
}
