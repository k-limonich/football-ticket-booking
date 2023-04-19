package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.entity.Seat;

import java.util.List;

public interface SeatService {

	Seat getSeatById(Long id);
	List<Seat> getAllSeats();
	List<Seat> getBookedSeatsOnFootballMatch(Long matchId);
}
