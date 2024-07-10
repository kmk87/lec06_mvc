package com.gn.board.service;

import com.gn.board.dao.BoardDao;
import com.gn.board.vo.Board;

import static com.gn.common.sql.JDBCTemplate.getConnection;
import static com.gn.common.sql.JDBCTemplate.close;

import java.sql.Connection;

public class BoardService {
	public int createBoard(Board b) {
		Connection conn = getConnection();
		int result = new BoardDao().createBoard(b,conn);
		close(conn);
		return result;
	}
}
