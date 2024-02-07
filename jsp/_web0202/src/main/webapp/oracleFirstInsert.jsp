<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

	<%
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String sql = "INSERT INTO score VALUES (?, ?, ?, ?, ?)";
	String[][] score = { 
			{ "1", "홍길동", "50", "60", "70" }, 
			{ "2", "이순신", "65", "75", "85" },
			{ "3", "강감찬", "60", "80", "70" } 
			};

	try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
		for (int i = 0; i < score.length; i++) {
		pstmt.setInt(1, Integer.parseInt(score[i][0]));
		pstmt.setString(2, score[i][1]);
		pstmt.setInt(3, Integer.parseInt(score[i][2]));
		pstmt.setInt(4, Integer.parseInt(score[i][3]));
		pstmt.setInt(5, Integer.parseInt(score[i][4]));
			
		pstmt.executeUpdate();
		
			}
		out.println("초기값 입력 성공");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	%>

</body>
</html>