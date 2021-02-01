package com.ytp.springbootsql.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="flight")
public class Flight {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String source;
	private String destination;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	@Override
	public String toString() {
		return "Flight [id=" + id + ", name=" + name + ", source=" + source + ", destination=" + destination + "]";
	}
	public Flight(int id, String name, String source, String destination) {
		super();
		this.id = id;
		this.name = name;
		this.source = source;
		this.destination = destination;
	}
	public Flight() {
		super();
	}

}
