package by.bsuir.ticketbooking.controller;

import by.bsuir.ticketbooking.dto.mapper.FootballMatchMapper;
import by.bsuir.ticketbooking.service.FootballMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class FootballMatchController {

	private final FootballMatchService footballMatchService;
	private final FootballMatchMapper footballMatchMapper;

	@GetMapping("/matches")
	public String getUpcomingMatchesPage(Model model) {
		var footballMatches = footballMatchService.getAllUpcomingFootballMatches();
		var footballMatchesResponse = footballMatchMapper.toResponseList(footballMatches);
		model.addAttribute("footballMatches", footballMatchesResponse);
		return "matches";
	}


}
