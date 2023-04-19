package by.bsuir.ticketbooking.exception;

import jakarta.persistence.EntityNotFoundException;

public class PaymentNotFoundException extends EntityNotFoundException {

	public PaymentNotFoundException(Long id) {
		super("Payment with id = " + id + " not found");
	}
}
