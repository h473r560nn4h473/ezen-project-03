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
<style>
.point_table {
    width: 100%;
}
.point_table th {
	background-color: #ff4a4a;
	color : white;
}
.point_table th, .point_table td {
	border-bottom: 1px solid #444444;
	padding: 10px;
}
</style>
<body>

<div class="col-md-4 ftco-animate mx-auto" style="text-align:center">
	<h3>보유 포인트</h3>
</div> 		

<br>

<div class="col-md-4 ftco-animate mx-auto">
	<table class="mx-auto" style="width:50%">
		<tbody>
			<tr> 
				<td scope="col" width="50%" style="text-align:center">보유 포인트</td> 
				<td scope="col" style="text-align:center"><b>${eventBean.PIGI_POINT}Point</b></td> 
			</tr>  
		
		</tbody> 
	</table>
</div>

<br>
<br>

<div class="col-md-4 ftco-animate mx-auto" style="text-align:center">
	<h3>포인트 획득 내역</h3>
</div> 

<br>
<br>

	<div class="container">
	
	     <div class="row justify-content-center ftco-animate">
   			<div class="col-md-10 mb-5 text-center">
   				<ul class="product-category">
   					<li><a href="point.al">전체</a></li>
   					<li><a href="point.al?maxDate=0">1개월전~오늘</a></li>
   					<li><a href="point.al?maxDate=1">2개월전~1개월전순</a></li>
   					<li><a href="point.al?maxDate=2">3개월전~2개월전</a></li>
   					<li><a href="point.al?maxDate=3">3개월전</a></li>
   				</ul>
   			</div>
   		</div>
	
	<div class="col-md-12" style="text-align:center" id="board" >
		<div class="col-md-4 ftco-animate mx-auto">
			<table class="point_table col-md-12 mx-auto">
				<thead class="thead-primary">
		    		<tr> 
						<th>획득 날짜</th>
						<th>획득 포인트</th>			
					</tr>		
				</thead>
			
				<tbody>
					<c:if test="${size==0}">
						<tr><td colspan="2">조회된 내역이 없습니다</td></tr>				
					</c:if>
					<c:if test="${size!=0}">
						<c:forEach var="point" items="${pointList}">
							<tr> 
								<td scope="col" width="50%" style="text-align:center">${point.ROULETTEDATE}</td> 
								<td scope="col" style="text-align:center"><b>${point.PIGI_POINT}Point</b></td> 
							</tr>  
						</c:forEach>
					</c:if>
				
				</tbody> 
			</table>
			${paging.pageHtml}
			</div>
		</div>
	</div>

	<br>
	<br>
	<br>

<!-- <input type="button" value="500원" onClick="updatePoint()"> -->

</body>
</html>