package com.product.dbutil.product.util;

public class DividePage {

	private int pageSize;// ��ʾ��ʾ������
	private int recordCount;// ��ʾ��¼��������
	private int currentPage;// ��ʾ��ǰҳ

	public DividePage(int pageSize, int recordCount, int currentPage) {
		// TODO Auto-generated constructor stub
		this.pageSize = pageSize;
		this.recordCount = recordCount;
		setCurrentPage(currentPage);
	}

	public DividePage(int pageSize, int recordCount) {
		// TODO Auto-generated constructor stub
		this(pageSize, recordCount, 1);
	}

	// �����ҳ��
	public int getPageCount() {
		int size = recordCount / pageSize;
		int mod = recordCount % pageSize;
		if (mod != 0) {
			size++;
		}
		return recordCount == 0 ? 1 : size;
	}

	public int getFromIndex() {
		return (currentPage - 1) * pageSize;
	}

	public int getToIndex() {
		return pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		int validPage = currentPage <= 0 ? 1 : currentPage;
		validPage = validPage > getPageCount() ? getPageCount() : validPage;
		this.currentPage = validPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
}
