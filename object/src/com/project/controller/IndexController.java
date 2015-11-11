package com.project.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class IndexController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 
	 * PC页面拦截
	 * 
	 * @param request
	 * @param model
	 * @param pagename
	 * @return
	 */
	@RequestMapping(value = "/{pagename}.htm", method = RequestMethod.GET)
	public String pagename(HttpServletRequest request, Model model,
			@PathVariable String pagename) {
		return pagename;
	}

	/** ------------------返回值处理---------------- **/
	public void returnvalues(HttpServletResponse response, String value) {
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(value);
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		System.out.println(new Timestamp(new Date().getTime()));
	}
}
























































