<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
	boolean login = false;
    
    Class.forName("oracle.jdbc.driver.OracleDriver");
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
    
    
    if (login) {
        Cookie cookie = new Cookie("userId", id); // 어떤 아이디로 로그인 중인지 인증서 쿠키 발행
        cookie.setMaxAge(-1);         // 웹 브라우저가 닫힐 때 쿠키 만료됨
        response.addCookie(cookie);
        
        response.sendRedirect("index.jsp");   // 로그인 메인 화면으로 돌아감
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>

<script>
    alert('아이디 또는 비밀번호가 틀립니다!'); // 알림팝업을 띄워주는 기능
    history.back(); // 세션의 히스토리에 기록된 이전 페이지로 돌아가는 기능
</script>
 
</body>
</html>