package com.br.consumerprice.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles("test")
public class ErrorHandlerTreatmentTests {

	@InjectMocks
	public ErrorHandlerTreatment errorHandlerTreatment;
	
	@Test
	void testThrowException() {
		var throwable = mock(Throwable.class);
		AmqpRejectAndDontRequeueException e =assertThrows(AmqpRejectAndDontRequeueException.class, () -> {
			errorHandlerTreatment.handleError(throwable);
		});
		assertEquals("Shouldn't return to queue", e.getMessage());
	}
	
}
