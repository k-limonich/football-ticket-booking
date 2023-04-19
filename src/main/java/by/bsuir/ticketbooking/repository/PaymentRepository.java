package by.bsuir.ticketbooking.repository;

import by.bsuir.ticketbooking.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	List<Payment> findPaymentsByUserId(Long userId);
}
