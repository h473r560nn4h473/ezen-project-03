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
<div class="container">
<div class="row">
<div class="col-sm-2">
	<div class="sidebar-box ftco-animate" id="menu" style="margin-top:30px;">
		<ul class="categories">
			<li><a href="/Pigi/myPage.al" style="font-size:middle;">마이페이지</a></li>
			<c:if test="${EMAIL != 'ADMIN'}">
			<li><a href="/Pigi/myInfoModifyForm.al" style="font-size:middle;">회원정보 수정</a></li>
			</c:if>
			<li><a href="/Pigi/myInfoOrder.al" style="font-size:middle;">주문조회</a></li>
			<li><a href="/Pigi/myInfoReview.al" style="font-size:middle;">후기</a></li>
			<li><a href="/Pigi/myInfoQna.al" style="font-size:middle; color:#ff4a4a;">고객센터</a></li>
		</ul>
	</div>
</div>
<div class="col-sm-10">
	<div style="text-align:center">
		<h3> 내 고객센터 </h3><br><br>
	</div>
	<button type="button" class="btn btn-dark py-2 px-3" style="float:right;" onClick="location.href='/Pigi/qnaForm.al'">글쓰기</button>
	<br><br>
		<div class="row">
			<div class="col-md-12 ftco-animate">
			
				<div class="cart-list">
			
					<table class="table">
						<thead class="thead-primary">
							<tr class="text-center">
								<th>번호</th>
								<th>제목</th>
								<th>내용</th>
								<th>작성일</th>
							</tr>
						</thead>
						<tbody>
						
						    <c:choose> 
						    	<c:when test="${qnaCount!=0}"> 
						    		<c:forEach var="qna" items="${qnaBeanList}" varStatus="status"> 
										<tr class="text-center">
										<td>${qna.CIDX}</td>
										
										<td>${qna.CTITLE}</td>
   			
   										<td><a href="qnaDetail.al?CIDX=${qna.CIDX}">${qna.CCONTENT}</a></td>
										
										<td>${qna.CDATE}</td>
										
									</tr><!-- END TR-->
									</c:forEach>
								</c:when>
									
								<c:otherwise> 
									 <tr>
										<td colspan="3">조회된 결과가 없습니다</td> 
									</tr>
								</c:otherwise>
							</c:choose> 
						
						</tbody>
					</table>
					
				</div> <!-- end cart-list div -->
									
			</div>
		</div>
	</div>
</div>
</div>
</div>
<br><br><br>
</body>
</html>