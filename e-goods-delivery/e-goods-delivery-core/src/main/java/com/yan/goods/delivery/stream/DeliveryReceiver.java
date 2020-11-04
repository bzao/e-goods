package com.yan.goods.delivery.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import com.yan.goods.delivery.domain.Address;

@Service
@EnableBinding({IDeliveryReceiver.class})
public class DeliveryReceiver {
	
	@StreamListener(IDeliveryReceiver.INPUT)
	public void onReceive(Address address){
		System.out.println("receive:"+address.toString());
	}
}
