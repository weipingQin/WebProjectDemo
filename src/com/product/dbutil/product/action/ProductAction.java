package com.product.dbutil.product.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.product.dbutil.product.dao.ProductDao;
import com.product.dbutil.product.service.ProductService;
import com.product.dbutil.product.util.DividePage;
import com.product.dbutil.product.util.UUIDTools;

public class ProductAction extends HttpServlet {

	private ProductService service;

	/**
	 * Constructor of the object.
	 */
	public ProductAction() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String action_flag = request.getParameter("action_flag");
		if (action_flag.equals("add")) {
			addProduct(request, response);
		} else if (action_flag.equals("list")) {
			listProduct(request, response);
		} else if (action_flag.equals("del")) {
			delProduct(request, response);
		}else if(action_flag.equals("view")){
			viewProduct(request, response);
		}

		out.flush();
		out.close();
	}

	private void viewProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String proid = request.getParameter("proid");
		Map<String,Object> map = service.viewProduct(proid);
		request.setAttribute("map", map);
		request.getRequestDispatcher("/product/2_1_5xs.jsp").forward(request, response);
	}

	private void delProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getContextPath();
		// 获得选中的复选框的值
		String[] ids = request.getParameterValues("ids");
		boolean flag = service.delProduct(ids);
		if (flag) {
			response.sendRedirect(path
					+ "/servlet/ProductAction?action_flag=list");
		}
	}

	private void listProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// String path = request.getContextPath();
		// 接收用户的查询名字
		String proname = request.getParameter("proname");
		int recordCount = service.getItemCount();// 获得记录的总条数
		int currentPage = 1;// 当前页是第一页
		String pageNum = request.getParameter("pageNum");
		if (pageNum != null) {
			currentPage = Integer.parseInt(pageNum);
		}
		DividePage pUtil = new DividePage(5, recordCount, currentPage);
		int start = pUtil.getFromIndex();
		int end = pUtil.getToIndex();
		// 已经进行分页之后的数据集合
		List<Map<String, Object>> list = service.listProduct(proname, start,
				end);
		request.setAttribute("pUtil", pUtil);
		request.setAttribute("listproduct", list);
		request.setAttribute("proname", proname);
		request.getRequestDispatcher("/product/2_1_5.jsp").forward(request,
				response);
	}

	private void addProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 表单含有文件要提交
		String path = request.getContextPath();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 构建一个文件上传类
		ServletFileUpload servletFileUpload = new ServletFileUpload(
				diskFileItemFactory);
		servletFileUpload.setFileSizeMax(3 * 1024 * 1024);
		servletFileUpload.setSizeMax(6 * 1024 * 1024);// 上传文件总大小
		List<FileItem> list = null;
		List<Object> params = new ArrayList<Object>();
		params.add(UUIDTools.getUUID());
		try {
			// 解析request的请求
			list = servletFileUpload.parseRequest(request);
			// 取出所有表单的值:判断非文本字段和文本字段
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					if (fileItem.getFieldName().equals("proname")) {
						params.add(fileItem.getString("utf-8"));
					}
					if (fileItem.getFieldName().equals("proprice")) {
						params.add(fileItem.getString("utf-8"));
					}
					if (fileItem.getFieldName().equals("proaddress")) {
						params.add(fileItem.getString("utf-8"));
					}
				} else {
					try {
						String image = fileItem.getName();
						params.add(image);
						String upload_path = request.getRealPath("/upload");
						System.out.println("--->>" + upload_path);
						//
						File real_path = new File(upload_path + "/" + image);
						fileItem.write(real_path);
						boolean flag = service.addProduct(params);
						if (flag) {
							response
									.sendRedirect(path
											+ "/servlet/ProductAction?action_flag=list");
						}
						// 把数据插入到数据库中
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		service = new ProductDao();
	}

}
