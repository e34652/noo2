<%@page import="java.sql.ResultSet"%>
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
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url, "scott", "tiger");
	String sql = "select name, kor, eng, math from score where num = ?";
	PreparedStatement pstmt = conn.prepareStatement(sql); // Statement와 달리 stmt에서 미리 컴파일을 하므로 여기서 sql문을 넣어줌
	pstmt.setString(1, request.getParameter("num"));
	ResultSet rs = pstmt.executeQuery();
	String name = ""; String kor = ""; String eng = ""; String math = "";
	if(rs.next()){
		name = rs.getString("name");
		kor = rs.getString("kor");
		eng = rs.getString("eng");
		math = rs.getString("math");
	}
	%>
<a href="index.jsp">목록으로</a>

<form action="oracleUpdate.jsp" method = post> 
번호<input type="text" name = "num" value =<%=request.getParameter("num")%> /><br>
이름<input type="text" name = "name" value = <%=rs.getString("name")%> /><br>
국어<input type="text" name ="kor" value = <%=rs.getString("kor")%> /><br>
영어<input type="text" name ="eng" value = <%=rs.getString("eng")%> /><br>
수학<input type="text" name = "math" value = <%=rs.getString("math")%> /><br>
<input type="submit" value="전송"/>
<input type="reset" value = "리셋"/>
</form>

</body>
</html>