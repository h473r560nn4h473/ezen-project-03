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
	<div style="text-align:center">
		<h1> 주문 결과 </h1>
	</div>
	
	<form method="post" id="pOrderForm" action="pOrder.al">
	<!-- 단일 주문일 때의 결과 표시 -->

	
	<section class="ftco-section ftco-cart">
		<div class="container">
			<div class="row">
				<div class="col-md-12 ftco-animate">
				
					<div class="cart-list">

						<!-- 단독 주문일 경우 주문 리스트 출력 -->
						<c:if test="${result=='direct'}">	
				
						<table class="table">
							<thead class="thead-primary">
								<tr class="text-center">
									<th colspan="2">상품명</th>
									<th>수량</th>
									<th>상품 금액</th>
									<th>판매 금액</th>
									<th>할인 금액</th>
									<th>결제 금액</th>
								</tr>
							</thead>
							<tbody>
							
								<tr class="text-center">
									<td class="image-prod"><div class="img" style="background-image:url(img/product-${orderBean.OPID}.jpg);"></div></td>
									
									<td class="product-name">
										<h3><b>${orderBean.OPRODUCT}</b></h3>
									</td>
									
									<td class="count">${orderBean.OCOUNT}</td>
									
									<td class="price">${orderBean.OPRICE}원</td>
									
									<td class="price">
				          				<c:set var="salePrice" value="${orderBean.OPRICE * (100-orderBean.OSALE) / 100}" />
				          				<fmt:formatNumber value="${salePrice}" pattern="#.#" />원
									</td>
									
								<%-- 	<td id="saled${i}" style="color:Crimson">
				          			<c:set var="salePrice" value="${basketBeanList[i].BPRICE * (100-basketBeanList[i].BSALE) * 0.01}" />
				          			<input type="hidden" id="OPRICE${i}" name="OPRICE" value="${basketBeanList[i].BPRICE}"></td>
						      		 --%>
						      		
									<td class="saled" style="color:Crimson">
									<c:set var="saled" value="${(orderBean.OPRICE-salePrice) * orderBean.OCOUNT}" />
									<fmt:formatNumber value="${saled}" pattern="#.#" />원</td>
									
						      		
									<td class="total">
										<b>${orderBean.OTOTAL}원</b>
									</td>
								</tr><!-- END TR-->
							
							</tbody>
						</table>
						
<%-- 						<table class="table">
							<tbody>
								<tr class="text-center">
									<td rowspan="4"><h4><b>주문자 정보</b></h4></td>
								</tr>
							
								<tr class="text-center">
									<td class="count">이름</td>
									<td class="count">${memberBean.NAME}</td>
								</tr>
								
								<tr class="text-center">
									<td class="count">전화번호</td>				
									<td class="count">${memberBean.PHONE}</td>
								</tr>
								
								<tr class="text-center">	
									<td class="count">휴대폰번호</td>
									<td class="count">${memberBean.MOBILE}</td>
								</tr><!-- END TR-->

								<tr class="text-center">
									<td rowspan="4"><h4><b>배송 정보</b></h4></td>
								</tr>
							
								<tr class="text-center">
									<td class="count">우편번호</td>
									<td class="count">${orderBean.OPOSTCODE}</td>
								</tr>
								
								<tr class="text-center">
									<td class="count">주소</td>				
									<td class="count">${orderBean.OADDRESS1}</td>
								</tr>
								
								<tr class="text-center">	
									<td class="count">상세주소</td>
									<td class="count">${orderBean.OADDRESS2}</td>
								</tr><!-- END TR-->
							
								<tr class="text-center">
									<td rowspan="3"><h4><b>결제 정보</b></h4></td>
								</tr>
							
								<tr class="text-center">
									<td class="count">적립 포인트</td>
									<td class="count"><b>${pigi_point.PIGI_POINT}Point</b></td>
								</tr>
							
								<tr class="text-center">
									<td class="count">총 결제 금액</td>
									<td class="count"><b>${orderBean.OTOTAL}원</b></td>
								</tr>
								
								<tr class="text-center">
									<td class="count">무통장 입금</td>				
									<td class="count">Bank of England 00000001</td>
								</tr>
							
							</tbody>
						</table> --%>
						
						</c:if>
						
						<!-- 장바구니일 경우 주문 리스트 출력 -->
						<c:if test="${result=='basket'}">	
					
						<table class="table">
							<thead class="thead-primary">
								<tr class="text-center">
									<th colspan="2">상품명</th>
									<th>수량</th>
									<th>상품 금액</th>
									<th>판매 금액</th>
									<th>할인 금액</th>
									<th>결제 금액</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="order" items="${orderBeanList}">
								<tr class="text-center">
									<td class="image-prod">
									<div class="img" style="background-image:url(img/product-${order.OPID}.jpg);"></div></td>
									
									<td class="product-name">
										<h3><b>${order.OPRODUCT}</b></h3>
									</td>
									
									<td class="count">${order.OCOUNT}</td>
									
									<td class="price">${order.OPRICE}원</td>
									
									<%-- <td class="price">${order.OSALE}%</td> --%>
									
									<td class="price">
				          				<c:set var="salePrice" value="${order.OPRICE * (100-order.OSALE) / 100}" />
				          				<fmt:formatNumber value="${salePrice}" pattern="#.#" />원
									</td>
									
									<td class="saled" style="color:Crimson">
										<c:set var="saled" value="${(order.OPRICE-salePrice) * order.OCOUNT}" />
										<fmt:formatNumber value="${saled}" pattern="#.#" />원</td>
									
									<td class="total">
										<b><c:set var="total" value="${salePrice * order.OCOUNT}" />
										<fmt:formatNumber value="${total}" pattern="#.#" />원</b>
									</td>
								</tr><!-- END TR-->
								
							</c:forEach>	
							</tbody>
						</table>
						
						</c:if>
						
					</div> <!-- end cart-list div -->
					
					<!-- 직접 구매 -->
					<c:if test="${result=='direct'}">
									
					<div class="row justify-content-end">
						<div class="col-lg-4 mt-5 cart-wrap ftco-animate">
							<div class="cart-total mb-3">
								<h5><b>주문자 정보</b></h5><br>
								<p class="d-flex total-price">
									<span>이름</span>
									<span>${memberBean.NAME}</span>
								</p>
								<p class="d-flex total-price">
									<span>전화번호</span>
									<span>${memberBean.PHONE}</span>
								</p>
								<p class="d-flex total-price">
									<span>휴대폰번호</span>
									<span>${memberBean.MOBILE}</span>
								</p><br>
								<p class="d-flex total-price"></p>
							</div>
						</div>
						<div class="col-lg-4 mt-5 cart-wrap ftco-animate">
							<div class="cart-total mb-3">
								<h5><b>배송 정보</b></h5><br>
								<p class="d-flex total-price">
									<span>우편번호</span>
									<span>${orderBean.OPOSTCODE}</span>
								</p>
								<p class="d-flex total-price">
									<span>주소</span>
									<span>${orderBean.OADDRESS1}</span>
								</p>
								<p class="d-flex total-price">
									<span>상세주소</span>
									<span>${orderBean.OADDRESS2}</span>
								</p><br>
								<p class="d-flex total-price"></p>
							</div>
						</div>
						<div class="col-lg-4 mt-5 cart-wrap ftco-animate">
							<div class="cart-total mb-3">
								<h5><b>결제 정보</b></h5><br>
								<p class="d-flex total-price">
									<span>적립 포인트</span>
									<span>${pigi_point.PIGI_POINT}&nbsp;Point</span>
								</p>
								<p class="d-flex total-price">
									<span>총 결제 금액</span>
									<span>${orderBean.OTOTAL}&nbsp;원</span>
								</p>
								<p class="d-flex total-price">
									무통장 입금
								</p>
								<p class="d-flex total-price">
									&emsp;&emsp;<span><b>Bank of England</b></span>
									<span>00000001</span>
								</p>
							</div>
						</div>
					</div>				
					
					</c:if>	
					
					<!-- 장바구니 -->
					<c:if test="${result=='basket'}">
					
					<div class="row justify-content-end">
						<div class="col-lg-4 mt-5 cart-wrap ftco-animate">
							<div class="cart-total mb-3">
								<h5><b>주문자 정보</b></h5><br>
								<p class="d-flex total-price">
									<span>이름</span>
									<span>${memberBean.NAME}</span>
								</p>
								<p class="d-flex total-price">
									<span>전화번호</span>
									<span>${memberBean.PHONE}</span>
								</p>
								<p class="d-flex total-price">
									<span>휴대폰번호</span>
									<span>${memberBean.MOBILE}</span>
								</p><br>
								<p class="d-flex total-price"></p>
							</div>
						</div>
						<div class="col-lg-4 mt-5 cart-wrap ftco-animate">
							<div class="cart-total mb-3">
								<h5><b>배송 정보</b></h5><br>
								<p class="d-flex total-price">
									<span>우편번호</span>
									<span>${orderBeanList[0].OPOSTCODE}</span>
								</p>
								<p class="d-flex total-price">
									<span>주소</span>
									<span>${orderBeanList[0].OADDRESS1}</span>
								</p>
								<p class="d-flex total-price">
									<span>상세주소</span>
									<span>${orderBeanList[0].OADDRESS2}</span>
								</p><br>
								<p class="d-flex total-price"></p>
							</div>
						</div>
						<div class="col-lg-4 mt-5 cart-wrap ftco-animate">
							<div class="cart-total mb-3">
								<h5><b>결제 정보</b></h5><br>
								<p class="d-flex total-price">
									<span>적립 포인트</span>
									<span>${pigi_point.PIGI_POINT}&nbsp;Point</span>
								</p>
								<p class="d-flex total-price">
									<span>총 결제 금액</span>
									<span>${orderBeanList[0].OTOTAL}&nbsp;원</span>
								</p>
								<p class="d-flex total-price">
									무통장 입금
								</p>
								<p class="d-flex total-price">
									&emsp;&emsp;<span><b>Bank of England</b></span>
									<span>00000001</span>
								</p>
							</div>
						</div>
					</div>
					
					</c:if>

			</div>
		</div>
	</div>
</section>
	

	
	</form>	
	
</body>
</html>