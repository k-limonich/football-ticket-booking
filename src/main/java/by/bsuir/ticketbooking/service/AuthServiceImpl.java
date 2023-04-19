package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.dto.UserLoginRequest;
import by.bsuir.ticketbooking.dto.UserRegisterRequest;
import by.bsuir.ticketbooking.dto.mapper.UserMapper;
import by.bsuir.ticketbooking.exception.EmailNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserMapper userMapper;
	private final UserService userService;
	private final AuthenticationManager authManager;

	@Override
	public void register(UserRegisterRequest request, HttpServletRequest req) {
		var user = userMapper.from(request);
		userService.saveUser(user);
		var auth = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.email(),
						request.password()
				)
		);
		updateSecurityContext(auth, req);
	}

	@Override
	public void login(UserLoginRequest request, HttpServletRequest req) {
		if (!userService.userEmailExists(request.email())) {
			throw new EmailNotFoundException();
		}
		var auth = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.email(),
						request.password()
				)
		);
		updateSecurityContext(auth, req);
	}

	private void updateSecurityContext(Authentication auth, HttpServletRequest req) {
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(auth);
		HttpSession session = req.getSession(true);
		session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
	}

}
