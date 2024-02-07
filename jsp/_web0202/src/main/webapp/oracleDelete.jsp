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
<% 
request.setCharacterEncoding("utf-8");
Class.forName("oracle.jdbc.driver.OracleDriver");
String sql ="delete from score where num = ?";
try(
Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
PreparedStatement pstmt = conn.prepareStatement(sql);
		){
 
pstmt.setString(1,request.getParameter("num"));
pstmt.executeUpdate();
response.sendRedirect("oracleList.jsp");
}catch(Exception e){
	
}

%>


</body>
</html>