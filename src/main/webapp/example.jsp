<%@page import="com.servlet.MemberVO"%>
<%@page import="com.servlet.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// MemberDAO 생성
	MemberDAO dao = MemberDAO.getInstance();
	MemberVO vo = dao.getUserInfo("abc");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름:<%=vo.getName() %><br>
	지역:<%=vo.getRegion() %><br>
	성별:<%=vo.getGender() %>
</body>
</html>