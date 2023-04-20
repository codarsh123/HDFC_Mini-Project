package com.hdfc.midterm.library_management_app.entities;


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
/*
Name:Adarsh Verma
Date:  06-04-2023
Descreption:created entity class for Borrowing
	*/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "borrowing")
public class Borrowing {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "borrowing_id")
    private long borrowingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id",nullable = false)
    private Book book;

    @Column(name = "borrow_date")
    private LocalDate borrowDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "status")
    private String status;
}
