<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title></title>
  </head>
  
  <body>
    <h1>登录界面</h1>
    <form action="/MyShopping/GoHallCI?type=login" method="post">
	    <table border="1">
	    	<tr><td>id</td><td><input type="text" name="id" /></td></tr>
	    	<tr><td>密码</td><td><input type="text" name="password" /></td></tr>
	    	<tr><td><input type="submit" value="登录" /></td><td><input type="reset" value="清空重输" /></td></tr>
	    </table>
    </form>
  </body>
</html>