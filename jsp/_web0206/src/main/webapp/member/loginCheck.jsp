<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page import = "util.Cookies" %>
<%
	Cookies cookies = new Cookies(request);
%>
<html>
<head><title>로그인여부 검사</title></head>
<body>

<%
	if (cookies.exists("AUTH")) { // 키 << AUTH, 값 << 저장되어있는 ID 값, 이미 login에서 일치 여부에 대한 검증을 마쳤기에 존재 여부만으로 판단 가능 
%>
아이디 "<%= cookies.getValue("AUTH") %>"로 로그인 한 상태 
<%
	} else {
%>
로그인하지 않은 상태
<%
	}
%>
</body>
</html>
