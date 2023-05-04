package by.bsuir.ticketbooking.exception;

import jakarta.persistence.EntityNotFoundException;

public class BookingNotFoundException extends EntityNotFoundException {

	public BookingNotFoundException(Long id) {
		super("Booking with id = " + id + " not found");
	}

	public BookingNotFoundException() {
		super("Could not find booking");
	}
}
