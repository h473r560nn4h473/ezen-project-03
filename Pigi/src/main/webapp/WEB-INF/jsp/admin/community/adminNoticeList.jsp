<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지</title>
</head>
<body>
<br>
<!-- 기존 타이틀 위치 -->
<!-- <div style="text-align:center"><h2>공지 사항</h2></div> -->
<br/>
<style type="text/css">
#tatle1{ position: absolute; border: 3px solid red; top: 100px; }
</style>

		<div class="container">
			<div class="row">			
			<pre>                                                                               </pre>		
				<div>			
				</div>
				<br>
				<div class="container" style="padding-right:70px;">					
					<div class="col-md-12 ftco-animate">					
							<table class="table">
							<div style="text-align:center"><h2>공지 사항</h2></div>
							<br><br>
							<thead class="thead-primary">
								<tr class="text-center">
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>작성일</th>
									<th><th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="notice" items="${noticeList}"> 
								<tr class="text-center">
									<td>${notice.CIDX}</td>
									<td><a href="adminNoticeDetail.al?CIDX=${notice.CIDX}">${notice.CTITLE}</a></td>
									<td class="product-name">
										${notice.CWRITER}
									</td>
									<td>${notice.CDATE}</td>
									<td>
										<form id="qnaDelete" action="adminQnaDelete.al" method="post">
										   	<button class="btn btn-light py-2 px-3"
												onClick="javascript:if(confirm('삭제하시겠습니까?')==true){ location.href='adminQnaDelete.al?CIDX= + ${qna.CIDX}' } else{ return false; }">
												삭제
											</button>
										</form>
												</td>
								</tr><!-- END TR-->
								</c:forEach>
							</tbody>
							</table>
						<br>
						<div style="text-align:center;">
							<button class="btn btn-light py-2 px-3" style="height:55px;"
							onclick="location.href='adminNoticeWriteForm.al'">글쓰기</button>
						</div>
						<br>
						${paging.pageHtml}
					</div>			
				</div>
			</div>
		</div>
</body>
<br><br>
</html>