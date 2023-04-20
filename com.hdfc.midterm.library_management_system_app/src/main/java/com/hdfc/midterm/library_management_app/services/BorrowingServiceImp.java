package com.hdfc.midterm.library_management_app.services;
/*
Name:Adarsh Verma
Date:  12-04-2023
Descreption:created service class for Borrowing
	*/
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.midterm.library_management_app.entities.Book;
import com.hdfc.midterm.library_management_app.entities.Borrowing;
import com.hdfc.midterm.library_management_app.entities.User;
import com.hdfc.midterm.library_management_app.exceptions.BookNotFoundException;
import com.hdfc.midterm.library_management_app.repositories.BorrowingRepository;
import com.hdfc.midterm.library_management_app.restcontrollers.LoanManagementRestController;
import com.hdfc.midterm.library_management_app.restcontrollers.ReservationRestController;

@Service
@Transactional
public class BorrowingServiceImp implements IBorrowingService {

	@Autowired
	BorrowingRepository repo;
	
	@Autowired
	IBookService bservice;
	
	@Autowired
	IUserService uservice;
	
	@Autowired
	IReservationServive rservice;
	
	@Autowired
	LoanManagementRestController lmcontroller;
	
	@Autowired
	ReservationRestController recontroller;
	
	@Autowired
	ILoanManagementService lservice;
	
	@Override
	public void borrowBook(long userId, long bookId) throws BookNotFoundException {

		Borrowing borrowing = repo.findByUserUserIdAndBookBookId(userId, bookId);
		if (borrowing != null) {
			int borrowedBooksCount = repo.countBorrowedBooksByBookId(bookId);
			if (borrowedBooksCount > 0) {
				System.out.println("The book is not available for borrowing so it is been reserved for you.");
				//rservice.reserveBook(bookId, userId);
				recontroller.reserveBook(bookId, userId);
	            
	        }else {
			borrowing.setReturnDate(null);
	        borrowing.setStatus("Borrowed");
	        borrowing.setDueDate(LocalDate.now().plusDays(1));
	        repo.save(borrowing);
	        
	        lmcontroller.createLoan(userId, bookId);
	        }
		}else {
		int borrowedBooksCount = repo.countBorrowedBooksByBookId(bookId);
		if (borrowedBooksCount > 0) {
			System.out.println("The book is not available for borrowing so it is been reserved for you.");
			//rservice.reserveBook(bookId, userId);
			recontroller.reserveBook(bookId, userId);
            
        }else {
		
		LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(1);
        LocalDate returnDate = null;
        String status = "Borrowed";
        
        Borrowing newborrowing = new Borrowing();
        newborrowing.setUser(uservice.getUserById(userId));
        newborrowing.setBook(bservice.getBookById(bookId));
        newborrowing.setBorrowDate(borrowDate);
        newborrowing.setDueDate(dueDate);
        newborrowing.setReturnDate(returnDate);
        newborrowing.setStatus(status);
        
        repo.save(newborrowing);
        //lservice.createLoan(userId, bookId);
        lmcontroller.createLoan(userId, bookId);
        
        } 
		}
	}

	@Override
	public void returnBook(long userId, long bookId) throws BookNotFoundException {
		 List<Borrowing> borrowedBooks = repo.findBorrowedBooksByUserId(userId);
		    Borrowing borrowing = borrowedBooks.stream()
		        .filter(b -> b.getBook().getBookId()==bookId)
		        .findFirst()
		        .orElseThrow(() -> new RuntimeException("The user has not borrowed the book."));
		    
		    LocalDate returnDate = LocalDate.now();
		    String status = "Returned";
		    
		    borrowing.setReturnDate(returnDate);
		    borrowing.setStatus(status);
		    
		    rservice.notifyUserOfAvailableBook(bookId, userId);
		    lservice.deleteByUserAndBookId(userId, bookId);	
		    repo.save(borrowing);
		    
		    
	}
	
	
	@Override
	public Borrowing getUserBookForReport(User user, Book book) {
		return repo.findByUserAndBook(user, book);
	}
	
	@Override
	public String getStatusForReport(Borrowing borrow) {
		if(borrow.getReturnDate() == null ) {
			LocalDate today = LocalDate.now();
			if(today.isBefore(borrow.getDueDate())) {
				return "Borrowed";
			}else {
				return "Overdue";
			}
		}else {
			return "Returned";
		}
	}
/*
	public void borrowBook(long userId, long bookId) {

		List<Borrowing> borrowedBooks = repo.findBorrowedBooksByUserId(userId);
	    Optional<Borrowing> optionalBorrowing = borrowedBooks.stream()
	            .filter(b -> b.getBook().getBookId() == bookId)
	            .findFirst();
	    if (optionalBorrowing.isPresent()) {
	    	Borrowing borrowing = optionalBorrowing.get();
	    	borrowing.setReturnDate(null);
	        borrowing.setStatus("Borrowed");
	        borrowing.setDueDate(LocalDate.now().plusDays(1));
	        repo.save(borrowing);
	        //lmcontroller.createLoan(userId, bookId);
	    }else {

		int borrowedBooksCount = repo.countBorrowedBooksByBookId(bookId);
		if (borrowedBooksCount > 0) {
			System.out.println("The book is not available for borrowing so it is been reserved for you.");
			//rservice.reserveBook(bookId, userId);
			recontroller.reserveBook(bookId, userId);
            
        }else {
		
		LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(1);
        LocalDate returnDate = null;
        String status = "Borrowed";
        
        Borrowing borrowing = new Borrowing();
        borrowing.setUser(uservice.getUserById(userId));
        borrowing.setBook(bservice.getBookById(bookId));
        borrowing.setBorrowDate(borrowDate);
        borrowing.setDueDate(dueDate);
        borrowing.setReturnDate(returnDate);
        borrowing.setStatus(status);
        
        repo.save(borrowing);
        //lservice.createLoan(userId, bookId);
        lmcontroller.createLoan(userId, bookId);
        
        } 
	    }

	}*/

	@Override
	public void deleteByBorrowingId(long borrowingId) {
		repo.deleteById(borrowingId);
		
	}
}
