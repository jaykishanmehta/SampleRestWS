package com.webservice.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.webservice.demo.controller.FriendController;
import com.webservice.demo.model.Friend;

@SpringBootTest
public class IntegrationTest {

	@Autowired
	FriendController friendController;
	
	@Test
	public void testCreateReadDelete() {
		
		Friend friend = new Friend("ABCD", "XYZ");
		
		Friend friendResult = friendController.create(friend);
		
		Iterable<Friend> friends = friendController.read();
		Assertions.assertThat(friends).anyMatch(f -> f.getFname().equalsIgnoreCase("ABCD") 
				&& f.getLname().equalsIgnoreCase("XYZ"));
		
		friendController.delete(friendResult.getId());
		friends = friendController.read();
		Assertions.assertThat(friends).noneMatch(f -> f.getFname().equalsIgnoreCase("ABCD") 
				&& f.getLname().equalsIgnoreCase("XYZ"));
	}
}
