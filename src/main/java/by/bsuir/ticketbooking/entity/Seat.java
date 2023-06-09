package by.bsuir.ticketbooking.entity;

import by.bsuir.ticketbooking.entity.enums.Sector;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "bookings")
@Entity
@Table(name = "seat")
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Sector sector;

	@Column(name = "seat_row_number", nullable = false)
	private int seatRowNumber;

	@Column(name = "seat_number", nullable = false)
	private int seatNumber;

	@Column(nullable = false)
	private BigDecimal price;

	@Builder.Default
	@OneToMany(mappedBy = "seat", orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Booking> bookings = new ArrayList<>();

	public double getPriceDoubleValue() {
		return price.setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

}
