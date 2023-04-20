package com.hdfc.midterm.library_management_app.dto;

import java.time.LocalDate;

import com.hdfc.midterm.library_management_app.entities.Book;
import com.hdfc.midterm.library_management_app.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoanManagementDTO {
    private long loanId;
    private UserDTO user;
    private BookDTO book;
    private LocalDate dueDate;
    private double fine;

    
}
