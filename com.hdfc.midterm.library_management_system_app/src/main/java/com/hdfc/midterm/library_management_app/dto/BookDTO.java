package com.hdfc.midterm.library_management_app.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDTO {
	private long bookId;
    private String title;
    private String author;
    private String subject;
    private String isbn;
    private String publisher;
    private Date publicationDate;
    private int quantity;
    private int availableQuantity;
	
	
}
