package com.hdfc.midterm.library_management_app.services;
/*
Name:Adarsh Verma
Date:  13-04-2023
Descreption:created service class for Reservation
	*/
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdfc.midterm.library_management_app.dto.ReservationDTO;
import com.hdfc.midterm.library_management_app.entities.Book;
import com.hdfc.midterm.library_management_app.entities.Reservation;
import com.hdfc.midterm.library_management_app.entities.User;
import com.hdfc.midterm.library_management_app.exceptions.BookNotFoundException;
import com.hdfc.midterm.library_management_app.repositories.ReservationRepository;
@Service
public class ReservationServiceImp implements IReservationServive {

	@Autowired
	ReservationRepository repo;
	
	@Autowired
	IUserService uservice;
	
	@Autowired
	IBookService bservice;
	
	@Override
	public Reservation createReservation(ReservationDTO dto) throws BookNotFoundException {
		User user = uservice.getUserById(dto.getUserId());
		Book book = bservice.getBookById(dto.getBookId());
		
		
		Reservation reservation = new Reservation();
		reservation.setUser(user);
		reservation.setBook(book);
		reservation.setReservationDate(dto.getReservationDate());
		
		return repo.save(reservation);
	}

	@Override
	public List<ReservationDTO> getAllReservation() {
		List<Reservation> reservations = repo.findAll();
		List<ReservationDTO> reservationDtos = new ArrayList<>();
		for (Reservation reservation : reservations) {
	        ReservationDTO reservationDto = new ReservationDTO(reservation.getReservationId(), reservation.getUser().getUserId(), reservation.getBook().getBookId(), reservation.getReservationDate());
	        reservationDtos.add(reservationDto);
	    }
		return reservationDtos;
		
	}

	@Override
	public ReservationDTO getReservationById(long reservationId) {
		Reservation reservation = repo.findById(reservationId) .orElseThrow(() -> new EntityNotFoundException("Reservation with id " + reservationId + " not found"));;
	    return new ReservationDTO(reservation.getReservationId(), reservation.getUser().getUserId(), reservation.getBook().getBookId(), reservation.getReservationDate());

	}

	@Override
	public void deleteReservation(long reservationId) {

		repo.deleteById(reservationId);
	}
	
	@Override
	public void reserveBook(long bookId, long userId) throws BookNotFoundException {
	    
	    Book book = bservice.getBookById(bookId);
	    User user = uservice.getUserById(userId);
	    
	    LocalDate reservationDate = LocalDate.now();
	    Reservation reservation = new Reservation();
	    reservation.setUser(user);
	    reservation.setBook(book);
	    reservation.setReservationDate(reservationDate);
	    
	    repo.save(reservation);
	    //throw new RuntimeException("The book is not available for borrowing so it is been reserved for you.");
	}
	
	public void notifyUserOfAvailableBook(long bookId, long userId) throws BookNotFoundException {
	    User user = uservice.getUserById(userId);
	    Book book = bservice.getBookById(bookId);

	    String message = "Dear customer "+user.getFirstName()+" The book \"" + book.getTitle() + "\" is now available for checkout.";
	    System.out.println(message);
	    
	}
}



