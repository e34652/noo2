<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
String name = request.getParameter("name");
String value = request.getParameter("value");

Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
response.addCookie(cookie); // 서버에서 쿠키 만들어서 클라이언트에게 추가하라고 요청함 
response.sendRedirect("listCookies.jsp");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>