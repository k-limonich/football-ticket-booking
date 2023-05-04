package by.bsuir.ticketbooking.repository;

public interface EventCreator {

	void createEventToDeletePendingBookingAfterNMinutes(Long bookingId, int minutes);
}
