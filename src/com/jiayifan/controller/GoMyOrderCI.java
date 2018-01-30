package com.jiayifan.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiayifan.service.MyCar;

/**
 * Servlet implementation class GoMyOrderCI
 */
public class GoMyOrderCI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoMyOrderCI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset = utf-8");
		response.setCharacterEncoding("utf-8");
		MyCar mc = (MyCar) request.getSession().getAttribute("mycar");
		ArrayList al = mc.showMyCar();
		float totalprice = mc.getTotalPrice();
		request.setAttribute("orderinfo", al);
		request.setAttribute("total", totalprice);
		request.getRequestDispatcher("/WEB-INF/order.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
