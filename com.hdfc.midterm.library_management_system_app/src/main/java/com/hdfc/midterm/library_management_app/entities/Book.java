package com.hdfc.midterm.library_management_app.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
Name:Adarsh Verma
Date:  06-04-2023
Descreption:created entity class for Book
	*/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "book")
public class Book {
	
	@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
    private long bookId;

    @NotBlank(message = "Title is required")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Author is required")
    @Column(name = "author")
    private String author;

    @NotBlank(message = "Subject is required")
    @Column(name = "subject")
    private String subject;

    @NotBlank(message = "ISBN is required")
    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
    @Column(name = "isbn")
    private String isbn;

    @NotBlank(message = "Publisher is required")
    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publication_date")
    private Date publicationDate;

    @Min(value = 1, message = "Quantity must be at least 1")
    @Column(name = "quantity")
    private int quantity;

    @Min(value = 0, message = "Available quantity cannot be negative")
    @Max(value = 10, message = "Available quantity cannot be greater than total quantity")
    @Column(name = "available_quantity")
    private int availableQuantity;
}
