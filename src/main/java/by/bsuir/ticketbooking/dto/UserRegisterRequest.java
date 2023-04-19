package by.bsuir.ticketbooking.dto;

import lombok.Builder;

@Builder
public record UserRegisterRequest(
		String firstName,
		String lastName,
		String email,
		String password
) {

}
