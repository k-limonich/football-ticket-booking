package by.bsuir.ticketbooking.dto.mapper;

import by.bsuir.ticketbooking.dto.SeatDTO;
import by.bsuir.ticketbooking.entity.Seat;
import by.bsuir.ticketbooking.entity.enums.Status;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeatMapper {
	
	public SeatDTO toSeatDTO(Seat seat, Long matchId) {
		boolean seatIsBooked = seat.getBookings().stream().anyMatch(
				booking -> booking.getFootballMatch().getId().equals(matchId)
							&& booking.getStatus().equals(Status.PAID));
		boolean seatIsPending = seat.getBookings().stream().anyMatch(
				booking -> booking.getFootballMatch().getId().equals(matchId)
							&& booking.getStatus().equals(Status.PENDING));
		return SeatDTO.builder()
				.id(seat.getId())
				.sector(seat.getSector())
				.seatRowNumber(seat.getSeatRowNumber())
				.seatNumber(seat.getSeatNumber())
				.price(seat.getPriceDoubleValue())
				.isBooked(seatIsBooked)
				.isPending(seatIsPending)
				.build();
	}

	public List<SeatDTO> toSeatDTOList(List<Seat> seats, Long matchId) {
		return seats.stream().map(seat -> toSeatDTO(seat, matchId))
				.collect(Collectors.toList());
	}
}
