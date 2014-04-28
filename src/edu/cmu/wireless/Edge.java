package edu.cmu.wireless;

import java.util.List;

public class Edge {
	private String id;
	private List<Lane> lanes;
	
	public Edge(String id) {
		super();
		this.setId(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Lane> getLanes() {
		return lanes;
	}

	public void setLanes(List<Lane> lanes) {
		this.lanes = lanes;
	}


}
