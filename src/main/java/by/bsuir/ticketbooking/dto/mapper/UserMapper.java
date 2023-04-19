package by.bsuir.ticketbooking.dto.mapper;

import by.bsuir.ticketbooking.dto.UserInfo;
import by.bsuir.ticketbooking.dto.UserLoginRequest;
import by.bsuir.ticketbooking.dto.UserRegisterRequest;
import by.bsuir.ticketbooking.entity.Role;
import by.bsuir.ticketbooking.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

	private final PasswordEncoder passwordEncoder;

	public User from(UserRegisterRequest request) {
		return User.builder()
				.firstName(request.firstName())
				.lastName(request.lastName())
				.email(request.email())
				.password(passwordEncoder.encode(request.password()))
				.role(Role.ROLE_CUSTOMER)
				.build();
	}

	public User from(UserLoginRequest request) {
		return User.builder()
				.email(request.email())
				.password(passwordEncoder.encode(request.password()))
				.build();
	}

	public UserInfo toUserInfo(User user) {
		return UserInfo.builder()
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.email(user.getEmail())
				.build();
	}
}
