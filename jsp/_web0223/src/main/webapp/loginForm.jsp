<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style type="text/css">

.account{
	margin: 10px, 10px;
}

button{
	float: left;
	background: gray;
	color: black;
	margin: 10px, 10px;
}	

.write{
width: 100%;
}

#login{
	float: right; background: red; color: white;
}

</style>
</head>
<body>
<form action="login.do" method="post">
<div class = "account">
<label>아이디:</label><input type="text" class="write">
</div>
<div class = "account">
<label>이메일:</label><input type="text" class="write">
</div>
<button type="submit" id="login">로그인</button>
</form>
</body>
</html>