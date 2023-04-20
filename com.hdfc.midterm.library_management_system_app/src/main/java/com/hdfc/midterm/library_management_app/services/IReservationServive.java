package com.hdfc.midterm.library_management_app.services;
/*
Name:Adarsh Verma
Date:  13-04-2023
Descreption:created service interface for Reservation
	*/
import java.util.List;

import com.hdfc.midterm.library_management_app.dto.ReservationDTO;
import com.hdfc.midterm.library_management_app.entities.Reservation;
import com.hdfc.midterm.library_management_app.exceptions.BookNotFoundException;

public interface IReservationServive {

	public Reservation createReservation(ReservationDTO dto) throws BookNotFoundException;
	public List<ReservationDTO> getAllReservation();
	public ReservationDTO getReservationById(long reservationId);
	public void deleteReservation(long reservationId);
	
	
	public void reserveBook(long bookId, long userId) throws BookNotFoundException;
	public void notifyUserOfAvailableBook(long bookId, long userId) throws BookNotFoundException;
}
