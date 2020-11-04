package com.yan.goods.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import com.yan.goods.delivery.stream.IDeliveryReceiver;

/**
 * @version 1.0
 */
@EnableEurekaClient
@SpringBootApplication
@EnableBinding({IDeliveryReceiver.class})
public class DeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryApplication.class, args);
	}
}
