package by.bsuir.ticketbooking.repository;

import by.bsuir.ticketbooking.entity.FootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FootballMatchRepository extends JpaRepository<FootballMatch, Long> {

	@Query("FROM FootballMatch m WHERE  m.date >= ?1 ORDER BY m.date")
	List<FootballMatch> findFootballMatchesOnAndAfterDate(LocalDate date);

	@Query("FROM FootballMatch m WHERE  m.date >= ?1 ORDER BY m.date LIMIT 1")
	FootballMatch findClosesFootballMatch(LocalDate date);

}
