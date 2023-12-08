package com.br.consumerprice.exception;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.util.ErrorHandler;

public class ErrorHandlerTreatment implements ErrorHandler {

	@Override
	public void handleError(Throwable t) {
		String queueName = ((ListenerExecutionFailedException) t).getFailedMessage().getMessageProperties().getConsumerQueue();
		String message = new String(((ListenerExecutionFailedException) t).getFailedMessage().getBody());
	    System.out.println("Queue error: " + queueName);
	    System.out.println("Error Message: " + message);
	    System.out.println(t.getCause().getMessage());
	    throw new AmqpRejectAndDontRequeueException("Shouldn't return to queue");
		
	}

}
