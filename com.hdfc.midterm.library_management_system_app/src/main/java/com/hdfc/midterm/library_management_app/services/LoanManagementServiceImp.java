package com.hdfc.midterm.library_management_app.services;
/*
Name:Adarsh Verma
Date:  14-04-2023
Descreption:created service class for LoanManagement
	*/
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.midterm.library_management_app.dto.BookDTO;
import com.hdfc.midterm.library_management_app.dto.LoanManagementDTO;
import com.hdfc.midterm.library_management_app.dto.UserDTO;
import com.hdfc.midterm.library_management_app.entities.Book;
import com.hdfc.midterm.library_management_app.entities.LoanManagement;
import com.hdfc.midterm.library_management_app.entities.User;
import com.hdfc.midterm.library_management_app.exceptions.BookNotFoundException;
import com.hdfc.midterm.library_management_app.repositories.LoanManagementRepository;
@Service
public class LoanManagementServiceImp implements ILoanManagementService {

	@Autowired
	LoanManagementRepository repo;
	
	@Autowired
	IBookService bkservice;
	
	@Autowired
	IUserService uservice;
	
//	 public LoanManagementServiceImp(LoanManagementRepository repo) {
//	        this.repo = repo;
//	    }
	
//	@Autowired
//	IBorrowingService brservice;
	
	@Override
	public void createLoan(long userId, long bookId) throws BookNotFoundException {
		 User user = uservice.getUserById(userId);
	        Book book = bkservice.getBookById(bookId);
	        

	        LocalDate dueDate = LocalDate.now().plusDays(1);
	        LoanManagement loan = new LoanManagement();
	        loan.setUser(user);
	        loan.setBook(book);
	        loan.setDueDate(dueDate);
	        loan.setFine(0);
	        
	        repo.save(loan);
	        
	        
	        

	}

	@Override
	public LoanManagementDTO getLoan(long loanId) {
		
		LoanManagement loan= repo.findById(loanId).orElse(null);
		
		LoanManagementDTO dto = new LoanManagementDTO();
		dto.setLoanId(loan.getLoanId());
		dto.setUser(new UserDTO(loan.getUser().getUserId(), loan.getUser().getFirstName(), loan.getUser().getLastName(), loan.getUser().getEmail(), loan.getUser().getPassword(), loan.getUser().getAccountStatus()));
		dto.setBook(new BookDTO(loan.getBook().getBookId(), loan.getBook().getTitle(), loan.getBook().getAuthor(), loan.getBook().getSubject(), loan.getBook().getIsbn(), loan.getBook().getPublisher(), loan.getBook().getPublicationDate(), loan.getBook().getQuantity(), loan.getBook().getAvailableQuantity()));
		dto.setDueDate(loan.getDueDate());
		dto.setFine(loan.getFine());
		return dto;
	}

	@Override
	public void updateLoan(long loanId) {
		LoanManagement loan = repo.findById(loanId).orElse(null);
		if(loan.getDueDate().isBefore(LocalDate.now())) {
			long daysOverdue = ChronoUnit.DAYS.between(loan.getDueDate(), LocalDate.now());
	        double fine = daysOverdue * 50;
	        loan.setFine(fine);
		}
		
		repo.save(loan);
		
//		Borrowing borrow = new Borrowing();
//		 if (borrow.getStatus() == LoanStatus.Returned) {


	}

	@Override
	public String sendReminder(long loanId) throws BookNotFoundException {
		LoanManagement loan = repo.findById(loanId).orElse(null);
		String message = null;
		 if (ChronoUnit.DAYS.between(LocalDate.now(), loan.getDueDate()) <= 3) {
			 User user = uservice.getUserById(loanId);
		        Book book = bkservice.getBookById(loanId); 
		         message = "Dear " + user.getFirstName() + ",\n\nThis is a reminder that the following book is due in " + ChronoUnit.DAYS.between(LocalDate.now(), loan.getDueDate()) + " days:\n\n" +
		                "Book Title: " + book.getTitle() + "\n" +
		                "Author: " + book.getAuthor() + "\n" +
		                "Due Date: " + loan.getDueDate() + "\n\n" +
		                "Please return the book on or before the due date to avoid fines.\n\n" +
		                "Thank you for using our library!\n" +
		                "Library Staff"; 
		       
		 }
		
		return message;
	}

	@Override
	public void deleteByLoanId(long loanId) {
		repo.deleteById(loanId);
		
	}

	@Override
	public LoanManagement findByUserUserIdAndBookBookId(long userId, long bookId) {

		LoanManagement loan = repo.findByUserUserIdAndBookBookId(userId, bookId);
		
	    return loan;
	}

	@Override
	public void deleteByUserAndBookId(long userId, long bookId) {
		LoanManagement loan = findByUserUserIdAndBookBookId(userId, bookId);
	    deleteByLoanId(loan.getLoanId());
		
	}

	

}
