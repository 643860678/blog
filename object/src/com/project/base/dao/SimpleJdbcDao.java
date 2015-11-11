package com.project.base.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 连接正式服务器获取最新站点信息
 * 
 * @author xiaokai
 * 
 */
@Repository
public class SimpleJdbcDao {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> query(Class<T> clazz, String sql, Object... args) {
		RowMapper rowMapper = ParameterizedBeanPropertyRowMapper
				.newInstance(clazz);
		return (List<T>) this.getJdbcTemplate().query(sql, args, rowMapper);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> query(String sql) {
		return this.getJdbcTemplate().queryForList(sql);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> query(String sql,Object []obj) {
		return this.getJdbcTemplate().queryForList(sql,obj);
	}
	
	@SuppressWarnings("unchecked")
	public int querycount(String sql,Object []obj) {
		return this.getJdbcTemplate().queryForInt(sql,obj);
	}
	@SuppressWarnings("unchecked")
	public int querycount(String sql){
		return jdbcTemplate.queryForInt(sql);
	}
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return namedParameterJdbcTemplate;
	}
	
	// 以下是事务..
	@Transactional
	public boolean TranSave(String[] sqls) {
		// String
		// []sqls={"insert into Test(name) values('张三66')","insert into Test(name) values('张三66')","insert into Test(name) values('张三99')"};
		try {
			getJdbcTemplate().batchUpdate(sqls);
		} catch (Exception e) {
			return false;
		}
		return true;
		// getJdbcTemplate().execute("insert into Test(name) values('张三6')");
	}

	public int update(String sql,Object []param) {
		return getJdbcTemplate().update(sql,param);
	}

}
