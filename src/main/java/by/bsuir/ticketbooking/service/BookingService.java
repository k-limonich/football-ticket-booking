package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.entity.Booking;
import by.bsuir.ticketbooking.entity.Payment;
import by.bsuir.ticketbooking.entity.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookingService {

	Page<Booking> getBookingsByUserIdPaginated(Long userId, Pageable pageable);
	List<Booking> getBookingsByMatchIdAndSeatIds(Long matchId, List<Long> seatIds);
	int countPaidBookingsByMatchIdAndStatus(Long matchId, Status status);
	void createPendingBooking(Booking booking);
	void assignPaymentToBookings(Payment payment, List<Booking> bookings);
	void cancelSeatBookings(List<String> seatsIdsToCancel, Long matchId);
	void deleteBookingById(Long id);
}
