package com.yan.goods.delivery.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface IDeliveryReceiver {
	String INPUT ="inputDelivery";
	
	@Input(INPUT)
	SubscribableChannel receive();
}
