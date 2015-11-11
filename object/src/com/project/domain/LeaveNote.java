package com.project.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 留言表
 * @author Join_Liu
 *
 */
@Entity
@Table(name = "f_leavenote")
public class LeaveNote {
	@Id
	@GeneratedValue
	private int id;
	private int userid;//用户id(如果没登录就null)
	@Column(length =1500)
	private String message;//发表内容
	private Date issuetime;//发表时间
	@Column(length =50)
	private String userip;//用户ip
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getIssuetime() {
		return issuetime;
	}
	public void setIssuetime(Date issuetime) {
		this.issuetime = issuetime;
	}
	public String getUserip() {
		return userip;
	}
	public void setUserip(String userip) {
		this.userip = userip;
	}
	
}
