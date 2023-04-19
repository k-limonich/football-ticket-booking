package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.dto.FootballMatchRequest;
import by.bsuir.ticketbooking.entity.FootballMatch;
import by.bsuir.ticketbooking.exception.FootballMatchNotFoundException;
import by.bsuir.ticketbooking.repository.FootballMatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FootballMatchServiceImpl implements FootballMatchService {

	private final FootballMatchRepository footballMatchRepository;

	@Override
	public FootballMatch getFootballMatchById(Long id) {
		return footballMatchRepository.findById(id).orElseThrow(
				() -> new FootballMatchNotFoundException(id));
	}

	@Override
	public List<FootballMatch> getAllUpcomingFootballMatches() {
		return footballMatchRepository.findFootballMatchesOnAndAfterDate(LocalDate.now());
	}

	@Override
	public List<FootballMatch> getUpcomingFootballMatchesThisWeek() {
		return footballMatchRepository.findFootballMatchesOnAndAfterDateOnThatWeek(LocalDate.now());
	}

	@Override
	public FootballMatch addFootballMatch(FootballMatch footballMatch) {
		return footballMatchRepository.save(footballMatch);
	}

	@Override
	public FootballMatch editFootballMatch(Long matchToEdit, FootballMatchRequest request) {
		FootballMatch footballMatch = getFootballMatchById(matchToEdit);
		footballMatch.setCompetition(request.competition());
		footballMatch.setHomeTeam(request.homeTeam());
		footballMatch.setAwayTeam(request.awayTeam());
		footballMatch.setDate(request.date());
		footballMatch.setTime(request.time());
		return footballMatchRepository.save(footballMatch);
	}

	@Override
	public void deleteFootballMatchById(Long id) {
		if (!footballMatchRepository.existsById(id)) {
			throw new FootballMatchNotFoundException(id);
		}
		footballMatchRepository.deleteById(id);
	}
}
