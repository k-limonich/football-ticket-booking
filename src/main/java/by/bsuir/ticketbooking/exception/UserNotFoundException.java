package by.bsuir.ticketbooking.exception;

import jakarta.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {

	public UserNotFoundException(Long userId) {
		super("User with id = " + userId + " not found");
	}

	public UserNotFoundException(String email) {
		super("User with email \"" + email + "\" not found");
	}
}
