package by.bsuir.ticketbooking.controller;

import by.bsuir.ticketbooking.dto.UserLoginRequest;
import by.bsuir.ticketbooking.dto.UserRegisterRequest;
import by.bsuir.ticketbooking.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@GetMapping("/login")
	public String login(Model model) {
		UserLoginRequest loginRequest = UserLoginRequest.builder().build();
		model.addAttribute("loginRequest", loginRequest);
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute(value = "loginRequest")
						UserLoginRequest loginRequest, HttpServletRequest req) {
		authService.login(loginRequest, req);
		return "redirect:/";
	}

	@GetMapping("/register")
	public String register(Model model) {
		UserRegisterRequest registerRequest = UserRegisterRequest.builder().build();
		model.addAttribute("registerRequest", registerRequest);
		return "register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute(value = "registerRequest")
						   UserRegisterRequest registerRequest,
						   HttpServletRequest req) {
		authService.register(registerRequest, req);
		return "redirect:/";
	}

}
