package com.yan.goods.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yan.goods.product.domain.Product;
import com.yan.goods.product.persistence.ProductMapper;

@Service
public class ProductService {
	@Autowired
	private ProductMapper productMapper;
	
	public List<Product> findAllProduct() {
		return productMapper.findAllProduct();
	}
	
	public List<Product> listProduct() {
		return productMapper.findAllProduct();
	}

	public Product getProduct(int id) {
		return  productMapper.selectByPrimaryKey(id);
	}
}
