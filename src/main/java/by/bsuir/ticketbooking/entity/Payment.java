package by.bsuir.ticketbooking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "bookings")
@Entity
@Table(name = "payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = false)
	private BigDecimal amount;

	@Column(nullable = false)
	private LocalDateTime timestamp;

	@Builder.Default
	@OneToMany(mappedBy = "payment", orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Booking> bookings = new ArrayList<>();

	public void addBooking(Booking booking) {
		bookings.add(booking);
	}

	public String getTimestampFormatted() {
		return timestamp.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
	}

	public double getAmountDoubleValue() {
		return amount.setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
}
