package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.SysDao;
import com.project.domain.Users;
@Service
public class SysService {
	
	@Autowired
	SysDao sysdao;
	
	/**
	 *  保存用户的注册信息
	 * @param users
	 * @return
	 */
	public boolean register(Users users){
		return sysdao.register(users);
	}
}
