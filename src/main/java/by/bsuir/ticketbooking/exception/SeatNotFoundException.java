package by.bsuir.ticketbooking.exception;

import jakarta.persistence.EntityNotFoundException;

public class SeatNotFoundException extends EntityNotFoundException {

	public SeatNotFoundException(Long id) {
		super("Seat with id = " + id + " not found");
	}
}
