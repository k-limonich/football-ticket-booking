package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.entity.Booking;
import by.bsuir.ticketbooking.entity.Payment;
import by.bsuir.ticketbooking.entity.enums.Status;
import by.bsuir.ticketbooking.exception.BookingNotFoundException;
import by.bsuir.ticketbooking.repository.BookingRepository;
import by.bsuir.ticketbooking.repository.EventCreator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;
	private final EventCreator eventCreator;
	private final SeatService seatService;

	@Override
	public Page<Booking> getBookingsByUserIdPaginated(Long userId, Pageable pageable) {
		return bookingRepository.findBookingsByUserId(userId, pageable);
	}

	@Override
	public List<Booking> getBookingsByMatchIdAndSeatIds(Long matchId, List<Long> seatIds) {
		List<Booking> bookings = new ArrayList<>();
		for (var seatId : seatIds) {
			bookings.add(bookingRepository
					.findBookingByFootballMatchIdAndSeatId(matchId, seatId));
		}
		return bookings;
	}

	@Override
	public int countPaidBookingsByMatchIdAndStatus(Long matchId, Status status) {
		return bookingRepository.countBookingsByFootballMatchIdAndStatus(matchId, status);
	}

	@Override
	@Transactional
	public void createPendingBooking(Booking booking) {
		booking.setStatus(Status.PENDING);
		bookingRepository.save(booking);
		eventCreator.createEventToDeletePendingBookingAfterNMinutes(booking.getId(), 7);
	}

	@Override
	public void assignPaymentToBookings(Payment payment, List<Booking> bookings) {
		for (var booking : bookings) {
			booking.setPayment(payment);
			booking.setStatus(Status.PAID);
			payment.addBooking(booking);
		}
	}

	@Override
	public void cancelSeatBookings(List<String> seatIdsToCancel, Long matchId) {
		for (var seatId : seatIdsToCancel) {
			var seat = seatService.getSeatById(Long.valueOf(seatId));
			var bookingToCancel = seat.getBookings().stream().filter(booking ->
					booking.getFootballMatch().getId().equals(matchId)).findFirst()
					.orElseThrow(BookingNotFoundException::new);
			deleteBookingById(bookingToCancel.getId());
		}
	}

	@Override
	public void deleteBookingById(Long id) {
		if (!bookingRepository.existsById(id)) {
			throw new BookingNotFoundException(id);
		}
		bookingRepository.deleteById(id);
	}
}
