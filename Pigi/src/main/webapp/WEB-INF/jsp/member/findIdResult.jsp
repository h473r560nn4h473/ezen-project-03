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
	<span style="font-size:xx-large; color:#ff4a4a;"><b>이메일 찾기 결과</b></span>
</div>
<hr>
<div style="text-align:center">
	<c:if test="${empty Find}">
		<br><br><br>
		고객님의 회원가입시 이메일는 ${memberBean.EMAIL} 입니다
		
		<p></p>
		<br><br><br>
		<input type="button" value="로그인" class="btn btn-primary py-2 px-4"
			onClick="location.href='/Pigi/loginForm.al'">
		<input type="button" value="비밀번호 찾기" class="btn btn-light py-2 px-4"
			onClick="location.href='/Pigi/findPw.al'">
	</c:if>
    
	<c:if test="${!empty Find}">
		<br><br><br>
		존재하지 않는 회원입니다
		
		<p></p>
		<br><br><br>
		<input type="button" value="로그인" class="btn btn-primary py-2 px-4"
			onClick="location.href='/Pigi/loginForm.al'">
		<input type="button" value="회원가입" class="btn btn-light py-2 px-4"
			onClick="location.href='/Pigi/join.al'">
	</c:if>
			

	
	
		
	<p></p>		

</div>

</body>
</html>