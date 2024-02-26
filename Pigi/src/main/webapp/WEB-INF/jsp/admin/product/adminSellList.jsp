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
</head>
<body>

<section class="ftco-section ftco-cart">
	<div style="text-align:center">
		<h2>매출 리스트</h2>
	</div>
	<br>
		<div class="container">
			<div class="row">
				<div class="col-md-12 ftco-animate">
				
					<div class="cart-list">
				
						<table class="table">
							<thead class="thead-primary">
								<tr class="text-center">
									<th>&nbsp;</th>
									<th>상품 리스트</th>
									<th>&nbsp;</th>
									<th>가격</th>
									<th>재고</th>
									<th>판매량</th>
								</tr>
							</thead>
							<tbody>
							
								<c:forEach var="product" items="${sellList}">
								<tr class="text-center">
									<td></td>
									
									<td class="image-prod"><div class="img" style="background-image:url(img/${product.PIMAGE});"></div></td>
									
									<td class="product-name">
										<h3><b>${product.PNAME}</b></h3>
									</td>
									
									<td class="price">${product.PPRICE}</td>
									
									<td class="quantity">
										<div class="input-group mb-3">
										   	<input type="text" name="quantity" class="quantity form-control input-number" value="${product.PSTOCK}" readonly>
										</div>
									</td>
									
									<td class="total">${product.PSELL}</td>
								</tr><!-- END TR-->
								</c:forEach>
							
							</tbody>
						</table>
						
					</div> <!-- end cart-list div -->
					
					${paging.pageHtml}
					
				</div>
			</div>
		</div>
	</section>
	
</body>
</html>