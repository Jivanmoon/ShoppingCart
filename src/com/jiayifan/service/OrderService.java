package com.jiayifan.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;

import com.jiayifan.domain.BookBean;
import com.jiayifan.domain.UserBean;
import com.jiayifan.util.SqlHelper;
import com.mysql.jdbc.PreparedStatement;

//处理与订单相关的业务逻辑
public class OrderService {
	private Connection ct = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	public void submit(MyCar mycar, UserBean user) {
		String sql = "insert into orders(user_id,totalPrice,orderDate) values(?,?,now());";
		//SqlHelper功能有限，所以这里自己处理
		try {
			ct = SqlHelper.getConnection();
			//为了保证我们的订单号是稳定的，所以将七事务隔离级别升级（可串行）
			ct.setAutoCommit(false);
			ct.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			ps = (PreparedStatement) ct.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.setFloat(2, mycar.getTotalPrice());
			ps.executeUpdate();
			sql = "select MAX(id) from orders;";
			ps = (PreparedStatement) ct.prepareStatement(sql);
			rs = ps.executeQuery();
			int orderId = 0;
			if(rs.next()) {
				//取出刚刚生成的id
				orderId = rs.getInt(1);
			}
			//把订单细节表生成【批量提交】
			ArrayList al = mycar.showMyCar();
			for(int i=0;i<al.size();i++) {
				BookBean book = (BookBean) al.get(i);
				sql = "insert into ordersItem(orders_id,bookid,booknum) values(?,?,?);";
				ps = (PreparedStatement) ct.prepareCall(sql);
				ps.setInt(1, orderId);
				ps.setInt(2, book.getId());
				ps.setFloat(3, book.getBuynum());
				ps.executeUpdate();
			}
			ct.commit();
			
		}catch(Exception e) {
			try {
				ct.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		}finally {
			SqlHelper.close(rs, ps, ct);
		}
	}
}
