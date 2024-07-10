package com.gn.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gn.board.service.BoardService;
import com.gn.board.vo.Board;


@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardListServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("board_title");
		// option : 검색 조건
		Board option = new Board();
		option.setBoard_title(title);
		
		List<Board> list = new BoardService().selectBoardList(option);
		
		// /views/board/list.jsp
		// 2. css 추가
		// 3. 화면 이동 작성
		// 4. 화면 이동할 때 속성값을 전달 
		
		RequestDispatcher view = request.getRequestDispatcher("/views/board/list.jsp");
		request.setAttribute("resultList",list);
		view.forward(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
