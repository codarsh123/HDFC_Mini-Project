package com.hdfc.midterm.library_management_app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
	Name:Adarsh Verma
	Date:  06-04-2023
	Descreption:created entity class for User
		*/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long userId;
	
	 @Column(name = "first_name")
	 @NotBlank(message = "First name cannot be blank")
	private String firstName;
	 
	 @Column(name = "last_name")
	 @NotBlank(message = "Last name cannot be blank")
	private String lastName;
	 
	 @Column(name = "email")
	 @Email(message = "Invalid email format")
	@NotBlank(message = "Email cannot be blank")
	private String email;
	 
	 @Column(name = "password")
	 @NotBlank(message = "Password cannot be blank")
	private String password;
	 
	 @Column(name = "account_status")
	 @NotBlank(message = "set it to either active or inactive")
	private String accountStatus;
}
