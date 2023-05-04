package by.bsuir.ticketbooking.dto;

import lombok.Builder;

@Builder
public record PaymentDetails(
		String cardOwner,
		String cardNumber,
		Integer expirationMonth,
		Integer expirationYear,
		Integer cvv,
		Double amount
) {
}
