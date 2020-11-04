package com.yan.goods.product.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yan.goods.product.domain.Product;
import com.yan.goods.product.service.ProductService;

@RestController
public class ProductFacadeImpl implements ProductFacade{
	
	@Autowired
	private ProductService productService;
	
	public List<Product> findAllProduct() {
		return productService.findAllProduct();
	}
	
	public List<Product> listProduct() {
//		throw new RuntimeException("listExcepion");
		return productService.findAllProduct();
	}

	@Override
	public Product getProduct(int id) {
		return  productService.getProduct(id);
	}

	@Override
	public Product getProduct1(@RequestBody Product obj) {
		return obj;
	}

	@Override
	public Product getProduct2(int id, String name) {
		return new Product(id, name) ;
	}

	@Override
	public Product addProduct(@RequestBody Product obj) {
		return obj;
	}
}
