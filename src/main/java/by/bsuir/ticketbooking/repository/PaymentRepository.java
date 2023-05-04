package by.bsuir.ticketbooking.repository;

import by.bsuir.ticketbooking.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	Page<Payment> findPaymentsByUserId(Long userId, Pageable pageable);
}
