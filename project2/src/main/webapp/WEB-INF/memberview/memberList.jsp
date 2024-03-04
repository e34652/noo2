<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap demo</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="index.do">DCODLAB</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="boardList.do">게시판</a></li>

					<li class="nav-item"><a class="nav-link disabled"
						aria-disabled="true">회원관리</a></li>
				</ul>
				<form class="d-flex" role="search">
					<input class="form-control me-2" type="search" placeholder="Search"
						aria-label="Search">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
			</div>
		</div>
	</nav>
	<div class="container" style="padding-top: 50px">
		<table class="table table-bordered table-hover">

			<tr>
				<th scope="col">Memberno</th>
				<th scope="col">ID</th>
				<th scope="col">Email</th>
				<th scope="col">Name</th>
				<th scope="col">Actions</th>
			</tr>


			<c:if test="${ArticleM.hasNoArticles()}">
				<tr>
					<td colspan="4">게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach var="member" items="${ArticleM.contentM}">
				<tr>
					<td>${member.memberno}</td>
					<td><c:out value="${member.id}" /></td>
					<td>${member.email}</td>
					<td>${member.name}</td>
					<td>
						<a href="memberDelete.do?memberno=${member.memberno}"
								class="btn btn-warning">삭제</a>
								
						<a href="memberUpdateForm.do?memberno=${member.memberno}"
								class="btn btn-warning">수정</a> 
					</td>
				</tr>
			</c:forEach>

			<c:if test="${ArticleM.hasArticles()}">
				<tr>
					<td colspan="5"><c:if test="${ArticleM.startPage > 5}">
							<a href="memberList.do?pageNo=${ArticleM.startPage - 5}">[이전]</a>
						</c:if> <c:forEach var="pNo" begin="${ArticleM.startPage}"
							end="${ArticleM.endPage}">
							<a href="memberList.do?pageNo=${pNo}">[${pNo}]</a>
						</c:forEach> <c:if test="${ArticleM.endPage < ArticleM.totalPages}">
							<a href="memberList.do?pageNo=${ArticleM.startPage + 5}">[다음]</a>
						</c:if></td>
				</tr>
			</c:if>


		</table>

		<button class="btn btn-secondary" onclick="move()">회원 등록</button>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
		crossorigin="anonymous"></script>

	<script>
		function move() {
			location.href = "memberInsertForm.do";
		}
		function addRow(conent) {
			let table = document.getElementById("myTable");
			table.innerHTML = conent
		}

		window.onload = function() {
			let data = `<tr><td><a href="http://www.naver.com">네이버</a></td></tr> <tr><td>abcd</td></tr>`;
			addRow(data);
		}
	</script>
</body>
</html>