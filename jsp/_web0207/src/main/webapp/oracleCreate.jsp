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
	

	try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
		pstmt.setInt(1, Integer.parseInt(request.getParameter("num")));
		pstmt.setString(2, request.getParameter("name"));
		pstmt.setInt(3, Integer.parseInt(request.getParameter("kor")));
		pstmt.setInt(4, Integer.parseInt(request.getParameter("eng")));
		pstmt.setInt(5, Integer.parseInt(request.getParameter("math")));
		pstmt.executeUpdate();
		
			
		response.sendRedirect("index.jsp");
		
	} catch (Exception e) {
		response.sendRedirect("index.jsp");
	}
	%>
</body>
</html>