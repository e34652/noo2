<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.sql.*"%>
<%@page import="dto.Board"%>
<%@page import="java.util.List"%>
<%@page import="dao.BoardDao"%>


<%
	String memberId = (String) session.getAttribute("MEMBERID");
	if (memberId == null) { //로그인 안하면 못들어옴
		response.sendRedirect("sessionLoginForm.jsp");
	}
	//지정된 글 번호 얻기
	int num = Integer.parseInt(request.getParameter("num"));

	BoardDao dao = BoardDao.getInstance();
	int result = dao.del(num);

	if (result == 1) {
%>
<script>
	alert("삭제되었습니다");
	window.location.href = "list.jsp";
</script>
<%
	} else {
%>
<script>
	alert("권한이 없습니다");
	window.location.href = "list.jsp";
</script>
<%
	}
%>