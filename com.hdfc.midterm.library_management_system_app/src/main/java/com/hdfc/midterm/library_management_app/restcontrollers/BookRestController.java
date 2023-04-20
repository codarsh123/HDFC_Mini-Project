package com.hdfc.midterm.library_management_app.restcontrollers;
/*
Name:Adarsh Verma
Date:  09-04-2023
Descreption:created controller class for Book
	*/
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.midterm.library_management_app.dto.BookDTO;
import com.hdfc.midterm.library_management_app.entities.Book;
import com.hdfc.midterm.library_management_app.exceptions.BookNotFoundException;
import com.hdfc.midterm.library_management_app.services.IBookService;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

	@Autowired
	IBookService service;
	
	@PostMapping("/post/book")
	public Book addNewBook(@Valid @RequestBody BookDTO dto) {
	return service.addNewBook(dto);
	}
	
	@PutMapping("/update/book")
	public Book updateBook(@RequestBody BookDTO dto) {
		return service.updateBook(dto);
	}
	
	@GetMapping("/getall/book")
	public List<Book> getAllBook(){
		return service.getAllBook();
	}
	
	@DeleteMapping("/delete/{bookId}")
	public ResponseEntity<String> deleteByBookId(@PathVariable long bookId) throws BookNotFoundException {
		service.deleteByBookId(bookId);
		return new ResponseEntity<String>("Record Deleted ",HttpStatus.OK);
	}
	
	@GetMapping("/get/title/{title}")
	public List<Book> findByTitle(@PathVariable String title) throws BookNotFoundException{
		return service.findByTitle(title);
	}
	
	@GetMapping("/get/author/{author}")
	public List<Book> findByAuthor(@PathVariable String author) throws BookNotFoundException{
		return service.findByAuthor(author);
	}
	
	@GetMapping("/get/subject/{subject}")
	public List<Book> findBySubject(@PathVariable String subject) throws BookNotFoundException{
		return service.findBySubject(subject);
		
	}
}
