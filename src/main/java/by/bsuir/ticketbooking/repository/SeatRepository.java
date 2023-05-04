package by.bsuir.ticketbooking.repository;

import by.bsuir.ticketbooking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

	@Query("SELECT MAX(s.seatRowNumber) FROM Seat s")
	int findMaxSeatRowNumber();
}
