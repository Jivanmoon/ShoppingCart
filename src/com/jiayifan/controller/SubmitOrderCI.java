package com.jiayifan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiayifan.domain.UserBean;
import com.jiayifan.service.MyCar;
import com.jiayifan.service.OrderService;

/**
 * Servlet implementation class SubmitOrder
 */
public class SubmitOrderCI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitOrderCI() {
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
		try {
			MyCar mycar = (MyCar) request.getSession().getAttribute("mycar");
			UserBean user = (UserBean) request.getSession().getAttribute("loginuser");
			OrderService os = new OrderService();
			os.submit(mycar, user);
			request.getRequestDispatcher("/WEB-INF/OrderOk.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			request.getRequestDispatcher("/WEB-INF/errorInfo.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
