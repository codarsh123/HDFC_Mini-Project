package com.hdfc.midterm.library_management_app.dto;

import java.util.Date;

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
public class BorrowingDTO {

    private long borrowingId;
    private User user;
    private Book book;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;
    private String status;

}
