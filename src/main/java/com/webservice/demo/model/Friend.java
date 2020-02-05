package com.webservice.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Friend {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String fname;
	private String lname;
	
	public Friend() {
		
	}
	
	public Friend(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.fname = firstName;
		this.lname = lastName;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	@Override
	public String toString() {
		return "Friend [id=" + id + ", firstName=" + fname + ", lastName=" + lname + "]";
	}
	
}

