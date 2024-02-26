<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지</title>
</head>
<body>
<section class="ftco-section ftco-cart">
 	<div style="text-align:center"><h2>주문 리스트</h2></div>
 	<br/><br/>
 	<div class="container" >
 		<form action="adminOrderList.al" style="text-align:center;">
			<div class="row">
 				<pre>                                  </pre>
 				<h4 class="mb-4" style="padding:10px 0px 0px 50px;">검색 조건</h4>&emsp;
				<div class="select-wrap">
					<select name="condition" id="condition" class="form-control" style="width:100px;">
						<option id="OMAIL" <c:if test="${condition eq 'OMAIL'}">selected</c:if>  value="OMAIL">이메일</option>
	                    <option id="OID" <c:if test="${condition eq 'OID'}">selected</c:if> value="OID">주문번호</option>
	                    <option id="OPRODUCT" <c:if test="${condition eq 'OPRODUCT'}">selected</c:if> value="OPRODUCT">상품명</option>
	                    <option id="OPRODUCT" <c:if test="${condition eq 'OSTATUS'}">selected</c:if> value="OSTATUS">배송상태</option>
					</select>
				</div>&nbsp;
				<input type="text" name="keyword" id="keyword" class="form-control" style="width:250px;" value="${keyword}"> &nbsp;
			<input type="submit" style="height:53px;" value="검색" class="btn-black px-4">
			</div>
		</form>
	</div>
	<br><hr width="80%" align="center"><br>
	<div style="white-space:nowrap;overflow:auto;" class="container">				
		<div class="col-md-12 ftco-animate">
		<div class="overlay"></div>
			<table class="table">
				<thead class="thead-primary">
					<tr class="text-center">
						<th>주문번호</th>
						<th>주문일</th>
						<th>이메일</th>
						<th>상품명</th>
						<th>주소</th>
						<th>금액</th>
						<th>수량</th>
						<th>총금액</th>
						<th>배송상태</th>
					</tr>
				</thead>
				 
				<tbody>
					<c:choose>
						<c:when test="${fn:length(orderBeanList)>0}">
							<c:forEach var="order" items="${orderBeanList}" varStatus="status">
							<tr>
							<td>&emsp;&emsp;${order.OID}&emsp;&emsp;</td>
							<td>${order.ODATE}</td>
							<td>${order.OMAIL}</td>
							<td><a href="adminOrderDetail.al?OID=${order.OID}">${order.OPRODUCT}</a></td>
							<td>${order.OADDRESS1}</td>
						    <td>&nbsp;${order.OPRICE}&nbsp;</td>
						    <td>&nbsp;&nbsp;${order.OCOUNT}&nbsp;&nbsp;</td>
						    <td>${order.OTOTAL}</td>
						    <td>
						    <c:choose>
						    	<c:when test="${order.OSTATUS eq '배송전'}">&emsp;배송전&emsp;</c:when>
						    	<c:when test="${order.OSTATUS eq '배송중'}">&emsp;배송중&emsp;</c:when>
						    	<c:when test="${order.OSTATUS eq '배송완료'}">&emsp;배송완료&emsp;</c:when>
						    </c:choose>
						    </td>
							</c:forEach>
						</c:when>
						<c:otherwise>
						<tr><td>
						조회된 결과 없음
						</td></tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		</div>			
</section>
<br>
</body>
</html>