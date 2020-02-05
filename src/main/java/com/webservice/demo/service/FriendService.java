package com.webservice.demo.service;

import org.springframework.data.repository.CrudRepository;

import com.webservice.demo.model.Friend;

public interface FriendService extends CrudRepository<Friend, Integer> {
	
	Iterable<Friend> findByFnameAndLname(String fname, String lname);

	Iterable<Friend> findByFname(String fname);
	
	Iterable<Friend> findByLname(String lname);
}
