package by.bsuir.ticketbooking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record FootballMatchRequest(
		String competition,
		String homeTeam,
		String awayTeam,
		String homeTeamLogoUrl,
		String awayTeamLogoUrl,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
		LocalDate date,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
		LocalTime time
) {

}
