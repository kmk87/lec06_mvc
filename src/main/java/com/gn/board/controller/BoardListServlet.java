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
		
		// 페이징
		// 마지막에 해줌...
		String nowPage = request.getParameter("nowPage");
		if(nowPage != null) {
			option.setNowPage(Integer.parseInt(nowPage));
		}
		// 전체 목록 개수 조회 -> 페이징바 구성
		option.setTotalData(new BoardService().selectBoardCount(option)); // 보드의 페이징을 상속받았기 때문에 가능
		
	
		List<Board> list = new BoardService().selectBoardList(option);
		
		// 페이징
		request.setAttribute("paging",option);
		
		
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
