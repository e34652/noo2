<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% 
    String nameU = ""; 
    String valueU ="";
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	width: 400px;
	text-align: center;
}

th {
	background-color: cyan;
}
</style>
</head>
<body>
	<form method="get" id="frm">
		쿠키 이름<input type="text" name="name" value="<%=nameU%>" /><br /> 
		쿠키 값&nbsp;&nbsp;&nbsp;<input type="text" name="value" value="<%=valueU%>" /><br />
	</form>
	<button onclick="create()">입력</button>
	<button onclick="update()">수정</button>
	
	<table>
		<tr>
			<th>쿠키 이름</th>
			<th>쿠키 값</th>
		</tr>
<% 
Cookie[] cookies = request.getCookies();  //전송된 쿠키를 가져오는 과정
if(cookies != null && cookies.length > 0){ // 쿠키가 있는지 없는지 확인 
	for(Cookie ck : cookies){ // for문으로 쿠키배열을 쭉 돌면서 비교함 
		if(!ck.getName().equals("JSESSIONID")){ // 이름이 JSESSIONID가 아닌쿠키만 불러옴
%>
		<tr>
			<td><%=ck.getName()%></td>
			<td><%=URLDecoder.decode(ck.getValue(), "utf-8") %></td>
			<td><a href="delCookie.jsp?name=<%=ck.getName()%>">삭제</a></td> 
		</tr>
<% 
		}
	}
}
%>
	</table>
	<script>
function create() { // 입력 버튼을 누르면 텍스트 필드에 입력된 값들을 mkCookie로 보냄
	document.getElementById('frm').action = 'mkCookie.jsp';
	document.getElementById('frm').submit();
}
function update() { // 입력 버튼을 누르면 텍스트 필드에 입력된 값들을 updCookie로 보냄
	document.getElementById('frm').action = 'updCookie.jsp';
	document.getElementById('frm').submit();
}

		
}

</script>
</body>
</html>