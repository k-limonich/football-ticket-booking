package by.bsuir.ticketbooking.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class EventCreatorImpl implements EventCreator {

	private static final String CREATE_EVENT_QUERY = "CREATE EVENT %s " +
			"ON SCHEDULE AT CURRENT_TIME + INTERVAL %d MINUTE " +
			"DO DELETE FROM booking WHERE id = %d AND status = 'PENDING'";

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createEventToDeletePendingBookingAfterNMinutes(Long bookingId, int minutes) {
		String eventName = "delete_pending_booking_" + bookingId;
		String query = String.format(CREATE_EVENT_QUERY, eventName, minutes, bookingId);
		entityManager.createNativeQuery(query).executeUpdate();
	}
}
