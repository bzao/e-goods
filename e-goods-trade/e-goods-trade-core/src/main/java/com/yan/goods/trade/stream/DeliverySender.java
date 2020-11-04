package com.yan.goods.trade.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import com.yan.goods.delivery.domain.Address;

@Service
public class DeliverySender {
	
	@Autowired
	private IDeliverySender deliverySender;

	public void send(int userId, String userName, String address) throws InterruptedException {
		Address add = new Address(userId,userName,address);
		Message message=MessageBuilder.withPayload(add).build();
		deliverySender.send().send(message);
	}
}
