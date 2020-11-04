package com.yan.goods.user.facade;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.yan.goods.user.jwt.JwtUtils;
import com.yan.goods.user.service.UserService;
import com.yan.goods.user.util.IPUtils;

@RestController
public class UserFacadeImpl implements UserFacade{
	
	private final static Logger logger = LoggerFactory.getLogger(UserFacadeImpl.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	public Integer login(String userName, String passWord) {
		return userService.login(userName, passWord);
	}

	@Override
	public String getToken(String userName, String passWord, HttpServletRequest request) {
		logger.info("ip is:"+IPUtils.getRealIP(request));
		return jwtUtils.generateToken(userName, passWord);
	}
}
