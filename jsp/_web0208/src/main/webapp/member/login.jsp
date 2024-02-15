<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page import = "util.Cookies" %>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");
String pw = request.getParameter("pw");
boolean login = false;
String sql = "select count(*) as cnt from score where num = ? and name = ?";
try ( 
    Connection conn = DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
    PreparedStatement pstmt = conn.prepareStatement(sql);
) {
	pstmt.setString(1, id);
	pstmt.setString(2, pw);
	ResultSet rs = pstmt.executeQuery();
	
	rs.next();
	int res = rs.getInt("cnt");
	
	if(res == 1){
		login = true;
		// score에서 sql문의 조건과 일치하는(ID PW와 일치하는) 데이터가 나온다면 로그인 (num은 중복될 수 없음)
	}
	
} catch(Exception e) {
   System.out.println("오류!");
   e.printStackTrace();
}
%>


<html>
<body>
<%
if (login) {
	// ID와 암호가 같으면 로그인에 성공한 것으로 판단.
	response.addCookie(
		Cookies.createCookie("AUTH", id, "/", -1)
	);
%>
<script>
alert('로그인되었습니다'); // 알림팝업을 띄워주는 기능
</script>
<%
response.sendRedirect("index.jsp");
}else{%>
<script>
alert('로그인에 실패했습니다'); // 알림팝업을 띄워주는 기능
</script>
<%    
response.sendRedirect("index.jsp");	}
%>




</body>
</html>

