package com.webservice.demo.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.demo.model.Friend;
import com.webservice.demo.service.FriendService;

@RestController
public class FriendController {
	
	@Autowired
	FriendService friendService;
	
//	@PostMapping("/friend")
//	ResponseEntity<Friend> create(@RequestBody Friend friend) throws ValidationException {
//		if(friend.getId() == 0
//				&& friend.getFname() != null
//				&& friend.getLname() != null)
//			return new ResponseEntity<Friend>(friendService.save(friend), HttpStatus.OK);
//		else 
//			throw new ValidationException("Friend cannot be created");
//	}
	
	@PostMapping("/friend")
	Friend create(@Valid @RequestBody Friend friend) throws ValidationException {
		return friendService.save(friend);
	}
	
	
	@GetMapping("/friend")
	Iterable<Friend> read() {
		return friendService.findAll();
	}
	
	@PutMapping("/friend")
	ResponseEntity<Friend> update(@RequestBody Friend friend) {
		
		if(friendService.findById(friend.getId()).isPresent())
			return new ResponseEntity<Friend>(friendService.save(friend), HttpStatus.OK);
		else
			return new ResponseEntity<Friend>(friend, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/friend/{id}")
	void delete(@PathVariable Integer id) {
		friendService.deleteById(id);;
	}
	
	@GetMapping("/friend/{id}")
	Optional<Friend> findById(@PathVariable Integer id) {
		return friendService.findById(id);
	}
	
//	@GetMapping("/friend/search")
//	Iterable<Friend> findByQuery(@RequestParam("first") String firstName, @RequestParam("last") String lastName) {
//		return friendService.findByFnameAndLname(firstName, lastName);
//	}
	
	@GetMapping("/friend/search")
	Iterable<Friend> findAll(
			@RequestParam(value = "first", required = false) String firstName, 
			@RequestParam(value = "last", required = false) String lastName) {
		
		if(firstName != null && lastName != null)
			return friendService.findByFnameAndLname(firstName, lastName);
		else if(firstName != null)
			return friendService.findByFname(firstName);
		else if(lastName != null)
			return friendService.findByLname(lastName);
		else
			return friendService.findAll();
		
	}
}
