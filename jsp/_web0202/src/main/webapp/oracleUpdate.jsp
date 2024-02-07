<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
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
<a href="index.jsp">목록으로</a>
<% 
request.setCharacterEncoding("utf-8");
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
String sql = "update score set name = ?, kor = ?, eng = ?, math = ? where num = ?";
PreparedStatement pstmt = conn.prepareStatement(sql); // Statement와 달리 stmt에서 미리 컴파일을 하므로 여기서 sql문을 넣어줌
pstmt.setString(1, request.getParameter("name"));
pstmt.setString(2, request.getParameter("kor"));
pstmt.setString(3, request.getParameter("eng"));
pstmt.setString(4, request.getParameter("math"));
pstmt.setString(5, request.getParameter("num"));
pstmt.executeUpdate();
out.println("수정 성공");
%>
</body>
</html>