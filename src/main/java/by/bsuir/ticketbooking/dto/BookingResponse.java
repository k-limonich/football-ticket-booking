package by.bsuir.ticketbooking.dto;

import lombok.Builder;

@Builder
public record BookingResponse(
		Long id,
		FootballMatchResponse footballMatch,
		UserInfo userInfo,
		SeatDTO seat,
		PaymentResponse payment
) {
}
