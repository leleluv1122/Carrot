<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<c:url var="R" value="/" />
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>딸기마켓 제품보기</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="res/index.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=Cute+Font|Poor+Story&display=swap&subset=korean"
	rel="stylesheet">
</head>
<style>
/* div .abcde {
	height: 504px;
	width: 729px;
} */

.imgg {
	margin-left:auto;
	margin-right:auto;
	margin-top:30px;
	margin-bottom:50px;
	display: block;
	width: 500px;
	height: 400px;
	overflow: hidden;
	border-radius: 8px;
}
</style>
<body>
	<%@ include file="../include/nav.jsp"%>

	<div class="container">
		<div>
			<img src="/images/${pi.filename}" class="imgg">
		</div>
	</div>
	<%@ include file="../include/bottom.jsp"%>
</body>
</html>
