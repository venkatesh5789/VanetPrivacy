package edu.cmu.wireless;

public class Vehicle {
	private String id;
	private Double pos;
	private Double speed;
	
	public Vehicle(String id) {
		super();
		this.setId(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getPos() {
		return pos;
	}

	public void setPos(Double pos) {
		this.pos = pos;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	
}
