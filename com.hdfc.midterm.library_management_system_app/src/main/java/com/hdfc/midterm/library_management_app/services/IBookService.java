package com.hdfc.midterm.library_management_app.services;
/*
Name:Adarsh Verma
Date:  08-04-2023
Descreption:created service Interface for Book repository
	*/
import java.util.List;

import com.hdfc.midterm.library_management_app.dto.BookDTO;
import com.hdfc.midterm.library_management_app.entities.Book;
import com.hdfc.midterm.library_management_app.exceptions.BookNotFoundException;

public interface IBookService {

	public Book addNewBook(BookDTO dto);
	
	public Book updateBook(BookDTO dto);
	
	public List<Book> getAllBook();
	
	public void deleteByBookId(long bookId) throws BookNotFoundException;
	
	public List<Book> findByTitle(String title) throws BookNotFoundException;
	
	public List<Book> findByAuthor(String author) throws BookNotFoundException;
	
	public List<Book> findBySubject(String subject) throws BookNotFoundException;
	
	public Book getBookById(long bookId) throws BookNotFoundException;
	
	public void returnBook(long bookId);
	//find by available quantity
	
	
}
