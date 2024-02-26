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
function getCount() {
	var count = document.getElementById('BCOUNT').value;
	var price = document.getElementById('PPRICE').value;
	var sale = document.getElementById('PSALE').value;
	var salePrice = price * (100-sale) / 100;
	var totalPrice = salePrice * count;
	
	document.getElementById("totalPrice").innerText = totalPrice + '원';
	return count;
}

function putBasketAjax(pid) {
	if(confirm("장바구니에 넣으시겠습니까?") == true) {
		
		let count = document.getElementById("BCOUNT").value;
		
		$.ajax({
			url			:"putBasketAjax.al",
			data		:{"BID":Number(pid), "BCOUNT" : Number(count), "message" : "" },
			contentType	:"application/json",
			success		:function(data){
				alert(data.message);
				if(confirm("장바구니로 가시겠습니까?") == true) {
					location.href='basketList.al';
				}
			},
			error		:function(request, error){
							alert("fail");
							alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
} 

function pOrderForm() {
	var pId = document.getElementById('PID').value;
	var count = Number(document.getElementById('BCOUNT').value);
	var stock = Number(document.getElementById('PSTOCK').value);
	// 구매수량이 상품 재고보다 많을 경우 구매 정지
	
	if(count>stock) {
		alert("상품의 재고 수량보다 많은 양을 살 수 없습니다 재고 :" + stock + "개");
		return false;		
	}
	location.href="pOrderForm.al?PID=" + pId + "&PCOUNT=" + count;	
}

window.onload = function() {
	getCount();
};
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
				          				${productBean.PWEIGHT}&nbsp;kg
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
									<tr>
				          				<td style="text-align:left;">
				          				<b>수량</b>
				          				</td>
				          				<td style="text-align:right; width:50%">
				          					<input type="number" min="0" max="${productBean.PSTOCK}" id="BCOUNT" value="1" onChange="getCount()">
				          					<input type="hidden" id="PSTOCK" value="${productBean.PSTOCK}">
				          				</td>
				          			</tr>
									<tr>
				          				<td style="text-align:left;">
				          				<b>총 상품 금액</b>
				          				</td>
				          				<td style="text-align:right; width:50%">
				          				<div id="totalPrice" style="font-weight:bold; font-size:25px; color:#ff4a4a">

				          				</div>
				          				</td>
				          			</tr>
				          			<tr>
				          				<td colspan="2" style="text-align:right;">
				          					<p><input type="button" class="btn btn-primary py-2 px-4" 
				          						onClick="putBasketAjax(${productBean.PID});" value="장바구니">
				          					<input type="button" class="btn btn-black py-2 px-4" onClick="pOrderForm()" value="구&emsp;&emsp;매"></p>
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

	<div>
		<div>
			<div>
				<section>
					<c:set var="pid" value="${productBean.PID}" />
					<%
						String imgDir1 = "/img/product-" + pageContext.getAttribute("pid").toString() + "-detail1.jpg";
						String imgDir2 = "/img/product-" + pageContext.getAttribute("pid").toString() + "-detail2.jpg";
						String imgDir3 = "/img/product-" + pageContext.getAttribute("pid").toString() + "-detail3.jpg";
						String imgDir4 = "/img/product-" + pageContext.getAttribute("pid").toString() + "-detail4.jpg";
						
						String imgRealDir1 = request.getSession().getServletContext().getRealPath(imgDir1);
						String imgRealDir2 = request.getSession().getServletContext().getRealPath(imgDir2);
						String imgRealDir3 = request.getSession().getServletContext().getRealPath(imgDir3);
						String imgRealDir4 = request.getSession().getServletContext().getRealPath(imgDir4);	
						
						java.io.File file1 = new java.io.File(imgRealDir1);
						java.io.File file2 = new java.io.File(imgRealDir2);
						java.io.File file3 = new java.io.File(imgRealDir3);
						java.io.File file4 = new java.io.File(imgRealDir4);
					%>

					<% if(file1.exists()) { %>
					<p style="text-align:center;"><img src="img/product-${productBean.PID}-detail1.jpg" /></p>
					<% } %>
					<% if(file2.exists()) { %>
					<p style="text-align:center;"><img src="img/product-${productBean.PID}-detail2.jpg" /></p>
					<% } %>
					<% if(file3.exists()) { %>
					<p style="text-align:center;"><img src="img/product-${productBean.PID}-detail3.jpg" /></p>
					<% } %>
					<% if(file4.exists()) { %>
					<p style="text-align:center;"><img src="img/product-${productBean.PID}-detail4.jpg" /></p>
					<% } %>
				</section>			
			</div>
		</div>
	</div>

	<section>	
	<div class="container">
		<div class="row slider-text justify-content-center align-items-center">
			<div class="col-lg-10 mb-5">

					<table style="text-align:center; margin-left:auto; margin-right:auto; width:80%;">
						<tr>
							<td style="width:80%; text-align:right" colspan="2">
				   				<ul class="product-category">
				   					<li><a href="pReviewForm.al?PID=${pid}">글쓰기</a></li>
				   				</ul>
							</td>
						</tr>
						<c:forEach var="review" items="${reviewBeanList}">
						<tr bgcolor="#ff4a4a">
							<td style="width:80%; text-align:left;">
							<h3 class="heading"><b>${review.CTITLE}</b></h3>
							</td>
							<td style="width:20%; text-align:right;">
							<h3 class="heading"><b>${review.CWRITER}</b></h3>
							</td>
						</tr>
						<tr>
							<td style="width:80%; text-align:left" colspan="2">
							<p>${review.CCONTENT}</p>
							</td>
						</tr>
						<tr><td colspan="2"><hr></td></tr>
						</c:forEach>

					</table>
					<hr>				
				
			</div>
		</div>
	</div>
	</section>

	
</body>
</html>