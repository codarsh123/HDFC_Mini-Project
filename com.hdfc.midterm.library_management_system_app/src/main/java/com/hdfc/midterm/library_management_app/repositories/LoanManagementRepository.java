package com.hdfc.midterm.library_management_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hdfc.midterm.library_management_app.entities.LoanManagement;
@Repository
public interface LoanManagementRepository extends JpaRepository<LoanManagement, Long>{

//	public void deleteByUserUserIdAndBookBookId(long userId, long bookId);
	//public void deleteByUserUserIdAndBookBookId(User user, Book book);
	public LoanManagement findByUserUserIdAndBookBookId(long userId, long bookId);
}
