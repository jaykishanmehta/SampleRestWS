package com.webservice.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.webservice.demo.controller.FriendController;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	FriendController friendController;
	
	@Test
	void contextLoads() {
	}

	@Test
	public void contextLoadsFriendController() {
		assertNotNull(friendController);
	}
	
}
