package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {

	Page<Payment> getPaymentByUserId(Long userId, Pageable pageable);
	void createPayment(Payment payment);
}
