<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%Cookie ck = new Cookie("logiddnd", "amin");
ck.setMaxAge(60); // 최대60초 생존
response.addCookie(ck);
%>
<%= ck.getName()%><br/>
<%= ck.getValue()%>
</body>
</html>