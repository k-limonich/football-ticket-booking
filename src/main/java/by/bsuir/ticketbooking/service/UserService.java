package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.entity.User;

public interface UserService {

	User getUserById(Long id);
	User getUserByEmail(String email);
	boolean userEmailExists(String email);
	User saveUser(User user);
	void deleteUserById(Long id);

}
