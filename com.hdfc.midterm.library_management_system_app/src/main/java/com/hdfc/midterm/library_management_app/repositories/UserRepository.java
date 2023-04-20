package com.hdfc.midterm.library_management_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hdfc.midterm.library_management_app.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
