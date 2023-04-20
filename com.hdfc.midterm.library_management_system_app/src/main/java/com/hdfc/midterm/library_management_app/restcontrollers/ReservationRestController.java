package com.hdfc.midterm.library_management_app.restcontrollers;
/*
Name:Adarsh Verma
Date:  13-04-2023
Descreption:created controller class for Reservation
	*/
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hdfc.midterm.library_management_app.dto.ReservationDTO;
import com.hdfc.midterm.library_management_app.entities.Reservation;
import com.hdfc.midterm.library_management_app.exceptions.BookNotFoundException;
import com.hdfc.midterm.library_management_app.services.IReservationServive;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

	@Autowired
	IReservationServive service;
	
	@PostMapping("/post/reservation")
	public Reservation createReservation(@RequestBody ReservationDTO dto) throws BookNotFoundException {
		return service.createReservation(dto);	
	}
	
	@GetMapping("/get/allreserve")
	public List<ReservationDTO> getAllReservation(){
		
		return service.getAllReservation();
	}
	
	@GetMapping("/get/reservation/{reservationId}")
	public ReservationDTO getReservationById(@PathVariable long reservationId) {
		return service.getReservationById(reservationId);
	}
	
	@DeleteMapping("/delete/{reservationId}")
	public ResponseEntity<String> deleteReservation(@PathVariable long reservationId) {
		 service.deleteReservation(reservationId);
	return new ResponseEntity<String>("Record Deleted ",HttpStatus.OK);

	}
	
	@PostMapping("/reserve/book")
	public ResponseEntity<String> reserveBook(@RequestParam long bookId,@RequestParam long userId) throws BookNotFoundException{
		try {
		service.reserveBook(bookId, userId);
		 return ResponseEntity.ok("The book is not available for borrowing so it is been reserved for you.");
		}catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

	}
}
