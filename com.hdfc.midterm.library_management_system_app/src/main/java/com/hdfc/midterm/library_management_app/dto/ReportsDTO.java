package com.hdfc.midterm.library_management_app.dto;

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
public class ReportsDTO {
    private long reportId;
    private User user;
    private Book book;
    private String userActivity;
    private String bookStatus;
    private double finesCollected;

}
