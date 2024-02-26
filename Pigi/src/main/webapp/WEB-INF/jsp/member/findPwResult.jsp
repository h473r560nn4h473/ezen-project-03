<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지</title>
</head>

<body>

<div style="text-align:center">
	<br>
	<span style="font-size:xx-large; color:#ff4a4a;"><b>비밀번호 찾기 결과</b></span>
</div>
<hr>
<div style="text-align:center">
	<c:choose>
         <c:when test = "${!empty invalidEMAIL}">
         <br><br><br>
         존재하지 않는 이메일 입니다
         <br><br><br>
         </c:when>
         <c:when test = "${!empty invalidNAME}">
         <br><br><br>
         존재하지 않는 회원 입니다
         <br><br><br>
         </c:when>
         <c:when test ="${empty Find}">
         <br><br><br>
         고객님의 회원가입시 비밀번호는 ${memberBean.PASSWORD} 입니다
         <br><br><br>
         </c:when>
         <c:when test = "${!empty Find}">
         <br><br><br>
         존재하지 않는 회원 입니다
         <br><br><br>
         </c:when>
	</c:choose>
	<p></p>		

	<input type="button" value="로그인" class="btn btn-primary py-2 px-4"
			onClick="location.href='/Pigi/loginForm.al'">
		
	<p></p>		

</div>

</body>
</html>