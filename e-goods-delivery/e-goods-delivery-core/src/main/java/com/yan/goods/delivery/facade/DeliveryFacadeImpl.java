package com.yan.goods.delivery.facade;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yan.goods.delivery.domain.Delivery;
import com.yan.goods.delivery.service.DeliveryService;

@RestController
public class DeliveryFacadeImpl implements DeliveryFacade{

	@Autowired
	private DeliveryService deliveryService;
	@Override
	public void createDelivery() {
		Delivery de = new Delivery();
		de.setArrivalAddress("苏州吴中4");
		de.setSendAddress("上海徐汇4");
		de.setArrivalName("徐小姐4");
		de.setSendName("苏先生");
		de.setSendTime(new Date());
//		de.setDeliveryId(deliveryId);
		deliveryService.saveDelivary(de);
	}
	
	@Override
	public List<Delivery> findDeliveryByName(String name) {
		List<Delivery>  de = deliveryService.findDeliveryByName(name);
		return de;
	}
	
	@Override
	public void updateDelivery(String id, String aname) {
		deliveryService.updateDelivery(id, aname);
	}
	
	@Override
	public void removeDelivery(String id) {
		deliveryService.removeDelivery(id);
	}
	
	public List<Delivery> selectPage(Integer currentPage, Integer pageSize, String sendName){
		List<Delivery> list = deliveryService.selectPage(currentPage, pageSize, sendName);
		return list;
	}
}
