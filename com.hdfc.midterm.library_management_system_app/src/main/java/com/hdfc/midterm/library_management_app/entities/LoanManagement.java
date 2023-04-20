package com.hdfc.midterm.library_management_app.entities;
/*
Name:Adarsh Verma
Date:  06-04-2023
Descreption:created entity class for Loan Management
	*/
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Loan_Management")
public class LoanManagement {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "loan_id")
	    private long loanId;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id",nullable = false)
	    private User user;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "book_id",nullable = false)
	    private Book book;

	    @Column(name = "due_date")
	    private LocalDate dueDate;

	    @Column(name = "fine")
	    private double fine;
}
