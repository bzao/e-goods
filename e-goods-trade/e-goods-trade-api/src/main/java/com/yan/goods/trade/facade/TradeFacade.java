package com.yan.goods.trade.facade; 

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yan.goods.trade.domain.Trade;


@RequestMapping("/trade")
public interface TradeFacade {
	
	@RequestMapping(value = "/createTrade", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createTrade(Trade obj);
	
	@GetMapping("/delivery")
	public void test();
	
	@GetMapping("/findById")
	public Trade findTradeById(Integer id);
	
	@GetMapping("/findOrder")
	public void tradeOrder(int orderId);
}
 