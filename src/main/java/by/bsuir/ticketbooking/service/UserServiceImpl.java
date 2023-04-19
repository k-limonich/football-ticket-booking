package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.entity.User;
import by.bsuir.ticketbooking.exception.EmailAlreadyRegisteredException;
import by.bsuir.ticketbooking.exception.UserNotFoundException;
import by.bsuir.ticketbooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(
				() -> new UserNotFoundException(id)
		);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(
				() -> new UserNotFoundException(email)
		);
	}

	@Override
	public boolean userEmailExists(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public User saveUser(User user) {
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new EmailAlreadyRegisteredException();
		}
		return userRepository.save(user);
	}

	@Override
	public void deleteUserById(Long id) {
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepository.deleteById(id);
	}
}
