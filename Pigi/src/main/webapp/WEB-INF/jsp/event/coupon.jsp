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
function updateCoupon(coupon) {
	
	var email = $('#EMAIL').val();
	
	$.ajax({
		url 			: "updateCoupon.al",
		data 			: {"EMAIL" : email, "inputCoupon" : coupon, "page" : "coupon" },
		contentType 	: "application/json",
		success			: function(data) {
			
			alert(data.message);
			
			if(data.coupon == '1K') {			
				$('#coupon1').html("<img src='img/1kcoupon.png' style='width:60%;'>");
			} else if(data.coupon == '2K') {
				$('#coupon2').html("<img src='img/2kcoupon.png' style='width:60%;'>");
			}
			
		},
		error:function(request, error) {
			alert("fail");
			// error 발생 이유를 알려준다
		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
	});
}

</script>
</head>
<body>

<input type="hidden" id="EMAIL" value="<%= request.getSession().getAttribute("EMAIL") %>">

<section class="ftco-section">
	<div class="container mx-auto">
		<div class="row no-gutters ftco-services mx-auto">
		
			<h4><b>보유 쿠폰</b></h4>&emsp;
		
			<c:if test="${eventBean.COUPON1K == 'Y'}">
			<div class="col-md-2 text-center d-flex align-self-stretch ftco-animate">
				<div class="media block-6 services mb-md-0 mb-4">
					<div class="media-body">
						<img src="img/1kcoupon.png" style="width:60%;">
					</div>
				</div>      
			</div>
			</c:if>
			<c:if test="${eventBean.COUPON1K == 'N'}">
			<div class="col-md-2 text-center d-flex align-self-stretch ftco-animate">
				<div class="media block-6 services mb-md-0 mb-4">
					<div id="coupon1" class="media-body">
						<img src="img/1kcoupon-no.png" style="width:60%;">
					</div>
				</div>      
			</div>			
			</c:if>
			
			<c:if test="${eventBean.COUPON2K == 'Y'}">
			<div class="col-md-2 text-center d-flex align-self-stretch ftco-animate">
				<div class="media block-6 services mb-md-0 mb-4">
					<div class="media-body">
						<img src="img/2kcoupon.png" style="width:60%;">
					</div>
				</div>      
			</div>
			</c:if>
			<c:if test="${eventBean.COUPON2K == 'N'}">
			<div class="col-md-2 text-center d-flex align-self-stretch ftco-animate">
				<div class="media block-6 services mb-md-0 mb-4">
					<div id="coupon2" class="media-body">
						<img src="img/2kcoupon-no.png" style="width:60%;">
					</div>
				</div>      
			</div>			
			</c:if>
			
			<c:if test="${eventBean.COUPON3K == 'Y'}">
			<div class="col-md-2 text-center d-flex align-self-stretch ftco-animate">
				<div class="media block-6 services mb-md-0 mb-4">
					<div class="media-body">
						<img src="img/3kcoupon.png" style="width:60%;">
					</div>
				</div>      
			</div>
			</c:if>
			<c:if test="${eventBean.COUPON3K == 'N'}">
			<div class="col-md-2 text-center d-flex align-self-stretch ftco-animate">
				<div class="media block-6 services mb-md-0 mb-4">
					<div class="media-body">
						<img src="img/3kcoupon-no.png" style="width:60%;">
					</div>
				</div>      
			</div>
			</c:if>
			
			<c:if test="${eventBean.COUPON5K == 'Y'}">
			<div class="col-md-2 text-center d-flex align-self-stretch ftco-animate">
				<div class="media block-6 services mb-md-0 mb-4">
					<div class="media-body">
						<img src="img/5kcoupon.png" style="width:60%;">
					</div>
				</div>      
			</div>
			</c:if>
			<c:if test="${eventBean.COUPON5K == 'N'}">
			<div class="col-md-2 text-center d-flex align-self-stretch ftco-animate">
				<div class="media block-6 services mb-md-0 mb-4">
					<div class="media-body">
						<img src="img/5kcoupon-no.png" style="width:60%;">
					</div>
				</div>      
			</div>
			</c:if>

			<c:if test="${eventBean.COUPON10K == 'Y'}">
			<div class="col-md-2 text-center d-flex align-self-stretch ftco-animate">
				<div class="media block-6 services mb-md-0 mb-4">
					<div class="media-body">
						<img src="img/10kcoupon.png" style="width:60%;">
					</div>
				</div>      
			</div>
			</c:if>
			<c:if test="${eventBean.COUPON10K == 'N'}">
			<div class="col-md-2 text-center d-flex align-self-stretch ftco-animate">
				<div class="media block-6 services mb-md-0 mb-4">
					<div class="media-body">
						<img src="img/10kcoupon-no.png" style="width:60%;">
					</div>
				</div>      
			</div>
			</c:if>
		</div>
	</div>
</section>

<section class="ftco-section">
	<div class="container">
		<h4><b>쿠폰 획득하기</b></h4><br>
		<hr>
		<br>
		<div class="row no-gutters ftco-services mx-auto">
			<div class="col-md-6 text-center d-flex align-self-stretch ftco-animate">
				<div class="media block-6 services mb-md-0 mb-4">
					<div class="media-body">
						<a href="javascript:updateCoupon('1K')"><img src="img/1kcoupon.png" style="width:60%;"></a>
					</div>
				</div>      
			</div>
			<div class="col-md-6 text-center d-flex align-self-stretch ftco-animate">
				<div class="media block-6 services mb-md-0 mb-4">
					<div class="media-body">
						<a href="javascript:updateCoupon('2K')"><img src="img/2kcoupon.png" style="width:60%;"></a>
					</div>
				</div>      
			</div>
		</div>
	</div>
</section>

<!-- 
<div class="row no-gutters ftco-services mx-auto">
<input type="button" value="1천쿠폰" onClick="updateCoupon('1K')">
<input type="button" value="2천쿠폰" onClick="updateCoupon('2K')">
<input type="button" value="3천쿠폰" onClick="updateCoupon('3K')">
<input type="button" value="5천쿠폰" onClick="updateCoupon('5K')">
<input type="button" value="1만쿠폰" onClick="updateCoupon('10K')">
</div>
 -->

</body>
</html>