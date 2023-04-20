package com.hdfc.midterm.library_management_app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hdfc.midterm.library_management_app.entities.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	public List<Book> findByTitle(String title);
	
	public List<Book> findByAuthor(String author);
	
	public List<Book> findBySubject(String subject);
}
