package by.bsuir.ticketbooking.entity;

import by.bsuir.ticketbooking.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "football_match_id", nullable = false)
	private FootballMatch footballMatch;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "seat_id", nullable = false)
	private Seat seat;

	@ManyToOne
	@JoinColumn(name = "payment_id")
	private Payment payment;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
}
