package by.bsuir.ticketbooking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "bookings")
@Entity
@Table(name = "football_match")
public class FootballMatch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String competition;

	@Column(name = "home_team", nullable = false)
	private String homeTeam;

	@Column(name = "home_team_logo_url")
	private String homeTeamLogoUrl;

	@Column(name = "away_team", nullable = false)
	private String awayTeam;

	@Column(name = "away_team_logo_url")
	private String awayTeamLogoUrl;

	@Column(nullable = false)
	private LocalDate date;

	@Column(nullable = false)
	private LocalTime time;

	@Builder.Default
	@OneToMany(mappedBy = "footballMatch", orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Booking> bookings = new ArrayList<>();

	public String getDateFormatted() {
		return DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
				.withLocale(Locale.forLanguageTag("ru"))
				.format(date);
	}

	public String getTimeFormatted() {
		return time.format(DateTimeFormatter.ofPattern("HH:mm"));
	}
}
