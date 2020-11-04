package com.yan.goods.trade.hystrix;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.yan.goods.order.domain.Order;
import com.yan.goods.trade.service.OrderService;

import feign.hystrix.FallbackFactory;

@Component
public class OrderServiceFallbackFactory implements FallbackFactory<OrderService>{
	
	private Logger logger = LoggerFactory.getLogger(OrderServiceFallbackFactory.class);

	@Override
	public OrderService create(Throwable arg0) {
		return new OrderService() {
			@Override
			public List<Order> findOrderByUserId(Integer userId) {
				logger.warn("OrderService findOrderByUserId fallback exception:",arg0);
				List<Order> order= new ArrayList<Order>();
				order.add(new Order(-1));
				return order;
			}

			@Override
			public Integer createOrder(Order obj) {
				logger.warn("OrderService createOrder fallback exception:",arg0);
				return -1;
			}

			@Override
			public void updateOrderByTrade(Order obj) {
				logger.warn("OrderService updateOrderByTrade fallback exception:",arg0);
			}

			@Override
			public Order findOrderById(Integer id) {
				logger.warn("订单服务降级 exception:",arg0);
				Order order= new Order(-1);
				return order;
			}
		};
	}

}
