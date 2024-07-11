package com.gn.board.dao;

import static com.gn.common.sql.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gn.board.vo.Board;

public class BoardDao {
	public int createBoard(Board b, Connection conn) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			String sql = "INSERT INTO `board` (board_title,board_content,board_writer,ori_thumbnail,new_thumbnail) VALUES (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql); // 준비
			pstmt.setString(1,b.getBoard_title());
			pstmt.setString(2,b.getBoard_content());
			pstmt.setInt(3,b.getBoard_writer());
			pstmt.setString(4,b.getOri_thumbnail());
			pstmt.setString(5,b.getNew_thumbnail());
			
			result = pstmt.executeUpdate();
			
					
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	// 페이징
	
	public int selectBoardCount(Board option,Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 검색 조건이 없는 경우
			String sql = "SELECT COUNT(*) AS cnt FROM board";
			if(option.getBoard_title() != null) {
				sql += " WHERE board_title LIKE CONCAT('%','"+option.getBoard_title()+"','%')";
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("cnt");
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	
	
	
	// 검색
	
	public List<Board> selectBoardList(Board option,Connection conn){
		List<Board> list = new ArrayList<Board>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 검색 조건
			// X : SELECT * FROM board
			// O : SELECT * FROM board WHERE board_title LIKE CONCAT('%',board_title,'%')
			String sql = "SELECT * FROM board";
			if(option.getBoard_title() != null) {
				sql += " WHERE board_title LIKE CONCAT('%','"+option.getBoard_title()+"','%')";
				
			}
			// LIMIT 앞 뒤 띄어쓰기 중요 -> WHERE절과 붙기 때문
			sql += " LIMIT "+option.getLimitPageNo()+", "+option.getNumPerPage();
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board resultVo = new Board(rs.getInt("board_no"),
						rs.getString("board_title"),
						rs.getString("board_content"),
						rs.getInt("board_writer"),
						rs.getTimestamp("reg_date").toLocalDateTime(),
						rs.getTimestamp("mod_date").toLocalDateTime(),
						rs.getString("ori_thumbnail"),
						rs.getString("new_thumbnail"));
				list.add(resultVo);
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
}
