<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지 </title>
</head>
<script>
/* 이메일 찾기 유효성 체크 */
function formCheck() {
	var form = document.getElementById("findPwForm");
	var EMAIL = document.getElementById("EMAIL");
	var NAME = document.getElementById("NAME");
	var JUMIN1 = document.getElementById("JUMIN1");
	var JUMIN2 = document.getElementById("JUMIN2");
	
	if(EMAIL.value.trim()=="") {
		alert("이메일를 입력해주세요.");
		EMAIL.focus();
		return false;
	} else if(NAME.value.trim()=="") {
		alert("이름을 입력해주세요.");
		NAME.focus();
		return false;
	} else if(JUMIN1.value.trim()=="") {
		alert("주민등록번호 앞자리를 입력해주세요.");
		JUMIN1.focus();
		return false;
	} else if(JUMIN2.value.trim()=="") {
		alert("주민등록번호 뒷자리를 입력해주세요.");
		JUMIN2.focus();
		return false;
	}
	else {	
		form.submit();
	}
}

/* 비밀번호를 다 입력하고 엔터키 입력시 로그인 시도 */
function keyPress() {
	if(window.event.keyCode ==13) {
		return formCheck();	
	}	
}
</script>
<script>
$(document).on("keyup", "input[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );})
</script>
<script>
$(document).on("keyup", "input[noSpecial]", function() {$(this).val( $(this).val().replace(/[^ㄱ-힣a-zA-Z0-9@]/gi,"") );})
</script>
<script>
$(document).on("keyup", "input[noBlank]", function() {$(this).val( $(this).val().replace(/\s/gi,"") );})
$(document).on("keyup", "input[noBlank]", function() {$(this).val( $(this).val().replace("관리자","") );})
</script>
<script>
/* 첫 화면 로딩 시 포커스 */
window.onload = function() {
	document.getElementById("EMAIL").focus();
}
</script>
<body>

<div class="container" style="text-align:center;">
	<div class="row justify-content-center">
		<div class="col-xl-12 ftco-animate">
			<form id="findPwForm" action="/Pigi/findPwResult.al" method="post" class="billing-form">
			<h2 class="mb-4 billing-heading">비밀번호 찾기</h2><br>
			<hr><br>
			<div class="row align-items-end"  style="text-align:center">
			
				<div class="col-md-4 mx-auto" align="center">
					<h6 class="mb-4" style="text-align:center;">이메일</h6>
				<div class="form-group d-flex">
					<input type="text" class="form-control" id="EMAIL" name="EMAIL" 
							maxlength="30" noSpecial onKeyup="this.value=this.value.replace('ADMIN','');">
				</div>
				</div>
				<div class="w-100"></div>
				
				<div class="col-md-4 mx-auto" align="center">
					<h6 class="mb-4" style="text-align:center;">이름</h6>
				<div class="form-group d-flex">
					<input type="text" id="NAME" name="NAME"  class="form-control" maxlength="8" noBlank>
				</div>
				</div>
				<div class="w-100"></div><br>
				
				
				<div class="col-md-4 mx-auto">
				<h6 class="mb-4" style="text-align:center;">주민등록번호</h6>
				<div class="row">
						<div class="col-md-6">
						<div class="form-group">
					<input type="text" id="JUMIN1" name="JUMIN1" size="12" maxlength="6"  class="form-control"
							numberOnly>
				
					</div>
					</div>
						<div class="col-md-6">
						<div class="form-group">
							<input type="password" id="JUMIN2" name="JUMIN2"  size="12" maxlength="7"  class="form-control"
								numberOnly>
					</div>
					</div>
					</div>
					</div>
					<div class="w-100"></div>
			<p></p>
			
			<div class="col-md-6 mx-auto">
			<input type="button" value="찾기" class="btn btn-primary py-2 px-4" style="padding-right:30px;"
				onClick="return formCheck()">
				</div>
				</div>
			<p></p>		
			</form>
		</div>
	</div>
</div>
<br><br><br><br><br><br>
</body>
</html>