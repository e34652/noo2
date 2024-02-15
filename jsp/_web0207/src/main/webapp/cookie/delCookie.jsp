<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String name = request.getParameter("name"); //listCookies에서 넘어온 name 값을 불러옴 << 지우고 싶은 쿠키의 이름
Cookie[] cookies = request.getCookies(); // 쿠키를 불러와 비교함 
if (cookies != null && cookies.length > 0) {
	for (Cookie ck : cookies) {
		if (ck.getName().equals(name)) { // 지우고 싶은 이름과 쿠키의 이름이 일치한다면
	Cookie cookie = new Cookie(name, ""); // 덮어쓰기 위해 같은 이름으로 쿠키로 만들어야함 
	cookie.setMaxAge(0);//삭제, 
	response.addCookie(cookie); //브라우저(클라이언트)에 쿠키 파일을 저장해달라고 요청, 저장하는 순간 덮어씌워지면서 수명이 0이되어 사라짐   
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