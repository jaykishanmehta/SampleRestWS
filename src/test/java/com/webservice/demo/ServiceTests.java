package com.webservice.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.webservice.demo.model.Friend;
import com.webservice.demo.service.FriendService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceTests {

	@Autowired
	FriendService friendService;
	
	@Test
	public void testCreateReadDelete() {
		
		Friend friend = new Friend("PQRS", "WXYZ");
		Friend friendResult = friendService.save(friend);
		
		Iterable<Friend> friends = friendService.findAll();
		Assertions.assertThat(friends).extracting(Friend::getFname).contains("PQRS");
		
		friendService.delete(friendResult);
		friends = friendService.findAll();
		Assertions.assertThat(friends).extracting(Friend::getFname).noneMatch(f -> f.equalsIgnoreCase("PQRS"));
	}
}
