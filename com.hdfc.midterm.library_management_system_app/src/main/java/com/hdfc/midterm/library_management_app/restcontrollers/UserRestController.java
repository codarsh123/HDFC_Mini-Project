package com.hdfc.midterm.library_management_app.restcontrollers;
/*
Name:Adarsh Verma
Date:  11-04-2023
Descreption:created controller class for User
	*/
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.midterm.library_management_app.dto.UserDTO;
import com.hdfc.midterm.library_management_app.entities.User;
import com.hdfc.midterm.library_management_app.services.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

	@Autowired
	IUserService service;
	
	@PostMapping("/post/user")
	public User registerNewUser(@Valid @RequestBody UserDTO dto) {
		return service.registerNewUser(dto);
		
	}
	
	@PutMapping("/update/user")
	public User updateUserInformation(@RequestBody UserDTO dto) {		
		return service.updateUserInformation(dto);
	}
	
	@GetMapping("/getall/users")
	public List<User> getAllUser(){
		return service.getAllUser();
		}
	
	@GetMapping("/get/user/{userId}")
	public User getUserById(@PathVariable long userId) {
		return service.getUserById(userId);
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> deleteByUserId(@PathVariable long userId){
		service.deleteByUserId(userId);
		return  new ResponseEntity<String>("Record Deleted ",HttpStatus.OK);
		
	}
	
}
