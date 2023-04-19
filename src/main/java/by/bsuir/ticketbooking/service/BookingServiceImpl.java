package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.dto.BookingRequest;
import by.bsuir.ticketbooking.entity.*;
import by.bsuir.ticketbooking.exception.BookingNotFoundException;
import by.bsuir.ticketbooking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;
	private final FootballMatchService footballMatchService;
	private final UserService userService;
	private final SeatService seatService;
	private final PaymentService paymentService;

	@Override
	public Booking getBookingById(Long id) {
		return bookingRepository.findById(id)
				.orElseThrow(() -> new BookingNotFoundException(id));
	}

	@Override
	public List<Booking> getBookingsByUserId(Long userId) {
		return bookingRepository.findBookingsByUserId(userId);
	}

	@Override
	public Booking createBooking(BookingRequest request) {
		FootballMatch footballMatch = footballMatchService
				.getFootballMatchById(request.footballMatchId());
		User user = userService.getUserById(request.userId());
		Seat seat = seatService.getSeatById(request.seatId());
		Payment payment = paymentService.getPaymentById(request.paymentId());
		Booking booking = Booking.builder()
				.footballMatch(footballMatch)
				.user(user)
				.seat(seat)
				.payment(payment)
				.build();

		return bookingRepository.save(booking);
	}

	@Override
	public void deleteBookingById(Long id) {
		if (!bookingRepository.existsById(id)) {
			throw new BookingNotFoundException(id);
		}
		bookingRepository.deleteById(id);
	}
}
