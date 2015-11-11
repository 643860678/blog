package com.project.dao;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.base.dao.SimpleJdbcDao;
import com.project.domain.LeaveNote;
import com.project.domain.Spoor;
import com.wash.utils.DaoUtil;


@Repository
public class LeaveNoteDao{
	
	private int showpage=12;//每页展示的数量
	@Autowired
	SimpleJdbcDao simpleJdbcDao;
	/**
	 * 保存快速留言
	 * @param sql
	 * @return
	 */
	public int saveSpeak(LeaveNote leaveNote){
		leaveNote.setIssuetime(new Date());
		String sql="insert into f_leavenote(userid,message,userip,issuetime) values(?,?,?,?)";
		return simpleJdbcDao.update(sql,new Object[]{leaveNote.getUserid(),leaveNote.getMessage(),leaveNote.getUserip(),leaveNote.getIssuetime()});
	}
	
	/**
	 * 刚进入页面时进行的查询
	 * @param leaveNote
	 * @return
	 */
	public List<Map<String,Object>> queryList(int current){
		//String sql="select id,userid,message,issuetime,userip from leaveNote order by id desc LIMIT 0,12";
		String sql="select id,userid,message,issuetime,userip from f_leavenote order by id desc LIMIT "+(showpage*current-showpage)+","+showpage+"";
		return simpleJdbcDao.query(sql);
	}
	
	/**
	 * 查看leavenote表所有的条数
	 * @return
	 */
	public int[] querycount(int current){
		int []arr=new int[2];
		arr[0]=simpleJdbcDao.querycount("select count(1) from f_leavenote");//获得总条数
		if(arr[0]%showpage==0){//获得总页数
			arr[1]=arr[0]/showpage;
		}else{
			arr[1]=arr[0]/showpage+1;
		}
		return arr;
	}
	/**
	 * 当用户进行任何.htm的访问时,记录下相应信息
	 * @param sp
	 * @return
	 */
	public boolean savespoor(Spoor sp){
		String sql="insert into f_spoor(url,ip,spoortime,uid) values(?,?,?,?)";
		int num=simpleJdbcDao.update(sql,new Object[]{sp.getUrl(),sp.getIp(),sp.getSpoortime(),sp.getUid()});
		return DaoUtil.isBoolean(num);
	}
}





































