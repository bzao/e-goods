package com.yan.goods.product.facade;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yan.goods.product.domain.Product;

@RequestMapping("/product")
public interface ProductFacade {
	
	@GetMapping("/list")
	public List<Product> listProduct();
	
	@GetMapping("/findAllProduct")
	public List<Product> findAllProduct();
	
	@GetMapping("/get")
	public Product getProduct(@RequestParam("id") int id);
	
	@GetMapping(value="/get1",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Product getProduct1(@RequestBody Product obj);
	
	@GetMapping("/get2")
	public Product getProduct2(@RequestParam("id") int id,@RequestParam("name") String name);
	
	@PostMapping("/add")
	public Product addProduct(@RequestBody Product obj);
}
