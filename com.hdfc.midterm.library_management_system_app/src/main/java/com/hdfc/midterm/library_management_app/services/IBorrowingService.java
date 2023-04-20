package com.hdfc.midterm.library_management_app.services;
/*
Name:Adarsh Verma
Date:  12-04-2023
Descreption:created service interface for Borrowing
	*/
import com.hdfc.midterm.library_management_app.entities.Book;
import com.hdfc.midterm.library_management_app.entities.Borrowing;
import com.hdfc.midterm.library_management_app.entities.User;
import com.hdfc.midterm.library_management_app.exceptions.BookNotFoundException;

public interface IBorrowingService {
	
	public void borrowBook(long userId, long bookId) throws BookNotFoundException;
	
	public void returnBook(long userId, long bookId) throws BookNotFoundException;
	
	public Borrowing getUserBookForReport(User user, Book book);
	
	public String getStatusForReport(Borrowing borrow);
	
	public void deleteByBorrowingId(long borrowingId);
}
