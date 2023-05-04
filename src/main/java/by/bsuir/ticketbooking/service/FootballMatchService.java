package by.bsuir.ticketbooking.service;

import by.bsuir.ticketbooking.entity.FootballMatch;

import java.util.List;

public interface FootballMatchService {

	FootballMatch getFootballMatchById(Long id);
	List<FootballMatch> getAllUpcomingFootballMatches();
	FootballMatch getClosestFootballMatch();
	void addFootballMatch(FootballMatch footballMatch);
	void editFootballMatch(Long matchToEdit, FootballMatch editedFootballMatch);
	void deleteFootballMatchById(Long id);
}
