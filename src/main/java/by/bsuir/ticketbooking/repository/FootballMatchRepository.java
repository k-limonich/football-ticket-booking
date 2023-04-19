package by.bsuir.ticketbooking.repository;

import by.bsuir.ticketbooking.entity.FootballMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FootballMatchRepository extends JpaRepository<FootballMatch, Long> {

	@Query("FROM FootballMatch m WHERE  m.date >= ?1")
	List<FootballMatch> findFootballMatchesOnAndAfterDate(LocalDate date);

	@Query("FROM FootballMatch m WHERE m.date >= ?1 AND YEARWEEK(?1) = YEARWEEK(m.date)")
	List<FootballMatch> findFootballMatchesOnAndAfterDateOnThatWeek(LocalDate date);

	@Query("FROM FootballMatch m WHERE m.date >= ?1 " +
			" AND MONTH(?1) = MONTH(m.date) AND YEAR(?1) = YEAR(m.date)")
	List<FootballMatch> findFootballMatchesOnAndAfterDateAfterOnThatMonthAndYear(LocalDate date);
}
