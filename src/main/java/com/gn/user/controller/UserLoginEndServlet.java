package com.gn.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gn.user.service.UserService;
import com.gn.user.vo.User;


@WebServlet(name="userLoginEnd",urlPatterns = "/user/loginEnd")
public class UserLoginEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UserLoginEndServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 -> 비밀번호 확인(사용자 입력 == 회원가입)
		// 회원가입 비밀번호 암호화 == 사용자 입력 암호화
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		//System.out.println("비밀번호 확인 : "+pw);
		
		
		//User u = new UserService().loginUser(id,pw);
		
		// 1. User u = new UserService().loginUser(id,pw); 코드 주석처리
		// 2. User 객체의 매개변수 생성자에 데이터베이스 실제 값을 넣어서 User 구성
		// 예시) new User(1,"admin","암호화된비밀번호","관리자");
		// 3. 매개변수 생성자로 만든 객체 주석처리
		// 4. User u = null;로 실패 상황도 테스트해보기
		
		User u = new User(3,"user02","pass02","asd");
		 //User u = null;
		
		if(u != null) {
			HttpSession session = request.getSession(true); //세선 처음 만드는거니까 트루
			if(session.isNew() || session.getAttribute("user") == null) {
				session.setAttribute("user", u);
				session.setMaxInactiveInterval(60*30);
			}
			// 메인페이지 요청
			response.sendRedirect("/");
			
		} else {
			// 로그인 실패 페이지로 이동
			RequestDispatcher view = request.getRequestDispatcher("/views/user/login_fail.jsp");
			view.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
