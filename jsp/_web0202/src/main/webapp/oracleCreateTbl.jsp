<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>   
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>

<%
    Class.forName("oracle.jdbc.driver.OracleDriver");
	String sql = 
	"create table score (" + 
	"    num  number          primary key," + 
	"    name varchar2(20),             " +
	"    kor  number,                     " +    
	"    eng  number,                     " +
	"    math number                      " +    
	")";
	
    try ( 
        Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		
    ) {
        pstmt.executeUpdate();
        out.println("성적 테이블 생성 성공 !");
        
        
    } catch(Exception e) {
        e.printStackTrace();
    }
%>
<button onclick="location.href='oracleFirstInsert.jsp'">초기 값 입력</button>
<button onclick="location.href='index.jsp'">처음으로</button>

</body>
</html>