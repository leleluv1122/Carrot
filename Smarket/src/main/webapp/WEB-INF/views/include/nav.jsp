<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:url var="R" value="/" />
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/minty/bootstrap.min.css"
	rel="stylesheet" media="screen">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link
	href="https://fonts.googleapis.com/css?family=Cute+Font|Poor+Story&display=swap&subset=korean"
	rel="stylesheet">
<link rel="stylesheet" href="${R}res/font.css">
<style>
</style>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-light">
			<a class="navbar-brand"
				style="color: pink; font-family: 'Poor Story', cursive; font-size: 5em; margin-right: 40px;"
				href="${R}shop/index">딸기마켓</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarNav" aria-controls="navbarNav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<form action="/shop/search/" class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search" name="word"
					placeholder="Search" aria-label="Search"
					style="margin-top: 10px; width: 300px;">
				<button class="btn btn-outline-success my-2 my-sm-0"
					style="margin-top: 10px;" type="submit">
					<span class="glyphicon glyphicon-search"
						style="margin-top: 10px; margin: 2px;"></span>
				</button>
			</form>
			<sec:authorize access="not authenticated">
				<a href="/shop/login"
					style="font-family: 'Poor Story', cursive; font-size: 13px; color: black; margin: 5px;">로그인</a>
				<a href="/shop/register"
					style="font-family: 'Poor Story', cursive; color: black; font-size: 13px; margin: 5px;">회원가입</a>
			</sec:authorize>
			<sec:authorize access="authenticated">
				<sec:authentication property="user.id" var="currentid" />
				<a href="/shop/users/${currentid}"
					style="font-family: 'Poor Story', cursive; color: black; font-size: 13px; margin: 5px;"><span
					class="glyphicon glyphicon-user" style="margin-right: 5px;"></span>내정보</a>
				<a
					style="font-family: 'Poor Story', cursive; color: black; font-size: 13px; margin: 5px;"
					class="btn-default btn-xs" href="${R}user/logout_processing">로그아웃</a>
				<br />
				<a href="#"
					style="font-family: 'Poor Story', cursive; color: black; font-size: 13px; margin: 5px; color: black;">관심상품</a>
				<a href="/user/write"
					style="font-family: 'Poor Story', cursive; color: black; font-size: 16px; margin: 5px; color: black;">글쓰기</a>
			</sec:authorize>
		</nav>
		<hr />

		<div>
			<ul>
				<c:forEach var="c" items="${category}">
					<li><a href="${R}shop/category/${c.id}"
						style="color: black; font-size: 1.5em; margin-right: 10px;">${c.name}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>