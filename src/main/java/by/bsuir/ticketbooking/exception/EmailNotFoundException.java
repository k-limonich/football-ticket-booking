package by.bsuir.ticketbooking.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmailNotFoundException extends UsernameNotFoundException {

	public EmailNotFoundException() {
		super("Адрес электронной почты не зарегистрирован");
	}

}
