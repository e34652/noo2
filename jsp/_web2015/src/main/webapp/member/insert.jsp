<%@page import="dto.Board"%>
<%@page import="dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.sql.*" %>  
<%@ page import="java.time.*" %>
 
<%
    request.setCharacterEncoding("utf-8");

String memberId = (String) session.getAttribute("MEMBERID");
if (memberId == null) { //로그인 안하면 못들어옴
	response.sendRedirect("sessionLoginForm.jsp");
}

String writer  = request.getParameter("writer" );
String title   = request.getParameter("title"  );
String content = request.getParameter("content");

//빈 칸이 하나라도 있으면 오류 출력하고 종료
if (writer  == null || writer.length()  == 0 ||
 title   == null || title.length()   == 0 ||
 content == null || content.length() == 0) {
%>      
 <script>
     alert('모든 항목이 빈칸 없이 입력되어야 합니다.');
     history.back();
 </script>
<%        
 return;
}
String regtime = LocalDate.now() + " " + LocalTime.now().toString().substring(0, 8);

BoardDao dao = BoardDao.getInstance();
Board board = new Board(0,writer, title,
		content, regtime, 0);

int result = dao.insert(board);

if (result == 1) {
%>
<script>
		alert("작성 완료");
		window.location.href = "list.jsp";
</script>
<%
} else {
%>
<script>
alert("작성에 실패했습니다");
window.location.href = "list.jsp";
</script>
<%
}
%>