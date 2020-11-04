package com.yan.goods.trade.persistence;

import com.yan.goods.trade.domain.Trade;


public interface TradeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Trade record);

    int insertSelective(Trade record);

    Trade selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Trade record);

    int updateByPrimaryKey(Trade record);
}