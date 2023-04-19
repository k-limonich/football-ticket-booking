package by.bsuir.ticketbooking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PaymentResponse(
		Long id,
		@JsonProperty("user_info")
		UserInfo userInfo,
		double amount,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
		LocalDateTime timestamp
) {
}
