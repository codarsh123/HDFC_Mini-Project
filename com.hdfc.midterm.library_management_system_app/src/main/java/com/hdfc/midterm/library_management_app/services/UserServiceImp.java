package com.hdfc.midterm.library_management_app.services;
/*
Name:Adarsh Verma
Date:  09-04-2023
Descreption:created service class for User
	*/
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.midterm.library_management_app.dto.UserDTO;
import com.hdfc.midterm.library_management_app.entities.User;
import com.hdfc.midterm.library_management_app.exceptions.UserNotFoundException;
import com.hdfc.midterm.library_management_app.repositories.UserRepository;
@Service
public class UserServiceImp implements IUserService {

	@Autowired
	UserRepository repo;
	
	@Override
	public User registerNewUser(UserDTO dto) {
		User user = new User();
		user.setUserId(dto.getUserId());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setAccountStatus(dto.getAccountStatus());
		
		return repo.save(user);
	}

	@Override
	public User updateUserInformation(UserDTO dto) {
		User user =registerNewUser(dto);
		return repo.save(user);
	}

	@Override
	public List<User> getAllUser() {
		
		return repo.findAll();
	}

	@Override
	public void deleteByUserId(long userId) throws UserNotFoundException {
		if(!repo.existsById(userId)) {
			throw new UserNotFoundException();
		}
		repo.deleteById(userId);
		

	}

	@Override
	public User getUserById(long userId) {
		// TODO Auto-generated method stub
		return repo.findById(userId).orElseThrow(() -> new UserNotFoundException());
	}

}
