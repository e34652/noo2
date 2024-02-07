<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String sql = "INSERT INTO score VALUES (?, ?, ?, ?, ?)";
	String[] score = {request.getParameter("stuNum"),request.getParameter("stuName"), request.getParameter("Kor"),
			request.getParameter("Eng"), request.getParameter("Math")};
	try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
		/*pstmt.setInt(1, Integer.parseInt(request.getParameter("stuNum")));
		pstmt.setString(2, request.getParameter("stuName"));
		pstmt.setInt(3, Integer.parseInt(request.getParameter("Kor")));
		pstmt.setInt(4, Integer.parseInt(request.getParameter("Eng")));
		pstmt.setInt(5, Integer.parseInt(request.getParameter("Math")));*/
			//배열 사용
			pstmt.setInt(1, Integer.parseInt(score[0]));
			pstmt.setString(2, score[1]);
			pstmt.setInt(3, Integer.parseInt(score[2]));
			pstmt.setInt(4, Integer.parseInt(score[3]));
			pstmt.setInt(5, Integer.parseInt(score[4]));
			
		pstmt.executeUpdate();
		
			
		out.println("입력 성공");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	%>
<button onclick="location.href='index.jsp'">처음으로</button>
</body>
</html>