package com.webservice.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Friend {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@JsonProperty("first-name")
	private String fname;
	@JsonProperty("last-name")
	private String lname;
	
	private int age;
	
	@JsonIgnore
	private boolean married;
	
	@OneToMany(cascade = CascadeType.ALL)
	List<Address> addresses;
	
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
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isMarried() {
		return married;
	}

	public void setMarried(boolean married) {
		this.married = married;
	}
	
	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	@Override
	public String toString() {
		return "Friend [id=" + id + ", firstName=" + fname + ", lastName=" + lname + "]";
	}
	
}

