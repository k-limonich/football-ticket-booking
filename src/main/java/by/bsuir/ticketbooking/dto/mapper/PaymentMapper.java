package by.bsuir.ticketbooking.dto.mapper;

import by.bsuir.ticketbooking.dto.PaymentResponse;
import by.bsuir.ticketbooking.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMapper {

	private final UserMapper userMapper;

	public PaymentResponse toResponse(Payment payment) {
		return PaymentResponse.builder()
				.id(payment.getId())
				.userInfo(userMapper.toUserInfo(payment.getUser()))
				.amount(payment.getAmountDoubleValue())
				.timestamp(payment.getTimestamp())
				.build();
	}
}
