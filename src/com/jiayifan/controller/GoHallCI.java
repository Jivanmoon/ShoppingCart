package com.jiayifan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiayifan.domain.UserBean;
import com.jiayifan.service.BookService;
import com.jiayifan.service.MyCar;
import com.jiayifan.service.UserService;

public class GoHallCI extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset = utf-8");
		response.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		UserService us = new UserService();
		BookService bs = new BookService();
		//先判断用户是否已经登陆过，如果登录过，则直接跳入购物大厅
		if("login".equals(type)) {
			System.out.println("账号没登陆过");
			String id = request.getParameter("id");
			String pwd = request.getParameter("password");		
			UserBean user = new UserBean();
			user.setId(Integer.parseInt(id));
			user.setPwd(pwd);
			if(us.checkUsers(user)) {
				//登录成功，创建一个购物车
				request.getSession().setAttribute("loginuser", user);
				MyCar mc = new MyCar();
				request.getSession().setAttribute("mycar", mc);
				ArrayList al = bs.getAllBook();
				request.setAttribute("books", al);
				request.getRequestDispatcher("/WEB-INF/hall.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			}
		}else if("logined".equals(type)) {
			if(request.getSession().getAttribute("loginuser") != null) {
				System.out.println("账号登陆过");
				if(request.getSession().getAttribute("mycar") == null) {
					System.out.println("no mycar");
					MyCar mc = new MyCar();
					request.getSession().setAttribute("mycar", mc);
				}
				ArrayList al = bs.getAllBook();
				request.setAttribute("books", al);
				request.getRequestDispatcher("/WEB-INF/hall.jsp").forward(request, response);
			}
		}else if("exit".equals(type)) {
			request.getSession().invalidate();
			response.sendRedirect("/MyShopping");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
