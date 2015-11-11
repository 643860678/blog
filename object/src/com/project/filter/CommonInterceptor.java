package com.project.filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.domain.Spoor;
import com.project.service.LeaveNoteService;
import com.wash.utils.GetIp;
@Controller
public class CommonInterceptor extends HandlerInterceptorAdapter{
	private static GetIp gip=new GetIp();
	private static Map<String,List<Long>> manage=new HashMap<String,List<Long>>();
	@Autowired
	LeaveNoteService lns;
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//System.out.println("afterCompletion:所有请求完成之后执行的方法");
		//System.out.println(request.getRequestURI());
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println("postHandle:在Servlet执行结束后执行");
		//System.out.println(request.getRequestURI());
	}
	//http://blog.csdn.net/tonytfjing/article/details/39207551
	/* (non-Javadoc)
	 * 拦截所有请求;在所有请求发送到servlet之前执行.
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String ip=gip.getIpAddr(request);
		long time=System.currentTimeMillis();
		List<Long> list=null;
		if(manage.get(ip)==null){
			list=new ArrayList<Long>();
		}else{
			list=manage.get(ip);
			for (int i = 0; i < list.size(); i++) {
				if(time-list.get(i)>3600000){//3600000
					list.remove(i);
				}
			}
		}
		list.add(time);
		manage.put(ip,list);
		if(list.size()>25){//一小时内访问网站内任何.htm请求超过25次...则返回false;每小时只允许某一iP访问25次.htm请求
			return false;
		}
		Spoor sp=new Spoor();
		sp.setIp(ip);
		sp.setUrl(request.getRequestURI());
		sp.setSpoortime(new Date());
		if(lns.savespoor(sp)){
			return true;
		}
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.info("CommonInterceptor.class:pageline:55:"+new Date());
		return false;
	}
}























