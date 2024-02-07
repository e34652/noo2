<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page import = "util.Cookies" %>
<%
	response.addCookie(
		Cookies.createCookie("AUTH", "", "/", 0) 
		// 아래의 매개값으로 만들어진 쿠키를 현재의 HTTP 응답에 추가함
		//쿠키의 값이 비어 있거나 null일 경우는 쿠키를 삭제하는 것과 동일한 효과를 가지며
		//경로가 "/"로 설정되어 있으면 해당 쿠키는 사이트의 모든 페이지에서 유효함 
		//수명을 0으로 설정하면 쿠키가 즉시 만료되어 브라우저에 의해 삭제됨 
		//해당 메서드로 만들어진 쿠키가 최신이고 AUTH 라는 쿠키명이 중복되므로 클라이언트가 서버로부터의 응답과 함께 해당 쿠키를 전송받는 순간 쿠키가 만료되며 로그아웃됨 
	);
%>
<html>
<head><title>로그아웃</title></head>
<body>

로그아웃하였습니다.

</body>
</html>
