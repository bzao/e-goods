package com.yan.goods.delivery.facade; 

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yan.goods.delivery.domain.Delivery;

@RequestMapping("/delivery")
public interface DeliveryFacade {
	
	@GetMapping(value = "/createDelivery")
    public void createDelivery();
	
	@GetMapping(value = "/findDeliveryByName")
	public List<Delivery> findDeliveryByName(@RequestParam("name") String name);
	
	@GetMapping(value = "/updateDelivery")
	public void updateDelivery(String id, String aname);
	
	@GetMapping(value = "/removeDelivery")
	public void removeDelivery(String id);
	
	@GetMapping(value = "/selectPage")
	public List<Delivery> selectPage(Integer currentPage, Integer pageSize, String sendName);
}
 