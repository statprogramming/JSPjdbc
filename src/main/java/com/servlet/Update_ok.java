package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Update_ok")
public class Update_ok extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Update_ok() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
	
		/*
		1. 아이디는 세션에서 얻는다.
		2. 폼태그에서 넘어오는 파라미터값을 받는다.
		3. executeUpdate()문장으로 업데이트 작업을 수행하고 1을 반환하면 세션에 변경된 이름을 저장한 후에 update_result.jsp, 0을 반환하면 login.jsp로 리다이렉트
		*/
		
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("user_id");
		
		String pw = request.getParameter("pw"); // pw
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String region = request.getParameter("region");

		// DAO에 int update(... 필요 매개변수);
		// 3. executeUpdate()문장으로 업데이트 작업을 수행하고 
		// 1을 반환하면 세션에 변경된 이름을 저장한 후에 update_result.jsp, 
		// 0을 반환하면 login.jsp로 리다이렉트
		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.update(id, pw, name, gender, region);
		
		if(result == 1) { // 성공
			session.setAttribute("user_name", name); // 세션에 변경된 이름 저장
			response.sendRedirect("update_result.jsp");
		} else { // 실패
			response.sendRedirect("login.jsp");
		}				
		/*
		// 데이터베이스 연결 주소
		String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
		String uid = "jsp";
		String upw = "jsp";
				
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "UPDATE MEMBER SET PW = ?, NAME = ?, GENDER = ?, REGION = ? WHERE ID = ?";
		
		try {
			// 1. 드라이버 호출
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. conn
			conn = DriverManager.getConnection(url, uid, upw);
			// 3. pstmt
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, name);
			pstmt.setString(3, gender);
			pstmt.setString(4, region);
			pstmt.setString(5, id);
			// 실행
			int result = pstmt.executeUpdate();
			
			if(result == 1) { // 성공
				
				// 세션의 이름 변경
				session.setAttribute("user_name", name);
				response.sendRedirect("update_result.jsp");
				
			} else { // 실패
				response.sendRedirect("login.jsp"); // 로그인 화면
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (Exception e2) {
				
			}
		}
		*/
	}
}
