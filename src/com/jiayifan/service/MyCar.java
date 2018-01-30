package com.jiayifan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.jiayifan.domain.BookBean;

//这个表示我的购物车
public class MyCar {
	HashMap<Integer, BookBean> hm = new HashMap<Integer, BookBean>();
	//添加书
	public void addBook(int id, BookBean book) {
		if(hm.containsKey(id)) {
			BookBean tbook = hm.get(id);
			int num = tbook.getBuynum();
			tbook.setBuynum(num + 1);
		}
		else
			hm.put(id, book);
	}
	public float getTotalPrice() {
		float total = 0;
		ArrayList al = new ArrayList();
		Iterator iterator = (Iterator) hm.keySet().iterator();
		while(iterator.hasNext()) {
			Integer id = (Integer) iterator.next();
			BookBean book = hm.get(id);
			total += book.getPrice() * book.getBuynum();
		}
		return total;
	}
	//删除书
	public void delBook(int id) {
		hm.remove(id);
	}
	//修改书的数量
	public void update(String id, String nums) {
		//取出id对用的bookBean
		BookBean book = hm.get(Integer.valueOf(id));
		book.setBuynum(Integer.parseInt(nums));
	}
	//清空购物车
	public void clean() {
		hm.clear();
	}
	//显示该购物车中的商品信息
	public ArrayList showMyCar() {
		ArrayList al = new ArrayList();
		Iterator iterator = (Iterator) hm.keySet().iterator();
		while(iterator.hasNext()) {
			Integer id = (Integer) iterator.next();
			BookBean book = hm.get(id);
			al.add(book);
		}
		return al;
	}
}
