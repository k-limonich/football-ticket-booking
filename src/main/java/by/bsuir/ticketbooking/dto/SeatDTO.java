package by.bsuir.ticketbooking.dto;

import by.bsuir.ticketbooking.entity.enums.Sector;
import lombok.Builder;

@Builder
public record SeatDTO(
		Long id,
		Sector sector,
		int seatRowNumber,
		int seatNumber,
		double price,
		boolean isBooked,
		boolean isPending
) {
}
