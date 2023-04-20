package com.hdfc.midterm.library_management_app.services;
/*
Name:Adarsh Verma
Date:  08-04-2023
Descreption:created service Interface for User
	*/
import java.util.List;

import com.hdfc.midterm.library_management_app.dto.UserDTO;
import com.hdfc.midterm.library_management_app.entities.User;


public interface IUserService {

	public User registerNewUser(UserDTO dto);
	
	public User updateUserInformation(UserDTO dto);
	
	public List<User> getAllUser();
	
	public void deleteByUserId(long userId);
	
	public User getUserById(long userId);
}
