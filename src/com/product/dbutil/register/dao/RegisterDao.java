package com.product.dbutil.register.dao;

import java.util.List;

import com.product.dbutil.jdbc.JdbcUtils;
import com.product.dbutil.register.service.RegisterService;

public class RegisterDao implements RegisterService {

	private JdbcUtils utils = null;

	public RegisterDao() {
		utils = new JdbcUtils();
	}

	/*
	 * ����˶��û�ע���dao�ı�д
	 * 
	 * @see
	 * com.product.dbutil.register.service.RegisterService#registerUser(java
	 * .util.List)
	 */
	public boolean registerUser(List<Object> params) {
		// TODO Auto-generated method stub
		boolean flag = false;

		String sql = "insert into userinfo(username,pswd,realname) values(?,?,?)";
		try {
			// �Ȼ������
			utils.getConnection();
			flag = utils.updateByPreparedStatement(sql, params);
			System.out.println("-flag-->>" + flag);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			// �ر����ݿ������
			utils.releaseConn();
		}
		return flag;
	}

}
