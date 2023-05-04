package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.entity.FootballMatch;
import by.bsuir.ticketbooking.exception.FootballMatchNotFoundException;
import by.bsuir.ticketbooking.repository.FootballMatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public FootballMatch getClosestFootballMatch() {
		return footballMatchRepository.findClosesFootballMatch(LocalDate.now());
	}

	@Override
	public void addFootballMatch(FootballMatch footballMatch) {
		footballMatchRepository.save(footballMatch);
	}

	@Override
	@Transactional
	public void editFootballMatch(Long matchToEdit, FootballMatch editedFootballMatch) {
		FootballMatch footballMatch = getFootballMatchById(matchToEdit);
		footballMatch.setCompetition(editedFootballMatch.getCompetition());
		footballMatch.setHomeTeam(editedFootballMatch.getHomeTeam());
		footballMatch.setHomeTeamLogoUrl(editedFootballMatch.getHomeTeamLogoUrl());
		footballMatch.setAwayTeam(editedFootballMatch.getAwayTeam());
		footballMatch.setAwayTeamLogoUrl(editedFootballMatch.getAwayTeamLogoUrl());
		footballMatch.setDate(editedFootballMatch.getDate());
		footballMatch.setTime(editedFootballMatch.getTime());
		footballMatchRepository.save(footballMatch);
	}

	@Override
	public void deleteFootballMatchById(Long id) {
		if (!footballMatchRepository.existsById(id)) {
			throw new FootballMatchNotFoundException(id);
		}
		footballMatchRepository.deleteById(id);
	}
}
