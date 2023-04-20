package com.hdfc.midterm.library_management_app.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hdfc.midterm.library_management_app.entities.Book;
import com.hdfc.midterm.library_management_app.exceptions.BookNotFoundException;
@SpringBootTest
@Disabled
class BookServiceImpTest {

	@Autowired
	IBookService service;
	
	@Test
	void testGetAllBook() {
		List<Book> list = service.getAllBook(); 
		assertTrue(list.size()>0);
		assertEquals(list.get(1).getBookId(),2);
	}

	@Test 
	
	void testFindByTitle() throws BookNotFoundException {
		
		
		String title = "Lord of the Rings";
		List<Book> result = service.findByTitle(title);
		assertNotNull(result);
		assertEquals(title, result.get(0).getTitle() );
	}

	@Test	
	void testFindBySubject() throws BookNotFoundException {
		String subject = "fantasy novel";
		List<Book> result = service.findBySubject(subject);
		assertNotNull(result);
		assertEquals(subject, result.get(0).getSubject());
	}

}
