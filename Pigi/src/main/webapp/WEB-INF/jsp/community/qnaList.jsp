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
 		<h3>고객 센터</h3>
	</div> 		
  	     <br>
  	     <div class="container">
  	    <!-- 글쓰기 버튼 -->
  	    <div style='width:100px; float: right;'>
			<input type="button" class="btn btn-dark py-2 px-3" value="글쓰기" onclick="location.href='/Pigi/qnaForm.al'"/>
			<!-- <a href="/Pigi/qnaForm.al">글쓰기</a> -->
		</div>
		<br>
		<br>
  		<!-- 고객센터 목록 부분 -->
  		
			<div style="text-align:center" id="board" >
				<div class="col-md-12 ftco-animate">
					<div class="cart-list">
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
    	<c:when test="${qnaCount!=0}"> 
    		<c:forEach var="qna" items="${qnaBeanList}" varStatus="status"> 
    	<tr> 
    		<td>${qna.getR()}</td> 
    		<td> 
    			<a href="qnaDetail.al?CIDX=${qna.CIDX}">${qna.CTITLE}</a>
   			</td>
   			<td>${qna.CWRITER}</td> 
   			<td>${qna.CDATE}</td>	
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
</div><!-- end cart-list div -->
</div> 
	<!-- 공지사항 페이징 -->
		${paging.pageHtml}
</div>
</div>

<br>
<br>
<br>
</body>
</html>