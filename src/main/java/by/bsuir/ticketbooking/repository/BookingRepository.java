package by.bsuir.ticketbooking.repository;

import by.bsuir.ticketbooking.entity.Booking;
import by.bsuir.ticketbooking.entity.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	Page<Booking> findBookingsByUserId(Long userId, Pageable pageable);

	Booking findBookingByFootballMatchIdAndSeatId(Long matchId, Long seatId);

	int countBookingsByFootballMatchIdAndStatus(Long matchId, Status status);
}
