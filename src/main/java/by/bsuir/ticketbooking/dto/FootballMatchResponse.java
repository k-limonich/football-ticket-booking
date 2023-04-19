package by.bsuir.ticketbooking.dto;

import lombok.Builder;

@Builder
public record FootballMatchResponse(
		Long id,
		String competition,
		String homeTeam,
		String awayTeam,
		String homeTeamLogoUrl,
		String awayTeamLogoUrl,
		String date,
		String time
) {
}
