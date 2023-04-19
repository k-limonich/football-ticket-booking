package by.bsuir.ticketbooking.dto;

import lombok.Builder;

@Builder
public record UserInfo(
		String firstName,
		String lastName,
		String email
) {
}
