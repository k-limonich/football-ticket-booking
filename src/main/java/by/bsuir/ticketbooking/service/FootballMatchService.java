package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.dto.FootballMatchRequest;
import by.bsuir.ticketbooking.entity.FootballMatch;

import java.util.List;

public interface FootballMatchService {

	FootballMatch getFootballMatchById(Long id);
	List<FootballMatch> getAllUpcomingFootballMatches();
	List<FootballMatch> getUpcomingFootballMatchesThisWeek();
	FootballMatch addFootballMatch(FootballMatch footballMatch);
	FootballMatch editFootballMatch(Long matchToEdit, FootballMatchRequest request);
	void deleteFootballMatchById(Long id);
}
