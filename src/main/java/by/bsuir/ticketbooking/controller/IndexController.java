package by.bsuir.ticketbooking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

	@GetMapping("/")
	public String getHomePage() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication());
		return "index";
	}
}
