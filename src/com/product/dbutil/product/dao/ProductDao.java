package com.product.dbutil.product.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.product.dbutil.jdbc.JdbcUtils;
import com.product.dbutil.product.service.ProductService;

public class ProductDao implements ProductService {

	private JdbcUtils jdbcUtils;

	public ProductDao() {
		// TODO Auto-generated constructor stub
		jdbcUtils = new JdbcUtils();
	}

	public boolean addProduct(List<Object> params) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			String sql = "insert into product(proid,proname,proprice,proaddress,proimage) values(?,?,?,?,?)";
			jdbcUtils.getConnection();
			flag = jdbcUtils.updateByPreparedStatement(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	/*
	 * (non-Javadoc) 提取产品的信息
	 * 
	 * @see com.product.dbutil.product.service.ProductService#listProduct()
	 */
	public List<Map<String, Object>> listProduct(String proname, int start,
			int end) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String sql = "select * from product where (1=1) ";
		// limit ?,?
		StringBuffer buffer = new StringBuffer(sql);
		List<Object> params = new ArrayList<Object>();
		if (proname != null) {
			buffer.append(" and proname like ? ");
			params.add("%" + proname + "%");
		}
		buffer.append("limit ?,? ");
		params.add(start);
		params.add(end);
		try {
			jdbcUtils.getConnection();
			list = jdbcUtils.findMoreResult(buffer.toString(), params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return list;
	}

	public int getItemCount() {
		int result = 0;
		Map<String, Object> map = null;
		String sql = " select count(*) mycount from product ";
		try {
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, null);
			result = Integer.parseInt(map.get("mycount").toString());
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		// TODO Auto-generated method stub
		return result;
	}

	public boolean delProduct(String[] ids) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			jdbcUtils.getConnection();
			String[] sql = new String[ids.length];
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
					sql[i] = "delete from product where proid='" + ids[i] + "'";
				}
			}
			flag = jdbcUtils.deleteByBatch(sql);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return flag;
	}

	public Map<String, Object> viewProduct(String proid) {
		// TODO Auto-generated method stub
		Map<String, Object> map = null;
		try {
			String sql = "select * from product where proid = ? ";
			List<Object> params = new ArrayList<Object>();
			params.add(proid);
			jdbcUtils.getConnection();
			map = jdbcUtils.findSimpleResult(sql, params);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			jdbcUtils.releaseConn();
		}
		return map;
	}

}
