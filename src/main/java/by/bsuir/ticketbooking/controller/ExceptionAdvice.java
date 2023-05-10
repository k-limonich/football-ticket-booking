package by.bsuir.ticketbooking.controller;

import by.bsuir.ticketbooking.dto.UserLoginRequest;
import by.bsuir.ticketbooking.dto.UserRegisterRequest;
import by.bsuir.ticketbooking.exception.EmailAlreadyRegisteredException;
import by.bsuir.ticketbooking.exception.EmailNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ModelAndView badCredentials() {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("ex", "Некорректный пароль");
		modelAndView.addObject("loginRequest", UserLoginRequest.builder().build());
		return modelAndView;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView emailNotRegistered(EmailNotFoundException e) {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("ex", e.getMessage());
		modelAndView.addObject("loginRequest", UserLoginRequest.builder().build());
		return modelAndView;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ModelAndView emailAlreadyRegistered(EmailAlreadyRegisteredException e) {
		ModelAndView modelAndView = new ModelAndView("register");
		modelAndView.addObject("ex", e.getMessage());
		modelAndView.addObject("registerRequest", UserRegisterRequest.builder().build());
		return modelAndView;
	}
}
