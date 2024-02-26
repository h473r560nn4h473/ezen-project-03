<%@ page import="org.springframework.web.bind.annotation.SessionAttributes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지</title>
</head>
<script>
function escapeCheck() {
	if(confirm("탈퇴하시겠습니까?") == true) {
		location.href = "/Pigi/myInfoDelete.al";
	}
}
</script>
<body>
<br><br>
<div style="text-align:center">

	<div style="text-align: center;">
		<img src="img/myPage.png" width="80px" height="80px">
	</div>
	<br>
	<p></p>
	
	<h3 style="text-align: center;">${EMAIL}님 환영합니다</h3>
	
	<p></p>
	<c:if test="${EMAIL != 'ADMIN'}">
		<a href="/Pigi/myInfoModifyForm.al" class="nav-link" style="font-size:large; color:#ff4a4a;">회원 정보 수정</a>
	</c:if>
		
	<p></p>		

	<a href="/Pigi/myInfoOrder.al" class="nav-link" style="font-size:large; color:#ff4a4a;">주문조회</a>

	<p></p>
	
	<a href="/Pigi/myInfoReview.al" class="nav-link" style="font-size:large; color:#ff4a4a;">후기</a>
	
	<p></p>
	
	<a href="/Pigi/myInfoQna.al" class="nav-link" style="font-size:large; color:#ff4a4a;">고객센터</a>
	
	<p></p>
		
	<c:if test="${EMAIL != 'ADMIN'}">	
		<a href="javascript:escapeCheck()" class="nav-link" style="font-size:large; color:#ff4a4a;">회원탈퇴</a>
	</c:if>
	
	<p></p>				
</div>
<br><br>
</body>
</html>