package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.dto.SeatDTO;
import by.bsuir.ticketbooking.dto.mapper.SeatMapper;
import by.bsuir.ticketbooking.entity.Seat;
import by.bsuir.ticketbooking.exception.SeatNotFoundException;
import by.bsuir.ticketbooking.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

	private final SeatRepository seatRepository;
	private final SeatMapper seatMapper;

	@Override
	public Seat getSeatById(Long id) {
		return seatRepository.findById(id).orElseThrow(() -> new SeatNotFoundException(id));
	}

	public List<Seat> getSeatsByCommaSeparatedIdString(String seatIds) {
		var idArray = Arrays.stream(seatIds.split(","))
				.mapToLong(Long::valueOf).toArray();
		List<Seat> seats = new ArrayList<>();
		for (var id : idArray) {
			seats.add(getSeatById(id));
		}
		return seats;
	}

	@Override
	public List<List<SeatDTO>> getAllSeatsForMatchSortedByRows(Long matchId) {
		var seats = seatRepository.findAll();
		var seatsNotSorted = seatMapper.toSeatDTOList(seats, matchId);
		var maxRowNumber = seatRepository.findMaxSeatRowNumber();
		List<List<SeatDTO>> seatsSorted = new ArrayList<>();
		for (int i = maxRowNumber; i > 0; i--) {
			int currentRow = i;
			List<SeatDTO> sortedRow = seatsNotSorted.stream()
					.filter(seat -> seat.seatRowNumber() == currentRow)
					.sorted(Comparator.comparing(SeatDTO::sector)
							.thenComparingInt(SeatDTO::seatNumber))
					.toList();
			seatsSorted.add(sortedRow);
		}
		return seatsSorted;
	}

}
