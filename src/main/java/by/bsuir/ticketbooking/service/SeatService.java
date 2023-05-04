package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.dto.SeatDTO;
import by.bsuir.ticketbooking.entity.Seat;

import java.util.List;

public interface SeatService {

	Seat getSeatById(Long id);
	List<Seat> getSeatsByCommaSeparatedIdString(String seatIds);
	List<List<SeatDTO>> getAllSeatsForMatchSortedByRows(Long matchId);
}
