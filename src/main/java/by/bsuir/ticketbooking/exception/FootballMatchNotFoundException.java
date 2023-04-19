package by.bsuir.ticketbooking.exception;

import jakarta.persistence.EntityNotFoundException;

public class FootballMatchNotFoundException extends EntityNotFoundException {

	public FootballMatchNotFoundException(Long id) {
		super("Football match with id = " + id + " not found");
	}
}
