package by.bsuir.ticketbooking.dto;

public record BookingRequest(
		Long footballMatchId,
		Long userId,
		Long seatId,
		Long paymentId
) {
}
