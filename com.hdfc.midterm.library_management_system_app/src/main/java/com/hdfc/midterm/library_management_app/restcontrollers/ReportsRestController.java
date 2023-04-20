package com.hdfc.midterm.library_management_app.restcontrollers;
/*
Name:Adarsh Verma
Date:  15-04-2023
Descreption:created controller class for Report
	*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.midterm.library_management_app.exceptions.BookNotFoundException;
import com.hdfc.midterm.library_management_app.services.IReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportsRestController {

	@Autowired
	IReportService service;
	
	@PostMapping("/generate/report")
	public ResponseEntity<String> generateReport(@RequestParam long userId,@RequestParam long bookId) throws BookNotFoundException {
		service.generateReport(userId, bookId);
		return ResponseEntity.ok("Report generated successfully.");
	}
	
	@DeleteMapping("/delete/{reportId}")
	public ResponseEntity<String> deleteByReportId(@PathVariable long reportId) {
		service.deleteByReportId(reportId);
		return  new ResponseEntity<String>("Report Record Deleted ",HttpStatus.OK);

	}
}
