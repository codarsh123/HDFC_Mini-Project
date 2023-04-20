package com.hdfc.midterm.library_management_app.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.hdfc.midterm.library_management_app.entities.Book;
import com.hdfc.midterm.library_management_app.entities.Reservation;
import com.hdfc.midterm.library_management_app.entities.User;
import com.hdfc.midterm.library_management_app.exceptions.BookNotFoundException;
import com.hdfc.midterm.library_management_app.repositories.ReservationRepository;
@SpringBootTest
@Disabled
class ReservationServiceImpTest {

	@Mock
	UserServiceImp uservice;
	
	@Mock
	BookServiceImp bservice;
	
	@Mock
	ReservationRepository repo;
	 
	@InjectMocks
	ReservationServiceImp service;
	
	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@Disabled
	void testReserveBook() throws BookNotFoundException {

		long bookId = 7L;
	    long userId = 6L;
	    Book book = new Book(bookId, "Test Book", "Test Author", "Test Subject", "1234567890", "Test Publisher", new Date(), 5, 5);
	    User user = new User(userId, "John", "Doe", "john.doe@example.com", "password", "active");
	    when(bservice.getBookById(bookId)).thenReturn(book);
	    when(uservice.getUserById(userId)).thenReturn(user);
	    when(repo.save(any(Reservation.class))).thenReturn(null);
	    service.reserveBook(bookId, userId);
	    ArgumentCaptor<Reservation> captor = ArgumentCaptor.forClass(Reservation.class);
	    verify(repo).save(captor.capture());
	    Reservation savedReservation = captor.getValue();
	    assertNotNull(savedReservation);
	}

	@Test
	@Disabled
	void testNotifyUserOfAvailableBook() throws BookNotFoundException {
		 ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		    System.setOut(new PrintStream(outputStream));
		long bookId = 7L;
	    long userId = 6L;
		User user = new User(userId, "John", "Doe", "johndoe@example.com", "password", "active");
	    Book book = new Book(bookId, "The Catcher in the Rye", "J.D. Salinger", "Fiction", "0316769177", "Little, Brown and Company", new Date(), 5, 5);
	    when(uservice.getUserById(user.getUserId())).thenReturn(user);
	    when(bservice.getBookById(book.getBookId())).thenReturn(book);
	    service.notifyUserOfAvailableBook(bookId, userId);
	    String expectedMessage = "Dear customer John The book \"The Catcher in the Rye\" is now available for checkout.\n";
	    String capturedOutput = outputStream.toString();
	    assertEquals(expectedMessage, capturedOutput);
	    System.setOut(System.out);
	}

}
