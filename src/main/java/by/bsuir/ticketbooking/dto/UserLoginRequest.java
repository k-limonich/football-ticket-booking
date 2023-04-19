package by.bsuir.ticketbooking.dto;

import lombok.Builder;

@Builder
public record UserLoginRequest(
		String email,
		String password
) {

}
