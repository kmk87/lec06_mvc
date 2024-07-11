package com.gn.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static com.gn.common.sql.JDBCTemplate.close;

import com.gn.user.vo.User;

public class UserDao {
	
	public int createUser(User u, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "INSERT INTO `user`(`user_id`,`user_pw`,`user_name`) VALUES(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, u.getUser_id());
			pstmt.setString(2, u.getUser_pw());
			pstmt.setString(3, u.getUser_name());
			
			
			
			result=pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	
	//public int loginUser
}
