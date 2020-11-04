package com.yan.goods.order.facade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yan.goods.order.domain.Order;
import com.yan.goods.order.service.OrderService;

@RestController
public class OrderFacadeImpl implements OrderFacade {
	
	private final static Logger logger = LoggerFactory.getLogger(OrderFacadeImpl.class);

	@Autowired
	private OrderService orderService;

	@Override
	public List<Order> findOrderByUserId(Integer userId) {
		return this.orderService.findOrderByUserId(userId);
	}

	@Override
	public Integer createOrder(@RequestBody Order obj) {
		return this.orderService.createOrder(obj);
	}

	@Override
	public void updateOrderByTrade(@RequestBody Order obj) {
		this.orderService.updateOrderByTrade(obj);
	}

	@Override
	public Order findOrderById(Integer id) {
		logger.info("id:"+id+",come here！！");
		return this.orderService.findOrderById(id);
	}

	

}
