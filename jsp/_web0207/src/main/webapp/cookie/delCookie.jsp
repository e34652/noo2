<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String name = request.getParameter("name"); //listCookies에서 넘어온 name 값을 불러옴 << 쿠키의 이름
Cookie[] cookies = request.getCookies();
if (cookies != null && cookies.length > 0) {
	for (Cookie ck : cookies) {
		if (ck.getName().equals(name)) {
	Cookie cookie = new Cookie(name, "");
	cookie.setMaxAge(0);//삭제
	response.addCookie(cookie); //브라우저(클라이언트)에 쿠키 파일을 저장해달라고 요청  
		}
	}
}
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