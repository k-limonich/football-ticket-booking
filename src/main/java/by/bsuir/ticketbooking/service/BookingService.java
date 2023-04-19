package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.dto.BookingRequest;
import by.bsuir.ticketbooking.entity.Booking;

import java.util.List;

public interface BookingService {

	Booking getBookingById(Long id);
	List<Booking> getBookingsByUserId(Long userId);
	Booking createBooking(BookingRequest request);
	void deleteBookingById(Long id);
}
