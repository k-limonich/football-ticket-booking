package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.dto.UserLoginRequest;
import by.bsuir.ticketbooking.dto.UserRegisterRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

	void register(UserRegisterRequest request, HttpServletRequest req);
	void login(UserLoginRequest request, HttpServletRequest req);
}
