package com.yan.goods.trade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.yan.goods.trade.stream.IDeliverySender;


@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.yan.goods.trade.persistence")
@EnableBinding({IDeliverySender.class})
public class TradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeApplication.class, args);
	}
}
