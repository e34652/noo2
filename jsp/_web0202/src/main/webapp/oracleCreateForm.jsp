<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="index.jsp">목록으로</a>
<form action="oracleCreate.jsp" method = get> 
번호<input type="text" name ="stuNum" /><br>
이름<input type="text" name ="stuName" /><br>
국어<input type="text" name ="Kor" /><br>
영어<input type="text" name ="Eng" /><br>
수학<input type="text" name ="Math" /><br>
<input type="submit" value="전송"/>
<input type="reset" value = "리셋"/>
</form>
</body>
</html>