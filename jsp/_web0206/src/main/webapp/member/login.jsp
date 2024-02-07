<%@ page contentType = "text/html; charset=utf-8" %>
<%@ page import = "util.Cookies" %>
<%
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	if (id.equals(password)) {
		// ID와 암호가 같으면 로그인에 성공한 것으로 판단. << 회원 정보가 없어서 대충 로그인만 기능만 구현해 놓은 상태 
		response.addCookie(
			Cookies.createCookie("AUTH", id, "/", -1)
		);// 로그인 성공시 해당 정보를 AUTH 라는 이름의 쿠키에 id 값을 저장하여 보관함
%>
<html>
<head><title>로그인성공</title></head>
<body>

로그인에 성공했습니다.

</body>
</html>
<%
	} else { // 로그인 실패시
%>
<script>
alert("로그인에 실패하였습니다.");
history.go(-1); // 이전 페이지로 이동
</script>
<%
	}
%>
