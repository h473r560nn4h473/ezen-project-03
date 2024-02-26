<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지</title>
<script>
function deleteCheckAjax(pid, index) {
	if(confirm("삭제하시겠습니까?") == true) {
		
		$.ajax({
			url			: "adminPDeleteAjax.al",
			data		: {"PID" : pid},
			contentType	: "application/json",
			success		: function(data) {
				alert("삭제하였습니다");
				$("#pro"+index).remove();
			},
			error:function(request, error) {
				alert("fail");
				// error 발생 이유를 알려준다
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
}
</script>
<script>
window.onload = function() {
	document.getElementById("keyword").focus();	
}
</script>
</head>

<body class="goto-here">
<section class="ftco-section ftco-cart">
	<div style="text-align:center"><h2>상품 리스트</h2></div>
	<br/>
		<div class="container" style="padding-right:70px;">
		<div class="col-xl-12 ftco-animate center">
			<div class="row">
				<br>
				
			<div class="col-md-6 ml-auto">
				<form action="adminPList.al">
					<div class="form-group d-flex">
						<input type="text" name="keyword" id="keyword" class="form-control" value="${keyword}">
						<input type="submit" value="검색" class="btn-black px-4">
						&nbsp;
					<div>
						<input type="button" value="상품 추가" class="btn btn-black py-3 px-2" onClick="location.href='adminPWriteForm.al'">
					</div>
					</div>
				</form>
			</div>
				
				
				<div class="container">				
					<div class="cart-list">
							<table class="table">
								<thead class="thead-primary">
									<tr class="text-center">
										<th>상품번호</th>
										<th>상품종류</th>
										<th>식자재종류</th>
										<th>무게</th>
										<th colspan="2">상품명</th>										
										<th>가격</th>
										<th>재고량</th>
										<th>&nbsp;</th>
									</tr>
								</thead>
								 
								<tbody>
									<c:forEach var="product" items="${adminPBeanList}" varStatus="status">
									<tr id="pro${status.index}">
										<td>${product.PID}</td>
										<td>${product.PTYPE}</td>
										<td>${product.PKIND}</td>
										<td>
											<c:if test="${! empty product.PWEIGHT && product.PWEIGHT!='-1'}">
												${product.PWEIGHT}
											</c:if>
										</td>
										<td>
										   	<div style="width:90px; height:70px;">
												<a href="pDetail.al?PID=${product.PID}" class="img-prod"><img class="img-fluid" src="img/${product.PIMAGE}"></a>
											</div> 
										</td>
										<td>
										    <a href="adminPModifyForm.al?PID=${product.PID}">${product.PNAME}</a>
										</td>
										<td>${product.PPRICE}</td>
										<td>${product.PSTOCK}</td>
										<td>
											<input type="hidden" id="del${product.PID}" value="${product.PID}">
											<a href="javascript:deleteCheckAjax(${product.PID}, ${status.index});">삭제</a>
										</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						
						${paging.pageHtml}
					</div>
				</div>
		</div>
	</div>
</section>

<br>
</body>
</html>