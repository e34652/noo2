<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="util.Cookies"%>
<%
Cookies cookies = new Cookies(request);
%>
<html>
<head>
<title>로그인여부 검사</title>
</head>
<body>

<%
	if (cookies.exists("AUTH")) {
%>
		<script>
			alert('로그인 상태입니다'); 
		</script>
<%
		response.sendRedirect("index.jsp");
	
	} 
	%>
</body>
</html>
