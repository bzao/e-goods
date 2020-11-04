package com.yan.goods.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yan.goods.user.persistence.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public Integer login(String userName, String passWord) {
		return userMapper.login(userName, passWord);
	}
}
