package com.yan.goods.trade.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yan.goods.order.domain.Order;
import com.yan.goods.trade.domain.Trade;
import com.yan.goods.trade.service.OrderService;
import com.yan.goods.trade.service.TradeService;
import com.yan.goods.trade.stream.DeliverySender;

@RestController
public class TradeFacadeImpl implements TradeFacade {
	
	private final static Logger logger = LoggerFactory.getLogger(TradeFacadeImpl.class);

	@Autowired
	private TradeService tradeService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private DeliverySender sender;

	@Override
	public void createTrade(@RequestBody Trade obj) {
		this.tradeService.createTrade(obj);
		
		//回填 订单里面的交易号
		Order order=this.orderService.findOrderById(obj.getOrderId());
		if (order.getId() == -1){
			logger.warn("订单服务已经被降级无法回填！");
			return;
		}
		logger.warn("开始回填订单！");
//		Order order=new Order();
//		order.setId(obj.getId());
		order.setTradeId(obj.getId());
		order.setTradeStatus(true);
		this.orderService.updateOrderByTrade(order);
	}
	
	@Override
	public void tradeOrder(int orderId) {
//		this.tradeService.createTrade(obj);
		
		//回填 订单里面的交易号
		Order order=this.orderService.findOrderById(orderId);
		if (order.getId() == -1){
			logger.warn("订单服务已经被降级无法回填！");
			return;
		} else {
			logger.info(order.toString());
		}
//		logger.warn("开始回填订单！");
////		Order order=new Order();
////		order.setId(obj.getId());
//		order.setTradeId(obj.getId());
//		order.setTradeStatus(true);
//		this.orderService.updateOrderByTrade(order);
	}

	@Override
	public void test() {
		try {
			sender.send(1, "admin", "苏州吴中区");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Trade findTradeById(Integer id) {
		return tradeService.findTradeById(id);
	}
}
