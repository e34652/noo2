 <%@page import="dto.Board"%>
<%@page import="dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%>
     
 <%@ page import="java.sql.*" %>  
 <%@ page import="java.time.*" %>
  
 <%
     request.setCharacterEncoding("utf-8");

     // 전달받은 값 읽기
     int    num     = Integer.parseInt(request.getParameter("num"));
     String writer  = request.getParameter("writer" );
     String title   = request.getParameter("title"  );
     String content = request.getParameter("content");
     String regtime = LocalDate.now() + " " + 
             LocalTime.now().toString().substring(0, 8);
     // 빈 칸이 하나라도 있으면 오류 출력하고 종료
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
     BoardDao dao = BoardDao.getInstance();
     Board board = new Board(num, writer, title,
     		content, regtime);
     int result = dao.update(board);
 
     if (result == 1) {
    	 %>
    	 <script>
    	 		alert("수정 완료");
    	 		window.location.href = "list.jsp";
    	 </script>
    	 <%
    	 } else {
    	 %>
    	 <script>
    	 alert("수정에 실패했습니다");
    	 window.location.href = "list.jsp";
    	 </script>
    	 <%
    	 }
    	 %>