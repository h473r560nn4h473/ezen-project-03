<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>양많으면댇지</title>
<script>
function checks() {
	var memberDetailForm = document.getElementById("memberDetailForm");
	
	var ADDRESS1 = document.getElementById("ADDRESS1");
	var ADDRESS2 = document.getElementById("ADDRESS2");
	var POSTCODE = document.getElementById("POSTCODE");
	var MOBILE = document.getElementById("MOBILE");
	
	if(ADDRESS1.value.trim() == ""){
		alert("주소를 입력해주세요.");
		ADDRESS1.focus();
		return false;
	}
	
	if(ADDRESS2.value.trim() == ""){
		alert("상세 주소를 입력해주세요.");
		ADDRESS2.focus();
		return false;
	}
	
	if(POSTCODE.value.trim() == ""){
		alert("우편번호를 입력해주세요.");
		POSTCODE.focus();
		return false;
	}

	if(MOBILE.value.trim() == ""){
		alert("핸드폰 번호를 입력해주세요.");
		MOBILE.focus();
		return false;
	}
	
	memberDetailForm.submit();
}
</script>
<script>
function changeForm(val) {
	if (val == "-1") {
		location.href = "/Pigi/memberList.al";
	} else if (val == "0") {
		return checks();
	} else if (val == "1") {
		location.href = "main.jsp?contentPage=/Pigi/memberDelete.al";
	}
}
</script>
<script>
function submit2(del) {
	if(confirm("삭제하시겠습니까?") == true) {
		del.action="memberDelete.al";
		del.submit();
		return true;
	}
}
</script>
<!-- 우편번호 API -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다 (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다
                    document.getElementById("ADDRESS2").value = extraAddr;
                
                } else {
                    document.getElementById("ADDRESS2").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다
                document.getElementById('POSTCODE').value = data.zonecode;
                document.getElementById("ADDRESS1").value = addr;
                // 커서를 상세주소 필드로 이동한다
                document.getElementById("ADDRESS2").focus();
            }
        }).open();
    }
</script>
<script>
// 특수문자 입력 방지
function characterCheck(obj){
var regExp = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi; 
// 특수문자 허용 정규식
if( regExp.test(obj.value) ){
	alert("특수문자는 입력하실수 없습니다");
	obj.value = obj.value.substring( 0 , obj.value.length - 1 ); // 입력한 특수문자 삭제
	}
}
</script>
<!-- 	<style type="text/css">
	table {margin-left: auto; margin-right: auto; border: 3px solid skyblue;}
	td {border: 1px solid skyblue}
	#title {background-color: skyblue}
	</style> -->
</head>
<body class="goto-here">
<section class="ftco-section">
<div class="container" style="text-align:center;">
	<div class="row justify-content-center">
		<div class="col-xl-12 ftco-animate">
			<form method="post" id="memberDetailForm" action="memberModify.al"
				name="membermodify">
			<h2 class="mb-4 billing-heading">회원정보 수정</h2>
				<div class="row align-items-end" style="text-align:center">
				
					<!-- 회원등급 -->
					<div class="col-md-4 mx-auto" align="left">
						<h6 class="md-4">
						회원등급 :
						<c:if test="${memberBean.RANK == 'B'}">
							브론즈
						</c:if>
						<c:if test="${memberBean.RANK == 'S'}">
							실버
						</c:if>
						<c:if test="${memberBean.RANK == 'G'}">
							골드
						</c:if>
						</h6>
						
						<select name="RANK" id="RANK" class="form-control">			
							<option value="B" <c:if test="${memberBean.RANK == 'B' }">selected</c:if>>브론즈</option>
							<option value="S" <c:if test="${memberBean.RANK == 'S' }">selected</c:if>>실버</option>
							<option value="G" <c:if test="${memberBean.RANK == 'G' }">selected</c:if>>골드</option>
						</select>
					</div>
					<div class="w-100"></div><br>
					
					<!-- 이메일 -->					
					<div class="col-md-4 mx-auto" align="left">
						<h6 class="md-4">이메일</h6>
						<input type="text" class="form-control" id="EMAIL" name="EMAIL" value="${memberBean.EMAIL}" readonly>
					</div>
					<div class="w-100"></div>
           
					<!-- 이름 -->
					<div class="col-md-4 mx-auto" align="left">
						<h6 class="md-4">이름</h6>
						<input type="text" id="NAME" name="NAME"  class="form-control" value="${memberBean.NAME}" readonly>
					</div>
					<div class="w-100"></div>
					
					<!-- 주민등록번호 -->
					<div class="col-md-4 mx-auto" align="left">
					<h6 class="md-4">주민등록번호</h6>
						<div class="row">
						<div class="col-md-6">
						<div class="form-group">
						<input type="text" id="JUMIN1" name="JUMIN1" size="12" maxlength="6"  class="form-control" value="${memberBean.JUMIN1}" readonly>
						</div>
						</div>
						<div class="col-md-6">
						<div class="form-group">
						<input type="text" id="JUMIN2" name="JUMIN2"  size="12" maxlength="7"  class="form-control" value="${memberBean.JUMIN2}" readonly>
					</div>
					</div>
					</div>
					</div>
					<div class="w-100"></div>
					
					<!-- 전화번호 -->
					<div class="col-md-4 mx-auto" align="left">
						<h6 class="md-4">전화번호</h6>
						<input type="text" maxlength="11" id="PHONE" name="PHONE" size="24"  class="form-control"
							onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" value="${memberBean.PHONE}">
						<div class="w-100"></div>
						<h6 class="md-4" style="text-align:left;">'-'는 빼고 숫자만 입력해주세요.</h6>
					</div>
					<div class="w-100"></div>

					<!-- 핸드폰 번호 -->
					<div class="col-md-4 mx-auto" align="left">
						<h6 class="md-4">핸드폰 번호</h6>
						<input type="text" maxlength="11" id="MOBILE" name="MOBILE" class="form-control"
							onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" size="24" value="${memberBean.MOBILE}"> 
						<div class="w-100"></div>
						<h6 class="md-4" style="text-align:left;">'-'는 빼고 숫자만 입력해주세요.</h6>
					</div>
					<div class="w-100"></div>
					
					<!-- 주소 -->
					<div class="col-md-4 mx-auto" align="left">
					<h6 class="md-4">우편번호</h6>
					<div class="form-group d-flex">
						<input type="text" maxlength="7" class="form-control" name="POSTCODE" id="POSTCODE" placeholder="우편번호" value="${memberBean.POSTCODE}">
						<input type="button" class="submit px-3" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
					</div>
					</div>
					<div class="w-100"></div>
					
					<div class="col-md-4 mx-auto" align="left">
					<h6 class="md-4">주소</h6>
					<div class="row">
					<div class="col-md-6">
					<div class="form-group">
						<input type="text" maxlength="50" onkeyup="characterCheck(this)" onkeydown="characterCheck(this)" name="ADDRESS1" id="ADDRESS1" placeholder="주소" class="form-control"
							value="${memberBean.ADDRESS1}">
					</div>
					</div>
					<div class="col-md-6">
					<div class="form-group">
						<input type="text" maxlength="50" onkeyup="characterCheck(this)" onkeydown="characterCheck(this)" name="ADDRESS2" id="ADDRESS2" placeholder="상세주소" class="form-control"							value="${memberBean.ADDRESS2}">
					</div>
					</div>
					</div>
					</div>
					<div class="w-100"></div>
					
					<!-- 회원 정지 -->
					<div class="col-md-4 mx-auto" align="left">
						<h6 class="md-4">회원 정지</h6>
							<c:if test="${memberBean.BLOCK == 'N'}">
								<input type="checkbox" size="100" name="BLOCK" value="Y" class="form-control" style="width:400px;"> 
							</c:if>
							<c:if test="${memberBean.BLOCK == 'Y'}">
								<input type="checkbox" size="100" name="BLOCK" value="Y" class="form-control" style="width:400px;" checked> 
							</c:if>
						<div class="w-100"></div>

					</div>
					<div class="w-100"></div>
					<br/><br/>
					
					<br/><br/><br/><br/>
				</div>
		
				<!-- 버튼 -->
				<div class="form-group" align="center">
					 <button type="button" class="btn btn-primary py-3 px-5" onClick="checks()">수 &emsp; 정</button>

			          &emsp;&emsp;
			          <button type="reset" class="btn btn-black py-3 px-5" onClick="return submit2(this.form);">회원 삭제</button>
			
			          &emsp;&emsp;
			          <button type="button" class="btn btn-black py-3 px-5" onclick="changeForm(-1)">취 &emsp; 소</button>
				     
				</div>
			</form>	
		</div>
	</div>
</div>
</section>
</body>