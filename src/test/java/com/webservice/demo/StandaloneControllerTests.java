package com.webservice.demo;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.webservice.demo.controller.FriendController;
import com.webservice.demo.model.Friend;
import com.webservice.demo.service.FriendService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FriendController.class)
public class StandaloneControllerTests {

	@MockBean
	FriendService friendService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void testCreateReadDelete() throws Exception {
		Friend friend = new Friend("MNOP", "STUV");
		List<Friend> friends = Arrays.asList(friend);
		
		Mockito.when(friendService.findAll()).thenReturn(friends);
		
		mockMvc.perform(get("/friend"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$", Matchers.hasSize(1)))
	        .andExpect(jsonPath("$[0].first-name", Matchers.is("MNOP")));
		
	}

}
