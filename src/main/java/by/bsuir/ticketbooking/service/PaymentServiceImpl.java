package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.entity.Payment;
import by.bsuir.ticketbooking.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final PaymentRepository paymentRepository;

	@Override
	public Page<Payment> getPaymentByUserId(Long userId, Pageable pageable) {
		return paymentRepository.findPaymentsByUserId(userId, pageable);
	}

	@Override
	public void createPayment(Payment payment) {
		paymentRepository.save(payment);
	}

}
