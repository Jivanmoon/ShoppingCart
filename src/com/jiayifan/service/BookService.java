package com.jiayifan.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jiayifan.domain.BookBean;
import com.jiayifan.util.SqlHelper;

//处理与book表相关的业务
public class BookService {
	//得到所有的书籍信息
	public ArrayList<BookBean> getAllBook() {
		String sql = "select * from book;";
		ArrayList al = SqlHelper.executeQuery(sql, null);
		ArrayList<BookBean> newal = new ArrayList<BookBean>();
		for(int i=0;i<al.size();i++) {
			Object[] obj = (Object[]) al.get(i);
			BookBean bb = new BookBean();
			bb.setId(Integer.parseInt(obj[0].toString()));
			bb.setName(obj[1].toString());
			bb.setAutor(obj[2].toString());
			bb.setPublishHouse((String)obj[3]);
			bb.setPrice(Float.parseFloat(obj[4].toString()));
			bb.setNum(Integer.parseInt(obj[5].toString()));
			newal.add(bb);
		}
		return newal;
	}
	public int getPageCount(int pageSize) {
		int rowCount = 0;
		String sql = "select * from book;";
		ArrayList al = SqlHelper.executeQuery(sql, null);
		rowCount = al.size();
		return (rowCount - 1) / pageSize + 1;
	}
	//按照分页显示用户
	public ArrayList<BookBean> getBooksByPage(int pageNow, int pageSize) {
		//查询语句
		String sql = "select * from book limit " + (pageNow - 1) * pageSize + "," + pageSize;
		ArrayList al = SqlHelper.executeQuery(sql, null);
		//二次封装
		ArrayList<BookBean> newal = new ArrayList<BookBean>();
		for(int i=0;i<al.size();i++) {
			Object[] obj = (Object[]) al.get(i);
			BookBean bb = new BookBean();
			bb.setId(Integer.parseInt(obj[0].toString()));
			bb.setName(obj[1].toString());
			bb.setAutor(obj[2].toString());
			bb.setPublishHouse((String)obj[3]);
			bb.setPrice(Float.parseFloat(obj[4].toString()));
			bb.setNum(Integer.parseInt(obj[5].toString()));
			newal.add(bb);
		}
		return newal;
	}
	public BookBean getBookById(int id) {
		String sql = "select * from book where id = ?;";
		String[] paras = {id+""};
		BookBean bb = new BookBean();
		ArrayList al = SqlHelper.executeQuery(sql, paras);
		if(al.size() == 1) {
			Object[] obj = (Object[]) al.get(0);
			bb.setId(Integer.parseInt(obj[0].toString()));
			bb.setName(obj[1].toString());
			bb.setAutor(obj[2].toString());
			bb.setPublishHouse((String)obj[3]);
			bb.setPrice(Float.parseFloat(obj[4].toString()));
			bb.setNum(Integer.parseInt(obj[5].toString()));
		}
		return bb;
	}
}
