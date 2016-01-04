package com.product.dbutil.login.dao;

import java.util.List;
import java.util.Map;

import com.product.dbutil.jdbc.JdbcUtils;
import com.product.dbutil.login.service.LoginService;

public class LoginDao implements LoginService {

	private JdbcUtils jdbcUtils = null;

	public LoginDao() {
		// TODO Auto-generated constructor stub
		jdbcUtils = new JdbcUtils();
	}

	public boolean login(List<Object> params) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "select * from userinfo where username=? and pswd=?";
		try {
			jdbcUtils.getConnection();
			Map<String, Object> map = jdbcUtils.findSimpleResult(sql, params);
			flag = !map.isEmpty() ? true : false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

}
