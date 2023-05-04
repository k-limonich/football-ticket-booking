package by.bsuir.ticketbooking.controller;

import by.bsuir.ticketbooking.entity.Booking;
import by.bsuir.ticketbooking.entity.Payment;
import by.bsuir.ticketbooking.entity.User;
import by.bsuir.ticketbooking.service.BookingService;
import by.bsuir.ticketbooking.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class AccountController {

	private static final int PAGE_SIZE = 5;

	private final BookingService bookingService;
	private final PaymentService paymentService;

	@GetMapping("/account/bookings")
	public String getAccountBookings(Model model, @RequestParam Optional<Integer> page) {
		int requestedPage = page.orElse(1) - 1;
		User user = (User) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Page<Booking> bookings = bookingService.getBookingsByUserIdPaginated(
				user.getId(),
				PageRequest.of(
						requestedPage,
						PAGE_SIZE,
						Sort.by("id").descending()
				)
		);
		if (bookings.getTotalPages() > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, bookings.getTotalPages())
					.boxed().toList();
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("bookings", bookings);
		return "account-bookings";
	}

	@GetMapping("/account/payments")
	public String getAccountPayments(Model model, @RequestParam Optional<Integer> page) {
		int requestedPage = page.orElse(1) - 1;
		User user = (User) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Page<Payment> payments = paymentService.getPaymentByUserId(
				user.getId(),
				PageRequest.of(
						requestedPage,
						PAGE_SIZE,
						Sort.by("id").descending()
				)
		);
		if (payments.getTotalPages() > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, payments.getTotalPages())
					.boxed().toList();
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("payments", payments);
		return "account-payments";
	}
}
