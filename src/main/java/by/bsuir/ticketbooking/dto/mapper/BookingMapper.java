package by.bsuir.ticketbooking.dto.mapper;

import by.bsuir.ticketbooking.dto.BookingRequest;
import by.bsuir.ticketbooking.dto.BookingResponse;
import by.bsuir.ticketbooking.dto.SeatDTO;
import by.bsuir.ticketbooking.entity.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingMapper {

	private final FootballMatchMapper footballMatchMapper;
	private final UserMapper userMapper;
	private final PaymentMapper paymentMapper;

	public Booking fromRequest(BookingRequest request) {
		return null;
	}

	public BookingResponse toResponse(Booking booking) {
		return BookingResponse.builder()
				.id(booking.getId())
				.footballMatch(footballMatchMapper.toResponse(booking.getFootballMatch()))
				.userInfo(userMapper.toUserInfo(booking.getUser()))
				.seat(SeatDTO.of(booking.getSeat()))
				.payment(paymentMapper.toResponse(booking.getPayment()))
				.build();
	}
}
