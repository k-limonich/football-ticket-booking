package by.bsuir.ticketbooking.controller;

import by.bsuir.ticketbooking.dto.PaymentDetails;
import by.bsuir.ticketbooking.entity.Booking;
import by.bsuir.ticketbooking.entity.Seat;
import by.bsuir.ticketbooking.entity.User;
import by.bsuir.ticketbooking.service.BookingService;
import by.bsuir.ticketbooking.service.FootballMatchService;
import by.bsuir.ticketbooking.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class BookingController {

	private final FootballMatchService footballMatchService;
	private final SeatService seatService;
	private final BookingService bookingService;

	@GetMapping("/matches/{matchId}/booking")
	public String getBookingPage(@PathVariable Long matchId,
								 @ModelAttribute("toPayment") String toPayment,
								 @ModelAttribute("selectedSeats") ArrayList<Seat> selectedSeats,
								 Model model) {
		var footballMatch = footballMatchService.getFootballMatchById(matchId);
		var seatRows = seatService.getAllSeatsForMatchSortedByRows(footballMatch.getId());
		Double totalCost = selectedSeats.stream().mapToDouble(Seat::getPriceDoubleValue).sum();
		var paymentDetails = PaymentDetails.builder().build();
		model.addAttribute("footballMatch", footballMatch);
		model.addAttribute("seatRows", seatRows);
		model.addAttribute("toPayment", toPayment);
		model.addAttribute("selectedSeats", selectedSeats);
		model.addAttribute("totalCost", totalCost);
		model.addAttribute("paymentDetails", paymentDetails);
		return "match-booking";
	}

	@PostMapping(value = "/matches/{matchId}/booking",
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ModelAndView proceedToPayment(@PathVariable Long matchId,
										 @RequestBody MultiValueMap<String, String> formData,
										 RedirectAttributes attributes) {
		var selectedSeatsIdCommaSeparated = formData.getFirst("selected-seats");
		var selectedSeats = seatService
				.getSeatsByCommaSeparatedIdString(selectedSeatsIdCommaSeparated);
		var footballMatch = footballMatchService.getFootballMatchById(matchId);
		var user = (User) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		System.out.println(user);
		for (var seat : selectedSeats) {
			Booking booking = Booking.builder()
					.footballMatch(footballMatch)
					.user(user)
					.seat(seat)
					.build();
			bookingService.createPendingBooking(booking);
			seat.getBookings().add(booking);
		}
		attributes.addFlashAttribute("toPayment", "true");
		attributes.addFlashAttribute("selectedSeats", selectedSeats);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(String.format("redirect:/matches/%s/booking", matchId));
		return modelAndView;
	}

	@PostMapping(value = "/matches/{matchId}/booking/cancel",
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String cancelBooking(@PathVariable Long matchId,
								@RequestBody MultiValueMap<String, String> formData) {
		var seatIdsToCancel = formData.get("seat-ids-to-cancel");
		bookingService.cancelSeatBookings(seatIdsToCancel, matchId);
		return String.format("redirect:/matches/%s/booking", matchId);
	}
}
