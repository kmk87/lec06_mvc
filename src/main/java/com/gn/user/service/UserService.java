package com.gn.user.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gn.user.dao.UserDao;
import com.gn.user.vo.User;
import static com.gn.common.sql.JDBCTemplate.getConnection;
import static com.gn.common.sql.JDBCTemplate.close;

public class UserService {
	public int createUser(User u) {
		Connection conn = getConnection();
		int result = new UserDao().createUser(u,conn);
		close(conn);
		return result;
		

	}
	
//	public int loginUser(String id, String pw) {
//		Connection conn = getConnection();
//		int result = new UserDao().loginUser(id,pw,conn);
//		close(conn);
//		return result;
//	}
	
}
