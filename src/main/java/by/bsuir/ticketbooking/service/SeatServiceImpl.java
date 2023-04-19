package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.entity.Seat;
import by.bsuir.ticketbooking.exception.SeatNotFoundException;
import by.bsuir.ticketbooking.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

	private final SeatRepository seatRepository;

	@Override
	public Seat getSeatById(Long id) {
		return seatRepository.findById(id).orElseThrow(() -> new SeatNotFoundException(id));
	}

	@Override
	public List<Seat> getAllSeats() {
		return seatRepository.findAll();
	}

	@Override
	public List<Seat> getBookedSeatsOnFootballMatch(Long matchId) {
		return seatRepository.findBookedSeatsOnFootballMatch(matchId);
	}
}
