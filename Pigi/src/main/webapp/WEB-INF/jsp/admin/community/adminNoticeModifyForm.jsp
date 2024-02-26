<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지</title>

<script type="text/javascript">

function check() {
		
	if(confirm("수정하시겠습니까?") == true) {
		
		if (document.Notice.Ctitle.value.trim() == ""){
			alert("제목를 입력해 주세요.");
			document.Notice.Ctitle.focus();
			return false;
		}else if(document.Notice.Ccontent.value.trim() == ""){
			alert("내용를 입력해 주세요.");
			document.Notice.Ccontent.focus();
			return false;
		} else {
			document.Notice.submit();
		}
	}
}
</script>

</head>
<body>
<br>
	<div style="text-align:center">
		<h3>공지사항 수정</h3>
	</div>
<br>
<br>
	<!-- 입력한 값을 전송하기 위해 form 태그를 사용한다 -->
	<!-- 파라미터 전송은 POST 방식 -->
	<!-- 글수정 폼 -->
<div style="text-align:center;">
<div class="col-md-12 ftco-animate">
	<form method="post" action="adminNoticeModify.al" style="width:50%; margin: auto;" name="Notice">

	<div class="form-group">
		<label for="exampleFormControlInput1">글번호</label>
		<input type="text" class="form-control" name="CIDX" value="${noticeBean.CIDX}" readonly> 
	</div>
	<!-- 작성자로 {CWRITER}정보를 넘겨줌 -->
	<div class="form-group">
		<label for="exampleFormControlInput1">작성자</label>
		<input type="text" class="form-control" name="CWRITER" value="${noticeBean.CWRITER}" readonly> 
	</div>
	<div class="form-group">
		<label for="exampleFormControlInput1">제목</label>
		<input type="text" maxlength="50" class="form-control" id="Ctitle" name="CTITLE" value="${noticeBean.CTITLE}"> 
	</div>
	<div class="form-group">
		<label for="exampleFormControlInput1">내용</label>
		<textarea class="form-control" maxlength="500" id="Ccontent" name="CCONTENT" placeholder="내용을 적어주세요." rows="10">${noticeBean.CCONTENT}</textarea>
	</div>
	
	<br>
	<br>
		<p>
			<button type="button" class="btn btn-primary py-2 px-4" onclick="return check();">수정</button>
			<button type="button" class="btn btn-dark py-2 px-4" onclick="location.href='adminNoticeList.al'">취소</button>
		</p>
	<br>
	</form>
</div>
</div>
</body>
</html>