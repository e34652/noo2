<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
			<a class="navbar-brand" href="#">Navbar</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> Dropdown </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="#">Action</a></li>
							<li><a class="dropdown-item" href="#">Another action</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="#">Something else
									here</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-link disabled"
						aria-disabled="true">Disabled</a></li>
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
		<h1>Hello, world!</h1>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th scope="col">memberno</th>
					<th scope="col">ID</th>
					<th scope="col">Email</th>
					<th scope="col">Name</th>
					
				</tr>
			</thead>
			<tbody>

			</tbody>
			<c:if test="${ArticlePage.hasNoArticles()}">
				<tr>
					<td colspan="4">게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach var="articlePage" items="${ArticlePage.comtentM}">
				<tr>
					<td>${articlePage.memberno}</td>
					<td><a href="view.do?num=${ArticlePage.memberno}"> <c:out value="${articlePage.id}" /></a></td>
					<td>${articlePage.email}</td>
					<td>${articlePage.name}</td>
					<td><a href="updateForm.jsp?memberno=${articlePage.memberno}">수정</a></td>
					<td><button class="btn btn-warning" onClick='location.href="delete.jsp?memberno=${articlePage.memberno}"'>삭제</button>
					</td>
				</tr>
			
				
			</c:forEach>
			<c:if test="${ArticlePage.hasArticles()}">
				<tr>
					<td colspan="5"><c:if test="${ArticlePage.startPage > 5}">
							<a href="list.do?pageNo=${ArticlePage.startPage - 5}">[이전]</a>
						</c:if> <c:forEach var="pNo" begin="${ArticlePage.startPage}"
							end="${ArticlePage.endPage}">
							<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
						</c:forEach> <c:if test="${ArticlePage.endPage < ArticlePage.totalPages}">
							<a href="list.do?pageNo=${ArticlePage.startPage + 5}">[다음]</a>
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
		function move(){
		location.href="memberForm.jsp";
		}
		</script>
</body>
</html>