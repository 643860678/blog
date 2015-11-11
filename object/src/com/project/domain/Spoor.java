package com.project.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author xiaokai
 * @description 用户信息表
 * 
 */
@Entity
@Table(name = "f_spoor")
public class Spoor {

	@Id
	@GeneratedValue
	private int id;
	@Column(length = 50)
	private String url;//访问的路径
	@Column(length = 50)
	private String ip;//访问者ip
	private Date spoortime;//访问的时间
	private int uid;//访问者.未登陆就为null
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getSpoortime() {
		return spoortime;
	}
	public void setSpoortime(Date spoortime) {
		this.spoortime = spoortime;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
}








































