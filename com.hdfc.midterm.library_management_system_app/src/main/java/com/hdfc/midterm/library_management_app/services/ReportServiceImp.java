package com.hdfc.midterm.library_management_app.services;
/*
Name:Adarsh Verma
Date:  15-04-2023
Descreption:created service class for Report
	*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.midterm.library_management_app.entities.Book;
import com.hdfc.midterm.library_management_app.entities.Borrowing;
import com.hdfc.midterm.library_management_app.entities.LoanManagement;
import com.hdfc.midterm.library_management_app.entities.Reports;
import com.hdfc.midterm.library_management_app.entities.User;
import com.hdfc.midterm.library_management_app.exceptions.BookNotFoundException;
import com.hdfc.midterm.library_management_app.repositories.ReportsRepository;
@Service
public class ReportServiceImp implements IReportService {

	@Autowired
	ReportsRepository repo;
	
	@Autowired
	IBookService bservice;
	
	@Autowired
	IUserService uservice;
	
	@Autowired
	IBorrowingService borrowservice;
	
	
	
	

	@Override
	public void generateReport(long userId, long bookId) throws BookNotFoundException {
		User user = uservice.getUserById(userId);
        Book book = bservice.getBookById(bookId);
        Borrowing borrow = borrowservice.getUserBookForReport(user, book);
        String status = borrowservice.getStatusForReport(borrow);
        
        LoanManagement loan = new LoanManagement();
        Reports report = new Reports();
        report.setUser(user);
        report.setBook(book);
        report.setUserActivity(user.getAccountStatus());
        report.setBookStatus(status);
        report.setFinesCollected(loan.getFine());
        
        repo.save(report);
		
	}


	@Override
	public void deleteByReportId(long reportId) {
		repo.deleteById(reportId);
		
	}
	
	

}
