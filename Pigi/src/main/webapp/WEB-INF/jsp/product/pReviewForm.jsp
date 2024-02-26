<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지</title>
<script>
function confirmReview() {
	var form = document.getElementById("reviewForm");
	var PID = document.getElementById("PID");
	var CWRITER = document.getElementById("CWRITER");
	var CCONTENT = document.getElementById("CCONTENT");
	var CTITLE = document.getElementById("CTITLE");
	
	if(confirm("후기를 등록하시겠습니까?") == true) {
		if(CTITLE.value.trim()=="") {
			alert("제목을 입력해주세요.");
			CTITLE.focus();	
			return false;
		} else if(CCONTENT.value.trim()=="") {
			alert("내용을 입력해주세요.");
			CCONTENT.focus();
			return false;
		} else {	
			form.submit();
		}
	}
}
window.onload = function() {
	document.getElementById("CTITLE").focus();
}
</script>
</head>
<body>
	<p/>
	<div>
		<div>
			<div>

			    <section class="ftco-section">
			    	<div class="container">
			    		<div class="row">
			    			<div class="col-lg-6 mb-5">
			    				<a href="img/${productBean.PIMAGE}" class="image-popup"><img src="img/${productBean.PIMAGE}" class="img-fluid" alt="이미지 없음"></a>
			    			</div>
			    			<div class="col-lg-6 product-details pl-md-5" style="text-align:right">
			    				<h3>${productBean.PNAME}&nbsp;</h3>
			    				<input type="hidden" id="PID" name="PID" value="${productBean.PID}">
							<div class="col-md-12">
				          		<table style="width:80%; margin-left:auto">
				          			<tr>
				          				<td style="text-align:left">
				          				<b>정가</b>
				          				</td>
				          				<td style="text-align:right; width:50%">
   										<c:if test="${productBean.PSALE == 0}">
				          				${productBean.PPRICE}원
				          				</c:if>
   										<c:if test="${productBean.PSALE != 0}">
				          				<del>${productBean.PPRICE}원</del>
				          				</c:if>
				          				</td>
				          			</tr>
									<tr>
				          				<td style="text-align:left;">
				          				<b>판매가</b>
				          				</td>
				          				<td style="text-align:right; width:50%">
				          				<c:set var="salePrice" value="${productBean.PPRICE * (100-productBean.PSALE) * 0.01}" />
				          				<fmt:formatNumber value="${salePrice}" pattern="#.#" />원
				          				<input type="hidden" id="PPRICE" name="PPRICE" value="${productBean.PPRICE}">
				          				</td>
				          			</tr>
									<tr>
				          				<td style="text-align:left;">
				          				<b>할인율</b>
				          				</td>
				          				<td style="text-align:right; width:50%">
				          				${productBean.PSALE}%
				          				<input type="hidden" id="PSALE" name="PSALE" value="${productBean.PSALE}">
				          				</td>
				          			</tr>
				          			<c:if test="${productBean.PWEIGHT!=-1}" >
									<tr>
				          				<td style="text-align:left;">
				          				<b>무게</b>
				          				</td>
				          				<td style="text-align:right; width:50%">
				          				${productBean.PWEIGHT}&nbsp;g
				          				</td>
				          			</tr>
				          			</c:if>
				          			<c:if test="${! empty productBean.PKIND}" >
									<tr>
				          				<td style="text-align:left;">
				          				<b>분류</b>
				          				</td>
				          				<td style="text-align:right; width:50%">
				          				${productBean.PKIND}
				          				</td>
				          			</tr>
				          			</c:if>
									<tr>
				          				<td style="text-align:left;">
				          				<b>제조사</b>
				          				</td>
				          				<td style="text-align:right; width:50%">
				          				${productBean.PCOM}
				          				</td>
				          			</tr>
									<tr>
				          				<td style="text-align:left;">
				          				<b>원산지</b>
				          				</td>
				          				<td style="text-align:right; width:50%">
				          				${productBean.PLOC}
				          				</td>
				          			</tr>
				          		</table>
				          	</div>
						</div>
						</div>
			    	</div>
			    </section>
			</div>
		</div>
	</div>

	<section>	
	<div class="container">
		<div class="row slider-text justify-content-center align-items-center">
			<div class="col-lg-10 mb-5">

					<form id="reviewForm" action="pReview.al" method="post">
					<table style="text-align:center; margin-left:auto; margin-right:auto; width:80%;">
						<tr>
							<td style="width:10%; text-align:left">
			   					작성자
			   				</td>
			   				<td>
			   				 	${CWRITER}
			   					<input type="hidden" id="CWRITER" name="CWRITER" value="${CWRITER}">
			   					<input type="hidden" id="PID" name="PID" value="${PID}">
							</td>
						</tr>
						<tr>
							<td style="width:10%; text-align:left;">
								제목 
							</td>
							<td>
								<input type="text" id="CTITLE" name="CTITLE" style="width:90%;" maxlength="50">
							</td>
						</tr>
						<tr>
							<td style="width:100%; text-align:left;" colspan="2">
								<textarea name="CCONTENT" id="CCONTENT" cols="30" rows="7" maxlength="500" class="form-control"></textarea>
							</td>
						</tr>
						<tr>
							<td style="width:100%; text-align:left;" colspan="2">
								<input type="button" class="btn btn-primary py-2 px-3"
									onClick="return confirmReview()" value="작성">
								<a href="pDetail.al?PID=${PID}" class="btn btn-black py-2 px-3">취소</a>
							</td>
						</tr>
					</table>
					</form>
					<hr>				

			</div>
		</div>
	</div>
	</section>	
</body>
</html>