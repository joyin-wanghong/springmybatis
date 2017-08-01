package com.huipu.test.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.huipu.test.mapper.UserMapper;

public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public void getUserById() {
		this.userMapper.getAllUser();
	}
	
}
