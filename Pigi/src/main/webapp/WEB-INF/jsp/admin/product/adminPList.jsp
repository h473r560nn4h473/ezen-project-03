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
function deleteCheck(e) {
	if(confirm("삭제하시겠습니까?") == true) {
		var PID = document.getElementById('del'+e).value;
		location.href = "adminPDelete.al?PID=" + PID;
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
		<div class="container">
			<div class="row">
			<pre>                                                                               </pre>
				<form action="adminPList.al">
				<div class="form-group d-flex">
					<input type="text" name="keyword" id="keyword" class="form-control" style="width:250px;" value="${keyword}">
					<input type="submit" value="검색" class="btn btn-black py-2 px-4">
				</div>
				</form>
				&nbsp;
				<div>
					<input type="button" value="상품 추가" class="btn btn-primary py-2 px-2" style="height:55px;" onClick="location.href='adminPWriteForm.al'">
				</div>
				<div>
				</div>
				<br>
				<div class="container" style="padding-right:70px;">				
					<div class="col-md-12 ftco-animate">
						<div class="cart-list">
							<table class="table">
								<thead class="thead-primary">
									<tr class="text-center">
										<th>상품번호</th>
										<th>상품종류</th>
										<th>식자재종류</th>
										<th>무게</th>
										<th>&nbsp;</th>
										<th>&nbsp;상품명</th>										
										<th>가격</th>
										<th>재고량</th>
										<th>&nbsp;</th>
									</tr>
								</thead>
								 
								<tbody>
									<c:forEach var="product" items="${adminPBeanList}" varStatus="status">
									<tr>
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
											<a href="javascript:deleteCheck(${product.PID});">삭제</a>
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