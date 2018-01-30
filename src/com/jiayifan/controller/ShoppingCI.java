package com.jiayifan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiayifan.service.BookService;
import com.jiayifan.service.MyCar;

public class ShoppingCI extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset = utf-8");
		response.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		MyCar mc = (MyCar) request.getSession().getAttribute("mycar");
		if("del".equals(type)) {
			//说明用户要删除商品
			int id = Integer.parseInt(request.getParameter("id"));
			mc.delBook(id);
			request.setAttribute("booklist", mc.showMyCar());
			request.setAttribute("totalprice", mc.getTotalPrice());
			request.getRequestDispatcher("/WEB-INF/showMyCar.jsp").forward(request, response);
		}else if("add".equals(type)) {
			int id = Integer.parseInt(request.getParameter("id"));
			mc.addBook(id, new BookService().getBookById(id));
			request.setAttribute("booklist", mc.showMyCar());
			request.setAttribute("totalprice", mc.getTotalPrice());
			request.getRequestDispatcher("/WEB-INF/showMyCar.jsp").forward(request, response);
		}else if("update".equals(type)) {
			String[] ids = request.getParameterValues("ids");
			String[] booknums = request.getParameterValues("booknum");
			for(int i=0;i<ids.length;i++) {
				mc.update(ids[i], booknums[i]);
			}
			request.setAttribute("booklist", mc.showMyCar());
			request.setAttribute("totalprice", mc.getTotalPrice());
			request.getRequestDispatcher("/WEB-INF/showMyCar.jsp").forward(request, response);
		}else if("see".equals(type)) {
			request.setAttribute("booklist", mc.showMyCar());
			request.setAttribute("totalprice", mc.getTotalPrice());
			request.getRequestDispatcher("/WEB-INF/showMyCar.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
