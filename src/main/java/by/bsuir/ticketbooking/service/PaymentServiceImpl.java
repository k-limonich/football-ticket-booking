package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.entity.Payment;
import by.bsuir.ticketbooking.exception.PaymentNotFoundException;
import by.bsuir.ticketbooking.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final PaymentRepository paymentRepository;

	@Override
	public Payment getPaymentById(Long id) {
		return paymentRepository.findById(id)
				.orElseThrow(() -> new PaymentNotFoundException(id));
	}

	@Override
	public List<Payment> getPaymentByUserId(Long userId) {
		return paymentRepository.findPaymentsByUserId(userId);
	}

	@Override
	public Payment createPayment(Payment payment) {
		return paymentRepository.save(payment);
	}
}
