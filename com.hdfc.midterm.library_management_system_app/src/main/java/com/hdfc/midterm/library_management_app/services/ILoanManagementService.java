package com.hdfc.midterm.library_management_app.services;
/*
Name:Adarsh Verma
Date:  14-04-2023
Descreption:created service interface for LoanManagement
	*/
import com.hdfc.midterm.library_management_app.dto.LoanManagementDTO;
import com.hdfc.midterm.library_management_app.entities.LoanManagement;
import com.hdfc.midterm.library_management_app.exceptions.BookNotFoundException;

public interface ILoanManagementService {

	public void createLoan(long userId, long bookId) throws BookNotFoundException;
	
	 public LoanManagementDTO getLoan(long loanId);
	 
	 public void updateLoan(long loanId);
	 
	 public String sendReminder(long loanId) throws BookNotFoundException; 
	 
	 public void deleteByLoanId(long loanId);
	 
	 public LoanManagement findByUserUserIdAndBookBookId(long userId, long bookId);
	 
	 public void deleteByUserAndBookId(long userId, long bookId);
}
