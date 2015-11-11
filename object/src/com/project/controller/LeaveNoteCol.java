package com.project.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.domain.LeaveNote;
import com.project.model.PageParam;
import com.project.service.LeaveNoteService;
import com.wash.utils.GetIp;
import com.wash.utils.ResponseWrite;



@Controller
public class LeaveNoteCol {
	
	@Autowired
	LeaveNoteService leaveNoteService;
	
	/**
	 * 当用于发表留言的时候访问该方法.执行保存数据的操作
	 * @param leaveNote
	 */
	@RequestMapping(value="savespeak.htm")
	public void saveSpeak(LeaveNote leaveNote,HttpServletRequest request,HttpServletResponse response){
		leaveNote.setUserip(new GetIp().getIpAddr(request));
		if(leaveNoteService.saveSpeak(leaveNote)>0){
			//ResponseWrite.write(response,"保存成功!");
		}else{
			ResponseWrite.write(response,"保存失败!");
		}
	}
	
	/**
	 * 初始化的页面.当访问时
	 * @param model
	 * @param pageparam
	 * @return
	 */
	@RequestMapping(value="index.htm")
	public String queryleavenote(Model model,PageParam pageparam){
		
		if(pageparam.getCurrent()==0){
			pageparam=new PageParam();
			pageparam.setCurrent(1);
		}
		List<Map<String,Object>> list=leaveNoteService.queryList(pageparam.getCurrent());
		model.addAttribute("list",list);
		
		int[]arr=leaveNoteService.querycount(pageparam.getCurrent());
		pageparam.setRownum(arr[0]);
		pageparam.setCount(arr[1]);
		model.addAttribute("pageparam",pageparam);
		return "index";
	}
}

//






























