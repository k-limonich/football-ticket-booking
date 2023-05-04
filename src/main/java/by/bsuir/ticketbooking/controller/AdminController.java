package by.bsuir.ticketbooking.controller;

import by.bsuir.ticketbooking.entity.FootballMatch;
import by.bsuir.ticketbooking.entity.enums.Status;
import by.bsuir.ticketbooking.service.BookingService;
import by.bsuir.ticketbooking.service.FootballMatchService;
import by.bsuir.ticketbooking.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

	private final FootballMatchService footballMatchService;
	private final BookingService bookingService;
	private final SeatService seatService;

	@GetMapping("/admin/matches")
	public String getMatches() {
		return "forward:/matches";
	}

	@GetMapping("/admin/matches/new")
	public String getNewMatchForm(Model model) {
		FootballMatch footballMatch = new FootballMatch();
		model.addAttribute("footballMatch", footballMatch);
		model.addAttribute("action", "new");
		model.addAttribute("action-href", "/admin/matches/new");
		return "match-form";
	}

	@PostMapping("/admin/matches/new")
	public String addMatch(@ModelAttribute(value = "footballMatch") FootballMatch footballMatch) {
		footballMatchService.addFootballMatch(footballMatch);
		return "redirect:/admin/matches";
	}

	@GetMapping("/admin/matches/{matchId}/edit")
	public String getEditMatchForm(@PathVariable Long matchId, Model model) {
		FootballMatch footballMatchToEdit = footballMatchService.getFootballMatchById(matchId);
		model.addAttribute("footballMatch", footballMatchToEdit);
		model.addAttribute("action", "edit");
		model.addAttribute("action-href",
				"/admin/matches/edit");
		return "match-form";
	}

	@PostMapping("/admin/matches/{matchId}/edit")
	public String editMatch(@PathVariable Long matchId,
							@ModelAttribute(value = "footballMatch") FootballMatch footballMatch) {
		footballMatchService.editFootballMatch(matchId, footballMatch);
		return "redirect:/admin/matches";
	}

	@PostMapping("/admin/matches/{matchId}/delete")
	public String deleteMatch(@PathVariable Long matchId) {
		footballMatchService.deleteFootballMatchById(matchId);
		return "redirect:/admin/matches";
	}

	@GetMapping("/admin/matches/{matchId}/bookings")
	public String getMatchBookings(@PathVariable Long matchId, Model model) {
		FootballMatch footballMatch = footballMatchService.getFootballMatchById(matchId);
		var seatRows = seatService.getAllSeatsForMatchSortedByRows(matchId);
		int bookedSeatsNumber = bookingService.countPaidBookingsByMatchIdAndStatus(
				matchId,
				Status.PAID
		);
		int pendingSeatsNumber = bookingService.countPaidBookingsByMatchIdAndStatus(
				matchId,
				Status.PENDING
		);
		int freeSeatsNumber = seatRows.size() * seatRows.get(0).size()
				- bookedSeatsNumber - pendingSeatsNumber;
		model.addAttribute("footballMatch", footballMatch);
		model.addAttribute("seatRows", seatRows);
		model.addAttribute("bookedSeatsNumber", bookedSeatsNumber);
		model.addAttribute("pendingSeatsNumber", pendingSeatsNumber);
		model.addAttribute("freeSeatsNumber", freeSeatsNumber);
		return "match-booking-admin";
	}
}
