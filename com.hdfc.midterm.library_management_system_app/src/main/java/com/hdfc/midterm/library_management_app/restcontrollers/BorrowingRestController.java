package com.hdfc.midterm.library_management_app.restcontrollers;
/*
Name:Adarsh Verma
Date:  12-04-2023
Descreption:created controller class for Borrowing
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

import com.hdfc.midterm.library_management_app.services.IBorrowingService;

@RestController
@RequestMapping("/api/borrowing")
public class BorrowingRestController {

	@Autowired
	IBorrowingService service;
	//http://localhost:8181/api/borrowing/borrow?userId=1&bookId=1
	@PostMapping("/borrow")
	public ResponseEntity<String> borrowBook(@RequestParam(name = "userId", required = true) long userId,@RequestParam(name = "bookId", required = true) long bookId){
		try {
		service.borrowBook(userId, bookId);
		
		return ResponseEntity.ok("The book borrowing process compleated.");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	//http://localhost:8181/api/borrowing/return?userId=1&bookId=1
	@PostMapping("/return")
	public ResponseEntity<String> returnBook(@RequestParam("userId") long userId,@RequestParam("bookId") long bookId){
		try {
		service.returnBook(userId, bookId);
		return ResponseEntity.ok("The book has been returned successfully.");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());	
		}
	}
	
	@DeleteMapping("/delete/borrowing/{borrowingId}")
	public ResponseEntity<String> deleteByBorrowingId(@PathVariable long borrowingId) {
		service.deleteByBorrowingId(borrowingId);
		return new ResponseEntity<String>("Record Deleted ",HttpStatus.OK);

	}
}
