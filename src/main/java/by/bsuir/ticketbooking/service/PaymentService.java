package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.entity.Payment;

import java.util.List;

public interface PaymentService {

	Payment getPaymentById(Long id);
	List<Payment> getPaymentByUserId(Long userId);
	Payment createPayment(Payment payment);
}
