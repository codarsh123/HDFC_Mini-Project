package com.hdfc.midterm.library_management_app.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.hdfc.midterm.library_management_app.dto.LoanManagementDTO;
import com.hdfc.midterm.library_management_app.entities.Book;
import com.hdfc.midterm.library_management_app.entities.LoanManagement;
import com.hdfc.midterm.library_management_app.entities.User;
import com.hdfc.midterm.library_management_app.exceptions.BookNotFoundException;
import com.hdfc.midterm.library_management_app.repositories.LoanManagementRepository;
@SpringBootTest
@Disabled
class LoanManagementServiceImpTest {

	@Mock
	UserServiceImp uservice;
	
	@Mock
	BookServiceImp bservice;
	
	@Mock
	LoanManagementRepository repo;
	
	@InjectMocks
	LoanManagementServiceImp service;
	
	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@Disabled
	void testGetLoan() throws BookNotFoundException {
		
		
		LoanManagement loan = new LoanManagement();
	    loan.setLoanId(8L);
	    User user = new User();
	    user.setUserId(5L);
	    user.setFirstName("John");
	    user.setLastName("Doe");
	    user.setEmail("johndoe@example.com");
	    user.setPassword("password");
	    user.setAccountStatus("active");
	    loan.setUser(user);
	    Book book = new Book();
	    book.setBookId(5L);
	    book.setTitle("The Catcher in the Rye");
	    book.setAuthor("J.D. Salinger");
	    book.setSubject("Fiction");
	    book.setIsbn("0316769487");
	    book.setPublisher("Little, Brown and Company");
	    book.setPublicationDate(new Date(97, 5, 26));
	    book.setQuantity(5);
	    book.setAvailableQuantity(2);
	    loan.setBook(book);
	    loan.setDueDate(LocalDate.of(2023, 4, 30));
	    loan.setFine(0);
	    
	    LoanManagementRepository mockRepo = Mockito.mock(LoanManagementRepository.class);
	    Mockito.when(mockRepo.findById(8L)).thenReturn(Optional.of(loan));
	    
	    //ILoanManagementService service = new LoanManagementServiceImp(mockRepo);
	    
	    LoanManagementDTO dto = service.getLoan(8L);
	    assertEquals(8L, dto.getLoanId());
	    assertEquals("John", dto.getUser().getFirstName());
	}

	@Test
	//@Disabled
	void testSendReminder() throws BookNotFoundException {
		long loanId = 8L;
        LocalDate dueDate = LocalDate.now().plusDays(1);
        LoanManagement loan = new LoanManagement();
        loan.setUser(new User());
        loan.setBook(new Book());
        loan.setDueDate(dueDate);
        when(repo.findById(loanId)).thenReturn(Optional.of(loan));
        when(uservice.getUserById(loanId)).thenReturn(new User());
        when(bservice.getBookById(loanId)).thenReturn(new Book());
        String message = service.sendReminder(loanId);
        assertNotNull(message);
        assertEquals("Dear null,\n\nThis is a reminder that the following book is due in 1 days:\n\n" +
                "Book Title: null\n" +
                "Author: null\n" +
                "Due Date: " + dueDate + "\n\n" +
                "Please return the book on or before the due date to avoid fines.\n\n" +
                "Thank you for using our library!\n" +
                "Library Staff", message);
	}

}
