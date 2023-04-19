package by.bsuir.ticketbooking.repository;

import by.bsuir.ticketbooking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

	@Query("FROM Seat s INNER JOIN Booking b WHERE b.footballMatch.id = ?1")
	List<Seat> findBookedSeatsOnFootballMatch(Long matchId);
}
