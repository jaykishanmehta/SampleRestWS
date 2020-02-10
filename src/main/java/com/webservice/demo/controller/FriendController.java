package com.webservice.demo.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.ValidationException;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Api(tags = {"Friend Resource"})
@SwaggerDefinition(tags = {
    @Tag(name = "Friend Resource", description = "Handles all the request related to friend CRUD")
})
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
	public Friend create(@Valid @RequestBody Friend friend) {
		return friendService.save(friend);
	}
	
	
	@GetMapping("/friend")
	public Iterable<Friend> read() {
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
	public void delete(@PathVariable Integer id) {
		
		try {
			friendService.deleteById(id);			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
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
	
	@GetMapping("/wrongurl")
	public Friend somethingIsWrong(){
		throw new ValidationException("Something is wrong");
	}
	
}
