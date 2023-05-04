package by.bsuir.ticketbooking.controller;

import by.bsuir.ticketbooking.entity.Booking;
import by.bsuir.ticketbooking.entity.Payment;
import by.bsuir.ticketbooking.entity.User;
import by.bsuir.ticketbooking.service.BookingService;
import by.bsuir.ticketbooking.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;
	private final BookingService bookingService;

	@PostMapping(value = "/matches/{matchId}/booking/payment",
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String processBookingPayment(@PathVariable Long matchId,
										@RequestBody MultiValueMap<String, String> formData) {
		var user = (User)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		var selectedSeatIds = formData.get("selected-seat-ids").stream().map(Long::valueOf).toList();
		Payment payment = Payment.builder()
				.user(user)
				.timestamp(LocalDateTime.now())
				.amount(BigDecimal.valueOf(Double.parseDouble(Objects
						.requireNonNull(formData.getFirst("amount")))))
				.build();
		List<Booking> bookings = bookingService
				.getBookingsByMatchIdAndSeatIds(matchId, selectedSeatIds);
		bookingService.assignPaymentToBookings(payment, bookings);
		paymentService.createPayment(payment);
		return "redirect:/account/bookings";
	}
}
