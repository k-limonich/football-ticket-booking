package by.bsuir.ticketbooking.controller;

import by.bsuir.ticketbooking.entity.FootballMatch;
import by.bsuir.ticketbooking.service.FootballMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

	private final FootballMatchService footballMatchService;

	@GetMapping("/")
	public String getHomePage(Model model) {
		var userAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		boolean isAdmin = userAuthorities.stream().anyMatch(u -> u.getAuthority().equals("ADMIN"));
		if (isAdmin) {
			return "redirect:/matches";
		}
		FootballMatch footballMatch = footballMatchService.getClosestFootballMatch();
		model.addAttribute("footballMatch", footballMatch);
		return "index";
	}
}
