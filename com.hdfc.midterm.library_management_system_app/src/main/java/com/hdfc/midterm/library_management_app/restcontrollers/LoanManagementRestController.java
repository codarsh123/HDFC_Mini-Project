package com.hdfc.midterm.library_management_app.restcontrollers;
/*
Name:Adarsh Verma
Date:  14-04-2023
Descreption:created controller class for LoanManagement
	*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.midterm.library_management_app.dto.LoanManagementDTO;
import com.hdfc.midterm.library_management_app.entities.LoanManagement;
import com.hdfc.midterm.library_management_app.exceptions.BookNotFoundException;
import com.hdfc.midterm.library_management_app.services.ILoanManagementService;

@RestController
@RequestMapping("/api/loans")
public class LoanManagementRestController {

	@Autowired
	ILoanManagementService service;
	
	@PostMapping("/loan/create")
	public ResponseEntity<String> createLoan(@RequestParam long userId,@RequestParam long bookId) {
		try {
		service.createLoan(userId, bookId);
		return ResponseEntity.ok("The loan has been sanctioned successfully.");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("/get/loan/{loanId}")
	public LoanManagementDTO getLoan(@PathVariable long loanId) {
		return service.getLoan(loanId);
	}
	
	@PutMapping("/update/loan/{loanId}")
	public ResponseEntity<String> updateLoan(@PathVariable long loanId) {
		try {
			service.updateLoan(loanId);
			return ResponseEntity.ok("The loan has been updated successfully.");
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PostMapping("/send/remind/{loanId}")
	public String sendReminder(@PathVariable long loanId) throws BookNotFoundException {
		return service.sendReminder(loanId);
	}
	
	@DeleteMapping("/delete/loan/{loanId}")
	public ResponseEntity<String> deleteByLoanId(@PathVariable long loanId) {
		service.deleteByLoanId(loanId);
		return new ResponseEntity<String>("Record Deleted ",HttpStatus.OK);

	}
	
	@DeleteMapping("/delete/by/{userId}/{bookId}")
	public ResponseEntity<String> deleteLoanByUserAndBookId(@PathVariable long userId,@PathVariable long bookId) throws BookNotFoundException {
		service.deleteByUserAndBookId(userId, bookId);
		return new ResponseEntity<String>("Record Deleted ",HttpStatus.OK);
	}
}
