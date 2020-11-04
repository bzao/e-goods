package com.yan.goods.zuul.filter;

//import org.springframework.boot.autoconfigure.web.ErrorController;

import org.springframework.boot.web.servlet.error.ErrorController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorGatewayController implements ErrorController{

	//指定路径
	@Override
	public String getErrorPath() {
		return "/error";
	}
	
	@RequestMapping("/error")
	public String error(){
		return "{\"result\":\"500 error！\"}";
	}
}
