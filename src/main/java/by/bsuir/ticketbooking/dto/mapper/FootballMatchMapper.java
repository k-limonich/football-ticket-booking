package by.bsuir.ticketbooking.dto.mapper;

import by.bsuir.ticketbooking.dto.FootballMatchRequest;
import by.bsuir.ticketbooking.dto.FootballMatchResponse;
import by.bsuir.ticketbooking.entity.FootballMatch;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class FootballMatchMapper {

	public FootballMatchResponse toResponse(FootballMatch footballMatch) {
		String date = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
				.withLocale(Locale.forLanguageTag("ru"))
				.format(footballMatch.getDate());
		String time = footballMatch.getTime()
				.format(DateTimeFormatter.ofPattern("HH:mm"));
		return FootballMatchResponse.builder()
				.id(footballMatch.getId())
				.competition(footballMatch.getCompetition())
				.homeTeam(footballMatch.getHomeTeam())
				.awayTeam(footballMatch.getAwayTeam())
				.homeTeamLogoUrl(footballMatch.getHomeTeamLogoUrl())
				.awayTeamLogoUrl(footballMatch.getAwayTeamLogoUrl())
				.date(date)
				.time(time)
				.build();
	}

	public List<FootballMatchResponse> toResponseList(List<FootballMatch> footballMatches) {
		return footballMatches.stream().map(this::toResponse)
				.collect(Collectors.toList());
	}

	public FootballMatch fromRequest(FootballMatchRequest request) {
		return FootballMatch.builder()
				.competition(request.competition())
				.homeTeam(request.homeTeam())
				.awayTeam(request.awayTeam())
				.homeTeamLogoUrl(request.homeTeamLogoUrl())
				.awayTeamLogoUrl(request.awayTeamLogoUrl())
				.date(request.date())
				.time(request.time())
				.build();
	}
}
