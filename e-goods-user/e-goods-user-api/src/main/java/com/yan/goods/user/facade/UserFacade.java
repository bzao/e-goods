package com.yan.goods.user.facade;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/user")
public interface UserFacade {
	
	@GetMapping("/login")
	public Integer login(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord);
	
	@GetMapping("/getToken")
	public String getToken(@RequestParam("userName") String userName, @RequestParam("passWord") String passWord, HttpServletRequest request);
}
