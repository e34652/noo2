<%@page import="dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
  </head>
  <body>
<div class="container" style="padding-top: 50px;">
<table class="table table-bordered table-hover">
    <tr>
        <th class="num"    >번호    </th>
        <th class="title"  >제목    </th>
        <th class="name" >작성자  </th>
        <th class="regtime">작성일시</th>
        <th                >조회수  </th>
    </tr>

<c:if test="${Article.hasNoArticles()}">
	<tr>
		<td colspan="4">게시글이 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="board" items="${Article.contentB}">
	<tr>
		<td>${board.num}</td>
		<td>
		<a href="boardView.do?num=${board.num}">
		<c:out value="${board.title}"/>
		</a>
		</td>
		<td>${board.name}</td>
		<td>${board.regtime}</td>
		<td>${board.hits}</td>
	</tr>
</c:forEach>
<c:if test="${Article.hasArticles()}">
	<tr>
		<td colspan="5">
			<c:if test="${Article.startPage > 5}">
			<a href="list.do?pageNo=${Article.startPage - 5}">[이전]</a>
			</c:if>
			<c:forEach var="pNo" 
					   begin="${Article.startPage}" 
					   end="${Article.endPage}">
			<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
			</c:forEach>
			<c:if test="${Article.endPage < Article.totalPages}">
			<a href="list.do?pageNo=${Article.startPage + 5}">[다음]</a>
			</c:if>
		</td>
	</tr>
</c:if>
</table>

<br>
<input type="button" value="글쓰기" class="btn btn-primary" onclick="location.href='boardWrite.do'">

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
  </body>
</html>