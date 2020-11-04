package com.yan.goods.commons;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yan.goods.entity.Order;
import com.yan.goods.entity.Product;
import com.yan.goods.entity.Trade;

public class TestTrade {
	public static void main(String[] args) {
		String token = "xxxx";
		int id = createOrder(token);
//		System.out.println(id);
		doTrade(8, token);
	}
	
	public static Integer createOrder(String token)  {

		// 2.查看所有产品列表
		List<Product> products = findAllProduct(token);
		// 3.选第一款产品，下订单
		Product product = products.get(0);
		Order order = new Order();
		order.setUserId(1);
		order.setProductId(product.getId());
		order.setPrice(product.getPrice());
		Integer orderId= createOrder(order, token);
		return orderId;
	}
	
	public static void doTrade(int orderId, String token) {
//		 4.实现订单交易支付
		Trade trade = new Trade();
		trade.setUserId(1);
		trade.setOrderId(orderId);
		trade.setPrice(299);
		trade.setPayType((byte)1);// 支付类型:1-支付宝支付，2-网银在线，3-银联，4-微信支付
		trade.setPayStatus((byte)4);// 1 未付款 2 付款中 3 付款失败 4 付款完成
		trade.setGatewayPayNum(String.valueOf((new Date()).getTime()));// 网关支付流水号取当前时间
		trade.setGatewayPayPrice(299);
		trade.setGatewayPayTime(new Date());
		createTrade(trade,token);
	}
	
	public static List<Product> findAllProduct(String token) {
		String url = "http://localhost:9011/goods-product/product/list";
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("token", token);
		String html = HttpClientHelper.doGet(url, null, headers);
		System.out.println(html);
		List<Product> list = JSONArray.parseArray(html, Product.class);
		return list;
		
	}
	
	public static Integer createOrder(Order order, String token) {
		String url = "http://localhost:9011/goods-order/order/createOrder";
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("token", token);
		String json = JSON.toJSONString(order);
		String html = HttpClientHelper.doPostPaython(url, json, headers);
		System.out.println(html);
		int orderId = Integer.parseInt(html);
		return orderId;
		
	}
	
	public static void createTrade(Trade trade, String token) {
		String url = "http://localhost:9011/goods-trade/trade/createTrade";
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("token", token);
		String json = JSON.toJSONString(trade);
		String html = HttpClientHelper.doPostPaython(url, json, headers);
		System.out.println(html);
	}
}
