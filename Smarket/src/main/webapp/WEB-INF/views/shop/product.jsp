<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	margin-left: auto;
	margin-right: auto;
	margin-top: 30px;
	margin-bottom: 60px;
	display: block;
	width: 500px;
	height: 400px;
	overflow: hidden;
	border-radius: 8px;
	display: block;
}

div .ab {
	margin-left: auto;
	margin-right: auto;
	display: block;
	width: 677px;
	height: 40px;
	margin-right: auto;
	display: block;
	width: 677px;
	height: 40px;
	margin-bottom: 30px;
	cursor: pointer;
}

div .abcc {
	margin-left: auto;
	margin-right: auto;
	display: block;
	width: 677px;
	height: 40px;
	margin-right: auto;
	display: block;
	width: 677px;
	height: 40px;
	margin-bottom: 120px;
}

}
div .a {
	margin-left: auto;
	margin-right: auto;
	display: block;
	width: 40px;
	height: 44px;
	margin-right: auto;
	display: block;
}

div .nick {
	margin-left: auto;
	margin-right: auto;
	display: block;
	width: 78px;
	height: 40px;
	margin-right: auto;
	display: block;
}

div .z {
	margin-left: auto;
	margin-right: auto;
	display: block;
	width: 132px;
	height: 44px;
	margin-right: auto;
	display: block;
}
.noimg{
	width:30px;
	height:30px;
	border-radius: 8px;
}
</style>
<body>
	<%@ include file="../include/nav.jsp"%>

	<div class="container">
		<div>
				<c:forEach var="pi" items="${pi}">
					<img src="/images/${pi.filename}" class="imgg">
				</c:forEach>

				<div class="ab" onclick="location.href='/shop/users/${p.user.id}'">
					<label style="margin-left: 80px;"><img src="/images/blank-profile-picture-973460_1280.png" class="noimg"></label> <span
						style="margin-left: 10px; font-size: 18px; font-weight: 600; line-height: 1.5; letter-spacing: -0.6px; color: #212529;"><b>${p.user.nickname}</b></span>
					<br /> <span style="margin-left: 120px; font-size: 16px;">
						<c:choose>
							<c:when test="${fn:length(p.user.address) gt 8}">
								<c:out value="${fn:substring(p.user.address, 0, 7)}">
								</c:out>
							</c:when>
							<c:otherwise>
								<c:out value="${p.user.address}">
								</c:out>
							</c:otherwise>
						</c:choose>
					</span> <span style="margin-left: 6px; font-size: 16px;"> <c:choose>
							<c:when test="${fn:length(p.user.addrplus) gt 6}">
								<c:out value="${fn:substring(p.user.addrplus, 2, 5)}">
								</c:out>
							</c:when>
							<c:otherwise>
								<c:out value="${p.user.addrplus}">
								</c:out>
							</c:otherwise>
						</c:choose></span>

				</div>


				<div class="abcc">
					<hr />
					<span style="margin-left: 80px; font-size: 20px;"><b>${p.title}</b></span>
					<br /> <span style="margin-left: 80px; font-size: 16px;">${p.category.name}</span>
					<span style="margin-left: 30px; font-size: 16px;"><fmt:formatDate
							value="${p.writedate}" pattern="yyyy.MM.dd HH:mm:ss" /></span> <br />
					<span style="margin-left:80px;color:orange; font-size:18px;">${p.price} 원</span><br />
					<span style="margin-left: 80px; font-size:18px;"><b>${p.detail}</b></span> <br />
					<hr />
					<label style="margin-left: 80px; font-size:18px;">조회수</label><span style="margin-left:5px;font-size:18px;">${p.click}</span>
				</div>

		</div>
		<br /><br />
		<hr />
	</div>
	<%@ include file="../include/bottom.jsp"%>
</body>
</html>