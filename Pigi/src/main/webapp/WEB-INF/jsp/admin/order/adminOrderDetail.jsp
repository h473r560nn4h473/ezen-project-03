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
<body >
<section>
<div class="container" style="text-align:center;">
	<div class="row justify-content-center">
		<div class="col-xl-7 ftco-animate">
			<form method="post" id="adminOrderDetail" action="adminOrderModify.al"
				name="adminOrderDetail">
			<h2 class="mb-4 billing-heading">주문 상세보기</h2>
				<div class="row align-items-end" style="padding-left:150px;">
					
					<!-- 주문번호 -->					
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">주문번호</h6>
						<input type="text" class="form-control" id="OID" name="OID" style="width:400px;"
							value="${orderBean.OID}" readonly>
					</div>
					<div class="w-100"></div>
           
					<!-- 주문일 -->
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">주문일</h6>
						<input type="text" id="ODATE"  class="form-control" style="width:400px;"
							value="${orderBean.ODATE}" readonly>
					</div>
					<div class="w-100"></div>
					
					<!-- 이메일 -->
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">이메일</h6>
						<input type="text" id="OMAIL" name="OMAIL"  class="form-control" style="width:400px;"
							value="${orderBean.OMAIL}" readonly>
					</div>
					<div class="w-100"></div>
					
					<!-- 상품명 -->
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">상품명</h6>
						<input type="text" id="OPRODUCT" name="OPRODUCT"  class="form-control" style="width:400px;"
							value="${orderBean.OPRODUCT}" readonly>
					</div>
					<div class="w-100"></div>
					
					<!-- 주소 -->
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">주소</h6>
						<input type="text" id="OPOSTCODE" name="OPOSTCODE"  class="form-control" style="width:200px;"
							value="${orderBean.OPOSTCODE}" readonly><br>
						<input type="text" id="OADDRESS1" name="OADDRESS1"  class="form-control" style="width:400px;"
							value="${orderBean.OADDRESS1}" readonly><br>
						<input type="text" id="OADDRESS2" name="OADDRESS2"  class="form-control" style="width:400px;"
							value="${orderBean.OADDRESS2}" readonly>
					</div>
					<div class="w-100"></div>
					
					<!-- 상품금액 -->
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">상품금액</h6>
						<input type="text" id="OPRICE" name="OPRICE"  class="form-control" style="width:400px;"
							value="${orderBean.OPRICE}" readonly>
					</div>
					<div class="w-100"></div>					
					
					<!-- 상품수량 -->
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">상품수량</h6>
						<input type="text" id="OCOUNT" name="OCOUNT"  class="form-control" style="width:400px;"
							value="${orderBean.OCOUNT}" readonly>
					</div>
					<div class="w-100"></div>
					
					<!-- 총금액 -->
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">총금액</h6>
						<input type="text" id="OTOTAL" name="OTOTAL"  class="form-control" style="width:400px;"
							value="${orderBean.OTOTAL}" readonly>
					</div>
					<div class="w-100"></div>
					
					<!-- 배송상태 -->
					<div class="select-wrap">
					<h6 class="mb-4" style="text-align:left;">배송상태</h6>
						<select name="OSTATUS" id="OSTATUS" class="form-control" style="width:400px;">
							<option id="OSTATUS" <c:if test="${orderBean.OSTATUS eq '배송전'}">selected</c:if> value="배송전">배송전</option>
		                    <option id="OSTATUS" <c:if test="${orderBean.OSTATUS eq '배송중'}">selected</c:if> value="배송중">배송중</option>
		                    <option id="OSTATUS" <c:if test="${orderBean.OSTATUS eq '배송완료'}">selected</c:if> value="배송완료">배송완료</option>
						</select>
					</div>&nbsp;
					<div class="w-100"></div>
					<br/><br/>
					
					<br/><br/><br/><br/>
				</div>
		
				<!-- 버튼 -->
				<div class="form-group" align="center">
					 <button type="submit" class="btn btn-black py-2 px-3" >저장</button>

			         <button type="button" class="btn btn-primary py-2 px-3" onclick="history.back()">취소</button>
				     
				</div>
			</form>	
		</div>
	</div>
</div>
</section>
<br><br>
<!-- <a href="/Pigi/adminOrderModify.al">저장 - 주문정보 수정 기능</a><br>
<a href="/Pigi/adminOrderList.al">취소 - 주문정보 리스트</a><br> -->
</body>
</html>