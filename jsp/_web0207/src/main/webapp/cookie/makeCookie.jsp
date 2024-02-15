<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    Cookie cookie = new Cookie("aaa", URLEncoder.encode("홍길동", "utf-8"));// 쿠키를 주고받을때의 보안성과 특정 문자와의 오류를 방지하기 위해 인코딩해줌
    response.addCookie(cookie);// 쿠키를 인코딩하여 포장한 후 클라이언트에 쿠키를 저장할 것을 요청
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
쿠키 생성

</body>
</html>