package com.jiayifan.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import  java.sql.*;
import java.util.*;

import javax.management.RuntimeErrorException;
import javax.swing.Spring;
public class SqlHelper {
	//定义需要的变量
	//细节1、如果访问数据库很频繁，我们的Connection就不应该为静态的
	private static Connection ct = null;
	//在大多数情况下，我们使用PreparedStatement替代Statement防止sql注入
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private static CallableStatement cs= null;
	//连接数据库的参数
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://127.0.0.1:3306/jiayifan";
	private static String username= "root";
	private static String password = "15517884870q";
	//加载驱动，只需一次
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
	}
	//得到连接
	public static Connection getConnection() {
		try {
			ct = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}
	//统一的select
	public static ArrayList executeQuery(String sql, String[] parameters) {
		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			if(parameters != null) {
				for(int i=0;i<parameters.length;i++) {
					ps.setString(i+1, parameters[i]);
				}
			}
			rs = ps.executeQuery();
			ArrayList al = new ArrayList();
			ResultSetMetaData rsmd = rs.getMetaData();
			int column = rsmd.getColumnCount();//这里可以得到你所查询的数据的列数
			while(rs.next()) {
				Object[] ob = new Object[column];//对象数组表示一行数据
				for(int i=1;i<=column;i++) {
					ob[i-1] = rs.getObject(i);
				}
				al.add(ob);
			}
			return al;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			close(rs, ps, ct);
		}
	}
	//如果有多个update / delete / insert语句【需要考虑事务】
	public static void executeUpdate2(String[] sql, String[][] parameters) {
		try {
			//核心
			//1、获得连接
			ct = getConnection();
			//因为这时用户传入的可能是多个sql语句
			ct.setAutoCommit(false);
			//2、获得sql语句
			for(int i=0;i<sql.length;i++) {
				if(parameters[i] != null) {
					ps = ct.prepareStatement(sql[i]);
					for(int j=0;j<parameters[i].length;j++) {
						ps.setString(j+1, parameters[i][j]);
					}
					ps.executeUpdate();
				}
			}	
			ct.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(rs, ps, ct);
		}
	}
	//先写一个update / delete / insert
	//sql 格式： update 表名   / set 字段名 = ? where 字段 = ?
	//parameters应该是{"abc" , 23}
	public static void executeUpdate(String sql, String[] parameters) {
		//创建一个ps
		try {
			ct = getConnection();
			ps = ct.prepareStatement(sql);
			//给?赋值
			if(parameters != null) {
				for(int i=0;i<parameters.length;i++) {
					ps.setString(i + 1, parameters[i]);
				}
			}
			//执行
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();//开发阶段
			//抛出异常，抛出运行异常的好处：可以让调用者一个选择，可以处理，也可以放弃处理
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		} finally {
			//关闭资源
			close(rs, ps, ct);
		}
	}
	public static void close(ResultSet rs, Statement ps, Connection ct) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		rs = null;
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ps = null;
		if(ct != null) {
			try {
				ct.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ct = null;
	}
}
