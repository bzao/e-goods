package com.yan.goods.order.persistence;

import java.util.List;

import com.yan.goods.order.domain.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
    
    public List<Order> findOrderByUserId(Integer userId);
}