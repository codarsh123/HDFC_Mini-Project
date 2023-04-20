package com.hdfc.midterm.library_management_app.services;
/*
Name:Adarsh Verma
Date:  08-04-2023
Descreption:created service class for Book
	*/
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.midterm.library_management_app.dto.BookDTO;
import com.hdfc.midterm.library_management_app.entities.Book;
import com.hdfc.midterm.library_management_app.exceptions.BookNotFoundException;
import com.hdfc.midterm.library_management_app.repositories.BookRepository;
@Service
public class BookServiceImp implements IBookService {

	@Autowired
	BookRepository repo;
	
	@Override
	public Book addNewBook(BookDTO dto) {
		Book book = new Book();
		book.setBookId(dto.getBookId());
		book.setTitle(dto.getTitle());
		book.setAuthor(dto.getAuthor());
		book.setSubject(dto.getSubject());
		book.setIsbn(dto.getIsbn());
		book.setPublisher(dto.getPublisher());
		book.setPublicationDate(dto.getPublicationDate());
		book.setQuantity(dto.getQuantity());
		book.setAvailableQuantity(dto.getAvailableQuantity());
		return repo.save(book);
	}

	@Override
	public Book updateBook(BookDTO dto) {
		Book book = addNewBook(dto);
		return repo.save(book);
	}

	@Override
	public List<Book> getAllBook() {
		
		return repo.findAll();
	}

	@Override
	public void deleteByBookId(long bookId) throws BookNotFoundException {
		
		if(!repo.existsById(bookId)) {
			throw new BookNotFoundException(); 
		}
		repo.deleteById(bookId);

	}

	@Override
	public List<Book> findByTitle(String title) throws BookNotFoundException {
		
		if(repo.findByTitle(title).isEmpty())
			throw new BookNotFoundException();
		return repo.findByTitle(title);
	}

	@Override
	public List<Book> findByAuthor(String author) throws BookNotFoundException {
		if(repo.findByAuthor(author).isEmpty())
			throw new BookNotFoundException();
		return repo.findByAuthor(author);
	}

	@Override
	public List<Book> findBySubject(String subject) throws BookNotFoundException {
		if(repo.findBySubject(subject).isEmpty())
			throw new BookNotFoundException();

		return repo.findBySubject(subject);
	}

	@Override
	public Book getBookById(long bookId) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return repo.findById(bookId).orElseThrow(()-> new BookNotFoundException());
	}

	@Override
	public void returnBook(long bookId) {
//		 Book book = bookRepository.findById(bookId)
//			        .orElseThrow(() -> new RuntimeException("Book not found"));
//			    book.setAvailable(true);
//			    bookRepository.save(book);
		
	}

}
