package by.bsuir.ticketbooking.dto;

import by.bsuir.ticketbooking.entity.Seat;
import by.bsuir.ticketbooking.entity.Sector;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record SeatDTO(
		Long id,
		Sector sector,
		@JsonProperty("seat_row_number")
		int seatRowNumber,
		@JsonProperty("seat_number")
		int seatNumber,
		double price
) {
	public static SeatDTO of(Seat seat) {
		return SeatDTO.builder()
				.id(seat.getId())
				.sector(seat.getSector())
				.seatRowNumber(seat.getSeatRowNumber())
				.seatNumber(seat.getSeatNumber())
				.price(seat.getPrice().doubleValue())
				.build();
	}
}
