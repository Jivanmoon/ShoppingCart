package com.jiayifan.service;

import java.util.ArrayList;

import com.jiayifan.domain.UserBean;
import com.jiayifan.util.SqlHelper;

//专门处理业务逻辑
//处理和users表对应的业务逻辑
public class UserService {
	//验证用户是否合法的方法
	public boolean checkUsers(UserBean user) {
		//到数据库去验证
		String sql = "select * from user where id = ? and pwd = ?;";
		String[] parameters = {user.getId() + "",user.getPwd()};
		ArrayList al = SqlHelper.executeQuery(sql, parameters);
		if(al.size() == 0) {
			return false;
		}
		else {
			Object[] obj = (Object[]) al.get(0);
			//把对象数组封装到UserBean对象
			user.setName((String)obj[1]);
			user.setEmail((String)obj[3]);
			user.setTel((String)obj[4]);
			user.setGrade((Integer)obj[5]);
			return true;
		}
	}
}
