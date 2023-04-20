package com.hdfc.midterm.library_management_app.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ReservationDTO {
    private long reservationId;
    private long userId;
    private long bookId;
    private LocalDate reservationDate;

}
