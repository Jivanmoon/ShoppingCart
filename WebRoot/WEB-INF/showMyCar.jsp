<%@ page language="java" import="java.util.*,com.jiayifan.domain.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title></title>
  </head>
  
  <body>
    <h2>我的购物车</h2>
	   <form action="/MyShopping/ShoppingCI?type=update" method="post">
	    <table border="1">
	    	<tr><td>bookid</td><td>书名</td><td>价格</td><td>出版社</td><td>数量</td><td>删除</td></tr>
	    	<%
	    		//从request中取出要显示的书
	    		ArrayList al = (ArrayList)request.getAttribute("booklist");
	    		for(int i=0;i<al.size();i++) {
	    			BookBean book =(BookBean)al.get(i);	
	    	%>
	    		<tr>
		    	<td><%=book.getId() %><input type="hidden" name="ids" value="<%=book.getId() %>" /></td>	
		    	<td><%=book.getName() %></td>
		    	<td><%=book.getPrice() %></td>
		    	<td><%=book.getPublishHouse() %></td>
		    	<td><input type="text" name="booknum" value="<%=book.getBuynum()%>"/>本</td>
		    	<td><a href="/MyShopping/ShoppingCI?type=del&id=<%=book.getId()%>">删除</a></td>
		    	</tr>
	    	<%		
	    		}
	    	%>
	    	<tr><td colspan="6"><input type="submit" value="update" /></td></tr>
	    	<tr><td colspan="6">购物车总价格：${totalprice}元</td></tr>
	    	<tr><td colspan="6"><a href="/MyShopping/GoHallCI?type=logined">返回购物大厅</a></td></tr>
	    </table>
	   </form>
	   <a href="/MyShopping/GoMyOrderCI">提交订单</a>
  </body>
</html>