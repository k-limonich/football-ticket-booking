package by.bsuir.ticketbooking.exception;

import jakarta.persistence.EntityExistsException;

public class EmailAlreadyRegisteredException extends EntityExistsException {

	public EmailAlreadyRegisteredException() {
		super("Адрес электронной почты уже зарегистрирован");
	}
}
