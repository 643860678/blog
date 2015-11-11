package com.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.domain.Users;
import com.project.service.SysService;

@Controller
public class LoginController {
	
	@Autowired
	SysService sysservice;
	/**
	 * 注册页面
	 * @return
	 */
	@RequestMapping(value="register.htm")
	public String register(Model model){
		model.addAttribute("token","tok");
		return "login/register";
	}
	/**
	 * 注册用户信息
	 * @return
	 */
	@RequestMapping(value="registerMes.htm")
	public String registerMes(Users users,HttpServletRequest request){
		if(sysservice.register(users)){
			request.getSession().setAttribute("nickName",users.getNickName());
			return "start";
		}
		return "404";
	}
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping(value="login.htm")
	public String login(){
		return "login/login";
	}
}
