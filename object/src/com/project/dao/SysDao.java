package com.project.dao;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.base.dao.SimpleJdbcDao;
import com.project.domain.Users;
import com.wash.utils.DaoUtil;


@Repository
public class SysDao{
	private static DaoUtil daoUtil=new DaoUtil();
	
	@Autowired
	SimpleJdbcDao simpleJdbcDao;
	
	/**
	 * 保存用户的注册信息
	 * @param users
	 * @return
	 */
	public boolean register(Users users){
		users.setRecordDate(new Date());
		String sql="insert into f_users(nickName,password,phone,email,recordDate) values(?,?,?,?,?)";
		return daoUtil.isBoolean(simpleJdbcDao.update(sql, new Object[]{users.getNickName(),users.getPassword(),users.getPhone(),users.getEmail(),users.getRecordDate()}));
	}
}





































