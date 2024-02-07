<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
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
String driver = "oracle.jdbc.driver.OracleDriver";
String url = "jdbc:oracle:thin:@localhost:1521:xe";
Class.forName(driver);
Connection conn = DriverManager.getConnection(url, "scott", "tiger");
String sql = "update emp1 set ename = ?, job = ?, sal = ? where ename = ?";
PreparedStatement pstmt = conn.prepareStatement(sql); // Statement와 달리 stmt에서 미리 컴파일을 하므로 여기서 sql문을 넣어줌
pstmt.setString(1, request.getParameter("ename"));
pstmt.setString(2, request.getParameter("job"));
pstmt.setString(3, request.getParameter("sal"));
pstmt.setString(4, request.getParameter("newname"));
pstmt.executeUpdate();
%>
수정성공
<a href="index.jsp">목록으로</a>


</body>
</html>