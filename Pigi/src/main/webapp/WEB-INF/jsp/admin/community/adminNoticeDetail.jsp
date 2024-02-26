<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지</title>
<script>
function deleteCheck() {
	var CIDX = document.getElementById('CIDX').value;
	if(confirm("삭제하시겠습니까?") == true) {
		location.href="adminNoticeDelete.al?CIDX=" + CIDX;
	}
}
</script>

<style type="text/css">
p { word-break: break-all;}
</style>

</head>
<body>
<div style="text-align:center">
	<h5>공지사항</h5>
	<input type="hidden" id="CIDX" name="CIDX" value="${noticeBean.CIDX }">
	<hr width="80%">
</div>

<section class="ftco-section ftco-degree-bg">
	<div class="container">
		<div class="row">
			<div class="blog-entry align-self-stretch d-md-flex">
				<div class="text d-block pl-md-4">
					<h3><p style="width: 100%;">${noticeBean.CTITLE}</p></h3>
					<div class="meta mb-3">
						<div>${noticeBean.CWRITER}</div>
						<div>${noticeBean.CDATE}</div>
					</div><hr>
      				<p class="mt-5" style="font-size:large; width: 100%;">${noticeBean.CCONTENT}</p>
    			</div>
			</div>
		</div><hr>
	</div>
</section>

<%-- <br> 
	<div style="text-align:center"><h2>공지 사항</h2></div>
 	<input type="hidden" id="CIDX" name="CIDX" value="${noticeBean.CIDX }">
<br>

  		<!-- 공지사항 부분 -->
		<div class="container" id="noticeDetail" action="adminNoticeDelete.al" >
			<div class="row">
			<pre>                                                                               </pre>
				<div>
				</div>
				<br>
		    	<div style="text-align:center" style="padding-right:70px;" id="noticeDetail" >
		    		<div class="col-md-12 ftco-animate">
			    		<table class="table" >
			    		<thead class="thead-primary">
						<tr class="text-center">
							<td colspan="1">작성자</td>
							<td colspan="3">${noticeBean.CWRITER}</td>
						</tr>
						</thead>
						<tbody>
						<tr class="text-center">
							<td>제목</td>
							<td class="product-name" width="50%">${noticeBean.CTITLE}</td>
							<td>날짜</td>
							<td class="product-name" width="*%">${noticeBean.CDATE}</td>
						</tr>		
						<tr class="text-center">
							<td colspan="1"><br>글 내용</td>
							<td colspan="3"><br>${noticeBean.CCONTENT}</td>
						</tr>
						</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
<br> --%>
<p style="text-align:center;">
<button class="btn btn-black py-2 px-3" onclick="location.href='adminNoticeModifyForm.al?CIDX=${noticeBean.CIDX}'">수정</button>
<button class="btn btn-light py-2 px-3" onclick="return deleteCheck()">삭제</button>
</p><br><br>
</body>
</html>