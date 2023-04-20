package com.hdfc.midterm.library_management_app.services;
/*
Name:Adarsh Verma
Date:  15-04-2023
Descreption:created service interface for Report
	*/
import com.hdfc.midterm.library_management_app.exceptions.BookNotFoundException;

public interface IReportService {

	public void generateReport(long userId, long bookId) throws BookNotFoundException;
	
	public void deleteByReportId(long reportId);
}
