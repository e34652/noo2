<%@page import="util.Cookies"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	Cookies cookies = new Cookies(request);
	if(!cookies.exists("AUTH")){
		response.sendRedirect("loginForm.jsp");
	}
%>
	
<% 
String numU = "";
String nameU = "";
String korU = "";
String engU = "";
String mathU = "";
String sql = "select * from score where num = " + request.getParameter("num");
try (	
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
){ 

	if (rs.next()) {
		numU = rs.getString("num");
		nameU = rs.getString("name");
		korU = rs.getString("kor");
		engU = rs.getString("eng");
		mathU = rs.getString("math");
	}
	
} catch (Exception e) {

}

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
		번호<input type="text" name="num" value="<%=numU%>" /><br /> 
		이름<input type="text" name="name" value="<%=nameU%>" /><br /> 
		국어<input type="text" name="kor" value="<%=korU%>" /><br /> 
		영어<input type="text" name="eng" value="<%=engU%>" /><br /> 
		수학<input type="text" name="math" value="<%=mathU%>" /><br />
		
	</form>
	<button onclick="create()">입력</button>
	<button onclick="update()">수정</button>
	<button onclick="clean()">리셋</button>
	<table>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>국어</th>
			<th>영어</th>
			<th>수학</th>
			<th>총점</th>
			<th>평균</th>
		</tr>
		<%
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String sql0 = "select * from score order by num";
		String sql1 = "select count('name') from score";

		try (
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
				PreparedStatement pstmt = conn.prepareStatement(sql0);
				PreparedStatement pstmt1 = conn.prepareStatement(sql1);
				ResultSet rs = pstmt.executeQuery();
				ResultSet rs1 = pstmt1.executeQuery();
				 ){
			int stuCount = 0;
			int sumClass = 0;
			int subCount = 3;

			int maxKor = 0;
			int maxEng = 0;
			int maxMath = 0;
			int stuNum = 0;


			rs1.next();//커서를 한칸 내려 값을 불러올 수 있게 함 안불러오면 읽는 칸이 없음
			stuCount = rs1.getInt("count('name')");
			while (rs.next()) {
				stuNum = rs.getInt("num");
				int scoreKor = rs.getInt("kor");
				int scoreEng = rs.getInt("eng");
				int scoreMath = rs.getInt("math");

				int sum = rs.getInt("kor") + rs.getInt("eng") + rs.getInt("math");
				sumClass += sum;

				if (maxKor <= scoreKor) {
			maxKor = scoreKor;
				}

				if (maxEng <= scoreEng) {
			maxEng = scoreEng;
				}

				if (maxMath <= scoreMath) {
			maxMath = scoreMath;
				}
		%>
		<tr>
			<td><%=rs.getString("num")%></td>
			<td><a href="index.jsp?num=<%=rs.getInt("num")%>"><%=rs.getString("name")%></a></td>
			<td><%=rs.getInt("kor")%></td>
			<td><%=rs.getInt("eng")%></td>
			<td><%=rs.getInt("math")%></td>
			<td><%=sum%></td>
			<td><%=String.format("%.2f", (float) sum / subCount)%></td>
			<td><button
					onclick="location.href='oracleDelete.jsp?num=<%=stuNum%>'">삭제</button></td>
		</tr>


		<%
		}
		%>
		<tr>
			<td colspan="7"><%=String.format("전체 평균 = %.2f", (float) sumClass / stuCount / subCount)%></td>
		</tr>
		<tr>
			<td colspan="7"><%=String.format("최고 점수\n국어: %d\n영어: %d\n수학: %d", maxKor, maxEng, maxMath)%></td>
		</tr>
	</table>

	<%
	} catch (Exception e) {

	}
	%>
<script>
function create() {
	document.getElementById('frm').action = 'oracleCreate.jsp';
	document.getElementById('frm').submit();
}
function update() {
	document.getElementById('frm').action = 'oracleUpdate.jsp';
	document.getElementById('frm').submit();
}
function clean() {
	var el = document.getElementById('frm');
	for(var i=0; i<el.length; i++){
	el[i].value = '';}
}
function loginCheck(){
	document.get
}

</script>
</body>
</html>