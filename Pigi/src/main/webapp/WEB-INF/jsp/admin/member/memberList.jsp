<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양많으면댇지</title>
</head>

<body>
	
<section class="ftco-section ftco-cart">
	<div style="text-align:center"><h2>회원 정보</h2></div>
	<br/>
		<div class="container">
			<div class="row">

				<form action="memberList.al">
				<div class="form-group d-flex">
					<select name="condition" id="condition">
						<option value="EMAIL" <c:if test="${condition=='EMAIL'}">selected</c:if>>이 메 일</option>
						<option value="NAME" <c:if test="${condition=='NAME'}">selected</c:if>>이 &emsp; 름</option>
						<option value="RANK" <c:if test="${condition=='RANK'}">selected</c:if>>회원등급</option>
					</select>
					<input type="text" name="keyword" id="keyword" class="form-control" style="width:250px;"
						placeholder="검색어 ..." value="${keyword}"/>
					<button type="submit" class="btn-black px-4">검색</button>
				</div>
				</form><!-- condition이라는 파라미터 명으로 넘어간다 -->
				<div>
				</div>
				<br>
				<div class="container" style="padding-right:70px;">				
					<div class="col-md-12 ftco-animate">
						<div class="cart-list">
							<table class="table">
							<thead class="thead-primary">
								<tr class="text-center">
									<th>회원 등급</th>
									<th>이메일</th>
									<th>이름</th>
									<th>전화번호</th>
									<th>핸드폰번호</th>
									<th>주소</th>
								</tr>
							</thead>
							<tbody>
							
								<c:forEach var="member" items="${memberBeanList}"> 
								<tr class="text-center">
									<td>
										<c:if test="${member.RANK == 'B'}">
											브론즈
										</c:if>
										<c:if test="${member.RANK == 'S'}">
											실버
										</c:if>									
										<c:if test="${member.RANK == 'G'}">
											골드
										</c:if>
									</td>
									<td><a href="memberDetail.al?EMAIL=${member.EMAIL}">${member.EMAIL}</a></td>
									<td class="product-name">
										${member.NAME}
									</td>
									<td>${member.PHONE}</td>
									<td>
										${member.MOBILE}
									</td>
									<td>${member.ADDRESS1} / ${member.ADDRESS2}</td>
								</tr><!-- END TR-->
								</c:forEach>
							
							</tbody>
							</table>
						</div>
						
						${paging.pageHtml}
					</div>			
				</div>
		</div>
	</div>
</section>	

</body>
</html>