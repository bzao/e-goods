package com.yan.goods.trade.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface IDeliverySender {
	String OUTPUT ="outputDelivery";
	
	@Output(OUTPUT)
	SubscribableChannel send();
}
