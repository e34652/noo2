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
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}

/* 홀수 번째 행의 테두리 스타일 */
tr:nth-child(odd) {
	background-color: #f2f2f2;
	border: 1px solid #a9a9a9;
}

/* 짝수 번째 행의 테두리 스타일 */
tr:nth-child(even) {
	background-color: #ffffff;
	border: 1px solid #a9a9a9;
}
</style>
</head>
<body>
	<a href="index.jsp">목록으로</a>
	<%
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url, "scott", "tiger");
	String sql = "select ename, job, sal from emp1";
	PreparedStatement pstmt = conn.prepareStatement(sql); // Statement와 달리 stmt에서 미리 컴파일을 하므로 여기서 sql문을 넣어줌
	ResultSet rs = pstmt.executeQuery();
	%>
	<table border="1">
		<tr>
			<td>Name</td>
			<td>Job</td>
			<td>Salary</td>
		</tr>
		<%
		while (rs.next()) {
		%>
		<tr>
		
			<td><a href="updateForm.jsp?ename=<%=rs.getString("ename")%>"><%=rs.getString("ename")%></a></td>
			<td><%=rs.getString("job")%></td>
			<td><%=rs.getString("sal")%></td>
			<td><a href="delete.jsp?ename=<%=rs.getString("ename")%>">삭제</a></td>
		</tr>
		<tr>
			<td colspan=3></td>
		</tr>
		<%}%>
	</table>
</body>
</html>