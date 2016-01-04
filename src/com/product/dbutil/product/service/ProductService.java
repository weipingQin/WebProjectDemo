package com.product.dbutil.product.service;

import java.util.List;
import java.util.Map;

public interface ProductService {

	public boolean addProduct(List<Object> params);

	public boolean delProduct(String[] ids);
	// 提取所有产品的信息
	public List<Map<String, Object>> listProduct(String proname,int start,int end);
	
	public int getItemCount();
	
	public Map<String,Object> viewProduct(String proid);
}
