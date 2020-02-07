package com.webservice.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.webservice.demo.model.Friend;

/**
 * Added some system tests to test friend controller
 * 
 * NOte: Problem with this test case is this tests need to execute in sequencial order how they defined
 * @author jaykishan.mehta
 *
 */
public class SystemTests {

	String server = "http://localhost:9999";
	
	RestTemplate restTemplate = new RestTemplate();
	
	String url = server + "/friend";
	Friend friend = new Friend("Gordon", "Moore");
	
	@Test
	public void testCreateReadDelete() {
		
		restTemplate.postForEntity(url, friend, Friend.class);
		
		Friend[] friends = restTemplate.getForObject(url, Friend[].class);
		Assertions.assertThat(friends).extracting(Friend::getFname).contains("Gordon");
		
	}

	
	@Test
	public void testReadDelete() {
		
		Friend[] friends = restTemplate.getForObject(url, Friend[].class);
		
		Assertions.assertThat(friends).extracting(Friend::getFname).contains("Gordon");
		Optional<Friend> optional = Arrays.stream(friends).filter(f -> f.getFname().equalsIgnoreCase("Gordon")).findAny();
		
		restTemplate.delete(url + "/" + optional.get().getId());
		
		Friend[] newFriends = restTemplate.getForObject(url, Friend[].class);
		Assertions.assertThat(newFriends).extracting(Friend::getFname).doesNotContain("Gordon");		
	}
	
	@Test
	public void testErrorHandlingReturnsBadRequest() {

		RestTemplate restTemplate = new RestTemplate();

		String url = server + "/wrongurl";

		try {
			restTemplate.getForEntity(url, String.class);
		} catch (HttpClientErrorException e) {
			assertTrue(HttpStatus.BAD_REQUEST.equals(e.getStatusCode()));
		}
	}
	
	
}
