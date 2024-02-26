<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지</title>

<style type="text/css">
p { word-break: break-all; color : #5d5d5d} 
</style>
</head>
<body>
<br><br>s
<div style="text-align:center">
	<h5>상세 내용</h5>
	<hr width="80%">
</div> 		
<br>
<br>
<section class="ftco-section ftco-degree-bg">
	<div class="container">
		<div class="row">
			<div class="blog-entry align-self-stretch d-md-flex">
				<div class="text d-block pl-md-4">
					<h3><p style="width: 100%;">${communityBean.CTITLE}</p></h3>
					<div class="meta mb-3">
						<div>${communityBean.CWRITER}</div>
						<div>${communityBean.CDATE}</div>
					</div><hr>
      				<p class="mt-5" style="font-size:large; width: 100%;">${communityBean.CCONTENT}</p>
    			</div>
			</div>
		</div>
		
		<div class="col-md-12"><hr>
		    <p class="mb-5 mt-5">댓글</p>
		    <c:choose>
		    	<c:when test="${comCount!=0}">
					<c:forEach var="comment" items="${commentBeanList}" varStatus="status"> 
					    <ul class="comment-list">
							<li class="comment">
								<div class="vcard bio">
                    				<h5 style="color:#5d5d5d;">Pigi</h5>
                  				</div>
								<div class="comment-body">
									<div class="meta"><p style="width: 100%;">${comment.COMMENTDATE}</p></div><!-- 답변 작성 날짜 -->
									<p style="font-size:middle; width: 100%;">
									 	${comment.COMMENTT}
									 </p>
								</div>
						     </li>
						</ul>
					</c:forEach>
				</c:when>
				<c:otherwise> 
		   			<tr>
						<td colspan="2">조회된 결과가 없습니다</td> 
					</tr>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</section>
<br><br><br><br><br><br><br><br><br><br><br><br>



<%-- <!-- 고객센터 상세 부분 -->
  	<div class="container">
		<div style="text-align:center" id="qnaDetail" >
			<div class="col-md-12 ftco-animate">
    		<table class="table" > 
				<thead class="thead-primary">
					<tr>				
						<th colspan="2">${communityBean.CTITLE}</th>
					</tr>
				</thead>
		<tbody>
			<tr>
				<td width="50%">${communityBean.CWRITER}</td>
				<td width="*%">${communityBean.CDATE}</td>
			</tr>
			
			<tr>
				<td colspan="2"><br>${communityBean.CCONTENT}<br><br></td>
			</tr>
			<tr>
				<th colspan="2"><b>관리자 답변</b></th>
			</tr>
	 <c:choose> 
    	<c:when test="${comCount!=0}"> 
    		<c:forEach var="comment" items="${commentBeanList}" varStatus="status"> 
			<tr>
				<td colspan="2"><br>${comment.COMMENTT}<br><br></td>
			</tr>
   		 	</c:forEach>
  	 	</c:when>
   	 
    	<c:otherwise> 
   			<tr>
				<td colspan="2">조회된 결과가 없습니다</td> 
			</tr>
		</c:otherwise>
	
	</c:choose> 
		</tbody>
		</table>
	
	</div>
</div>
</div> --%>
<br>
<br>
</body>
</html>