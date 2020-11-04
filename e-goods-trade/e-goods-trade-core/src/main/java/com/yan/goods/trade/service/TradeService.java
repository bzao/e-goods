package com.yan.goods.trade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yan.goods.trade.domain.Trade;
import com.yan.goods.trade.persistence.TradeMapper;


@Service
public class TradeService  {

	@Autowired
	private TradeMapper tradeMapper;

	public void createTrade(Trade obj){
		this.tradeMapper.insert(obj);
	}
	
	public Trade findTradeById(Integer id){
		return tradeMapper.selectByPrimaryKey(id);
	}
}
