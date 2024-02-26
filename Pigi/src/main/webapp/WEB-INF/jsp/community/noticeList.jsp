<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지</title>
</head>
<body>

<br>
	<div style="text-align:center">
 		<h3>공지 사항</h3>
	</div> 		
  	    <br>
  	    <br>
  	    <br>
  		<!-- 공지사항 목록 부분 -->
	<div class="container">
		<div style="text-align:center" id="board" >
		<div class="col-md-12 ftco-animate">
			<table class="table">
				<thead class="thead-primary">
    		<tr> 
    			<th scope="col" width="10%">번호</th> 
    			<th scope="col">내용</th> 
    			<th scope="col" width="15%">작성자</th> 
    			<th scope="col" width="15%">작성일</th> 
    		</tr> 
    	</thead>
     
    <tbody> 
    <c:choose> 
    	<c:when test="${noticeCount!=0}"> 
    		<c:forEach var="notice" items="${noticeBeanList}" varStatus="status"> 
    	<tr> 
    		<td>${notice.getR()}</td> 
    		<td> 
    			<a href="noticeDetail.al?CIDX=${notice.CIDX}">${notice.CTITLE}</a>
   			</td>
   			<td>${notice.CWRITER}</td> 
   			<td>${notice.CDATE}</td>	
    	</tr> 
			</c:forEach>
    </c:when>
    
    <c:otherwise> 
   		<tr>
			<td colspan="4">조회된 결과가 없습니다</td> 
		</tr>
	</c:otherwise>
	
	</c:choose> 
	</tbody> 
		</table>
</div>
	<!-- 공지사항 페이징 -->
		${paging.pageHtml}
</div>
</div>
<br><br><br>
</body>
</html>