package com.hdfc.midterm.library_management_app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hdfc.midterm.library_management_app.entities.Book;
import com.hdfc.midterm.library_management_app.entities.Borrowing;
import com.hdfc.midterm.library_management_app.entities.User;
@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

	  
	  @Query("SELECT COUNT(b) FROM Borrowing b WHERE b.book.bookId = :bookId AND b.status = 'Borrowed'")
	   public int countBorrowedBooksByBookId(@Param("bookId") long bookId);
	  
	  
	  @Query("SELECT b FROM Borrowing b WHERE b.user.userId = :userId AND b.status = 'Borrowed'")
	    List<Borrowing> findBorrowedBooksByUserId(@Param("userId") long userId);
	  
	  @Query("SELECT b FROM Borrowing b WHERE b.user = :user AND b.book = :book" )
	  Borrowing findByUserAndBook(@Param("user") User user, @Param("book") Book book);


	  public Borrowing findByUserUserIdAndBookBookId(long userId, long bookId);
	  
}
