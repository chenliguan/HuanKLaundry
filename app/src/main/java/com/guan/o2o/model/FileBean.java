package com.guan.o2o.model;

import com.guan.o2o.utils.GsonUtil;

import java.util.List;

public class FileBean {
	
	private int id;
	private String path;
	private String name;
	
	public FileBean() {
		super();
	}

	public FileBean(int id, String path, String name) {
		super();
		this.id = id;
		this.path = path;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gson解析Json数据
	 */
	public static List<FileBean> praseJson(String reponse) {

		GsonUtil gsonUtil = new GsonUtil();
		List<FileBean> list = (List<FileBean>)gsonUtil.gsonToList(reponse, FileBean.class);
		return list;
	}
}
