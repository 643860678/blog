package com.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.LeaveNoteDao;
import com.project.domain.LeaveNote;
import com.project.domain.Spoor;


@Service
public class LeaveNoteService {
	
	@Autowired
	LeaveNoteDao leaveNoteDao;
	
	/**
	 * 保存快速留言
	 * @param sql
	 * @return
	 */
	public int saveSpeak(LeaveNote leaveNote){
		return leaveNoteDao.saveSpeak(leaveNote);
	}
	
	/**
	 * 刚进入页面时进行的查询
	 * @param leaveNote
	 * @return
	 */
	public List<Map<String,Object>> queryList(int current){
		return leaveNoteDao.queryList(current);
	}
	
	/**
	 * 查看leavenote表所有的条数
	 * @return
	 */
	public int[] querycount(int current){
		return leaveNoteDao.querycount(current);
	}
	/**
	 * 当用户进行任何.htm的访问时,记录下相应信息
	 * @param sp
	 * @return
	 */
	public boolean savespoor(Spoor sp){
		return leaveNoteDao.savespoor(sp);
	}
}









































