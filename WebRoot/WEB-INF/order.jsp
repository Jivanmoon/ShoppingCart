<%@ page language="java" import="java.util.*,com.jiayifan.domain.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%UserBean user =(UserBean)session.getAttribute("loginuser");%>
	<h1>我的订单</h1>
	<table style='border-collapse' border='1'>
		<tr><td colspan='2'>用户信息</td></tr>
		<tr><td>用户名</td><td><%=user.getName() %></td></tr>
		<tr><td>电子邮件</td><td><%=user.getEmail() %></td></tr>
		<tr><td>用户级别</td><td><%=user.getGrade() %></td></tr>
	</table>
	<table border="1">
	   	<tr><td>bookid</td><td>书名</td><td>价格</td><td>出版社</td><td>数量</td></tr>
	   	<%
	   		//从request中取出要显示的书
	   		ArrayList al = (ArrayList)request.getAttribute("orderinfo");
	   		for(int i=0;i<al.size();i++) {
	   			BookBean book =(BookBean)al.get(i);	
	   	%>
	   	<tr>
	   	<td><%=book.getId() %></td>	
	   	<td><%=book.getName() %></td>
	   	<td><%=book.getPrice() %>元</td>
	   	<td><%=book.getPublishHouse() %></td>
    	<td><%=book.getBuynum() %>本</td>
  	   	</tr>
	   	<%
	   		}
	   	%>
	   	<tr><td colspan="5">购物车总价格：<%=request.getAttribute("total") %>元</td></tr>
	</table>
	<a href='/MyShopping/SubmitOrderCI'>确认提交</a>
</body>
</html>