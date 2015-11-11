package com.project.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户表
 * @author Join_Liu
 *
 */
@Entity
@Table(name = "f_users")
public class Users {
	@Id
	@GeneratedValue
	private int id;
	@Column(length = 50)
	private String nickName;//昵称
	@Column(length = 30)
	private String uname;//用户姓名
	@Column(length = 50)
	private String password;//密码
	private Date birthday;//生日
	private Date recordDate;//注册日期
	@Column(length = 100)
	private String personImg;//个人肖像
	@Column(length = 11)
	private String phone;//手机号码
	@Column(length = 50)
	private String email;//邮箱
	@Column(length =2)
	private String sex;//性别
	@Column(length =2)
	private String status;//超凡状态
	@Column(length = 150)
	private String address;//常驻地址
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPersonImg() {
		return personImg;
	}
	public void setPersonImg(String personImg) {
		this.personImg = personImg;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	
}













































