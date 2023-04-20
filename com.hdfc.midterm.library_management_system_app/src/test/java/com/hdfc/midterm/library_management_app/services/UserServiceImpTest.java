package com.hdfc.midterm.library_management_app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hdfc.midterm.library_management_app.entities.User;
@SpringBootTest
@Disabled
class UserServiceImpTest {

	@Autowired
	IUserService service;
	
	@Test
	void testGetAllUser() {
	
		List<User> list = service.getAllUser();
			assertTrue(list.size()>0);
			assertEquals(list.get(0).getUserId(),1);
		
	}

	@Test
	void testGetUserById() {
		
		User user =service.getUserById(2);
		if(user.getFirstName()== "john") {
			System.out.println(user);
		}
			
	}

}
