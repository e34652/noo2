<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String pw = request.getParameter("pw");
int res = 0;
boolean login = false;
String sql = "select count(*) from board where num = ? and writer = ?";
Class.forName("com.mysql.cj.jdbc.Driver");
try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1", "root", "mysql");
		PreparedStatement pstmt = conn.prepareStatement(sql);) {
	pstmt.setString(1, id);
	pstmt.setString(2, pw);
	try (ResultSet rs = pstmt.executeQuery()) {

		rs.next();
		if (rs.getInt(1) == 1) {
	login = true;

	// score에서 sql문의 조건과 일치하는(ID PW와 일치하는) 데이터가 나온다면 로그인 (num은 중복될 수 없음)
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
} catch (Exception e) {
	System.out.println("오류!");
	e.printStackTrace();
}
if (login) {
	session.setAttribute("MEMBERID", id);
	response.sendRedirect("list.jsp");
%>

<html>
<head>

</head>
<body>
	로그인에 성공했습니다.f<%
} else { // 로그인 실패시
%>
</body>
</html>

<script>
	alert("로그인에 실패하였습니다.");
	history.go(-1);
</script>
<%
}
%>
