<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지</title>
</head>
<style>
#EMAIL {
  display: block;
  min-width : 15%;
  max-width : 100%;
  height: calc(2.25rem + 2px);
  margin : 0 auto;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
}
#PASSWORD {
  display: block;
  min-width : 15%;
  max-width : 100%;
  height: calc(2.25rem + 2px);
  margin : 0 auto;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
}
</style>
<script>
/* 로그인 유효성 체크 */
function formCheck() {
	var form = document.getElementById("loginForm");
	var EMAIL = document.getElementById("EMAIL");
	var PASSWORD = document.getElementById("PASSWORD");
	
	if(EMAIL.value.trim()=="") {
		alert("이메일 주소를 입력해주세요.");
		EMAIL.focus();
		return false;
	} else if(PASSWORD.value.trim()=="") {
		alert("비밀번호를 입력해주세요.");
		return false;
	} else {	
		form.submit();
	}
}

/* 로그인 화면에서 엔터키 입력시 로그인 시도 */
function keyPress() {
	if(window.event.keyCode ==13) {
		return formCheck();	
	}	
}

/* 첫 화면 로딩 시 포커스 */
window.onload = function() {
	document.getElementById("EMAIL").focus();
}
</script>
<script>
$(document).on("keyup", "input[noSpecial]", function() {$(this).val( $(this).val().replace(/[^ㄱ-힣a-zA-Z0-9@.]/gi,"") );})
$(document).on("keyup", "input[noBlank]", function() {$(this).val( $(this).val().replace(/\s/gi,"") );})
</script>
<body>

<div style="text-align:center">
	<br>
	<span style="font-size:xx-large; color:#ff4a4a;"><b>로 그 인</b></span>
</div>
<hr>
<div style="text-align:center">
	<form id="loginForm" action="/Pigi/login.al" method="post">
		<label for="EMAIL">이메일 주소</label>
		<input type="text" id="EMAIL" name="EMAIL" maxlength="30" noSpecial>
		
		<label for="PASSWORD">비밀번호</label>
		<input type="password" id="PASSWORD" name="PASSWORD" onkeypress="keyPress()" noBlank>
	
		<!-- 이메일 저장 &nbsp; <input type="checkbox" id="ID_SAVE" name="ID_SAVE"><br> -->
		<p></p>
		
		<input type="button" value="로 그 인" class="btn btn-primary py-2 px-4"
			onClick="return formCheck()">
		<input type="button" value="회원가입" onClick="location.href='/Pigi/joinForm.al'" class="btn btn-primary py-2 px-4"><br>
		
		<p></p>
		<a href="/Pigi/findId.al" class="nav-link" style="font-size:large; color:#ff4a4a;">이메일 찾기</a>
		<a href="/Pigi/findPw.al" class="nav-link" style="font-size:large; color:#ff4a4a;">비밀번호 찾기</a>

	</form>
</div>

</body>
</html>