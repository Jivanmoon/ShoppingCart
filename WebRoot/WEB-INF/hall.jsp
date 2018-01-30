<%@page import="com.jiayifan.service.BookService"%>
<%@ page language="java" import="java.util.*,com.jiayifan.domain.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title></title>
  </head>
  
  <body>
    <h1>欢迎进入购物大厅</h1>
    <a href = '/MyShopping/GoHallCI?type=exit'>安全退出</a>
    <table border='1'>
    	<tr><td>书名</td><td>价格</td><td>出版社</td><td>点击购买</td></tr>
    	<%
	    	int pageNow = 1;//当前页
			int pageCount = 0;
			String spageNow = request.getParameter("pageNow");
			if(spageNow != null) {
				pageNow = Integer.parseInt(spageNow);
			}
			int pageSize = 3;//指定每页多少条记录 
			BookService bookService = new BookService();
			pageCount = bookService.getPageCount(pageSize);
			ArrayList<BookBean> all = bookService.getBooksByPage(pageNow, pageSize);
			for(BookBean book : all) {
    	%>
    		<tr>
	    		<td><%=book.getName()%></td>
	    		<td><%=book.getPrice()%></td>
	    		<td><%=book.getPublishHouse()%></td>
	    		<td><a href="/MyShopping/ShoppingCI?type=add&id=<%=book.getId()%>">购买</a></td>
    		</tr>
    	<%
    		}
    	%>
    	<tr><td colspan="4"><a href="/MyShopping/ShoppingCI?type=see">查看购物车</a></td></tr>
    </table>
	    <%
	  		//显示上一页
	  		if(pageNow - 1 >= 1) {
	  	%>
	  			<a href = '/MyShopping/GoHallCI?type=logined&pageNow=<%=pageNow - 1 %>'>上一页</a>
	  	<%
	  		}
		 	 //显示分页
		  	for(int i=1;i<=pageCount;i++) {
		 %>
				<a href = '/MyShopping/GoHallCI?type=logined&pageNow=<%=i %>'><<%=i %>></a>
		<%
		  	}
		%>
	  	<%
	  		//显示下一页
	  		if(pageNow + 1 <= pageCount)
	  		{
	  	%>
	  			<a href = '/MyShopping/GoHallCI?type=logined&pageNow=<%=pageNow + 1 %>'>下一页</a>
  		<%
	  		}
  		%>
  </body>
</html>