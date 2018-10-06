package com.lama.dsa.model.order;

/**
 * Uberoo Coursier representation.
 */
public class Coursier{
	
	private long id;
	private String name;
	private EnumCoursierStatus status;
	
	public Coursier(long id, String name,EnumCoursierStatus status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setStatus(EnumCoursierStatus status){
		this.status = status; 
	}

	public EnumCoursierStatus getStatus(){
		return status; 
	}
	
}
