<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지</title>
<style type="text/css">
.name {
  width        : 225px;
  text-overflow: ellipsis;
  white-space  : nowrap;
  overflow     : hidden;
  display      : block;
  color   : black;
}
</style>
<script>
function deleteCheckAjax(cidx, index) {
	if(confirm("삭제하시겠습니까?") == true) {
		
		$.ajax({
			url			: "adminNoticeDeleteAjax.al",
			data		: {"CIDX" : cidx},
			contentType	: "application/json",
			success		: function(data) {
				alert("삭제하였습니다");
				$("#not"+index).remove();
			},
			error:function(request, error) {
				alert("fail");
				// error 발생 이유를 알려준다
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
}
</script>
</head>
<body>
<section class="ftco-section ftco-cart">
	<div style="text-align:center">
		<h2> 공지사항 </h2>
	</div>
	<br>

		<div class="container">
			<div class="row">
				<div class="col-md-12 ftco-animate">
				
					<div class="cart-list">
					
						<div style="text-align:right;">
							<button class="btn btn-primary py-2 px-3" style="height:55px;"
								onclick="location.href='adminNoticeWriteForm.al'">글쓰기</button>
						</div>
						<br>
				
						<table class="table">
							<thead class="thead-primary">
								<tr class="text-center">
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>작성일</th>
									<th>&nbsp;</th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach var="notice" items="${noticeList}"  varStatus="status"> 
								<tr id="not${status.index}">
									<td>${notice.CIDX}</td>
								
									<td><a href="adminNoticeDetail.al?CIDX=${notice.CIDX}">${notice.CTITLE}</a></td>
									<td class="product-name">
										${notice.CWRITER}
									</td>
									<td>${notice.CDATE}</td>
									<td>
										<button class="btn btn-light py-2 px-3"
											onClick="deleteCheckAjax(${notice.CIDX}, ${status.index});">삭제</button>
									</td>
								</tr><!-- END TR-->
								</c:forEach>
							</tbody>
						</table>
					</div> <!-- end cart-list div -->
					<br>

					<br>
					${paging.pageHtml}
				</div>			
			</div>
		</div>
	</section>
</body>
<br><br>
</html>