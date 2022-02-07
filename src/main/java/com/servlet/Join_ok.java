package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Join_ok")
public class Join_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Join_ok() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String region = request.getParameter("region");
		String gender = request.getParameter("gender");

		// DAO객체 생성
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO vo = new MemberVO(id, pw, name, region, gender);
		
		int result = dao.userInsert(vo); // 성공 1, 실패 0
		
		if(result == 1) { // 성공
			response.sendRedirect("success.jsp");
		} else { // 실패
			response.sendRedirect("fail.jsp");
		}
		
	}

}
