<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<html>
<script>
function logoutCheck() {
	if(confirm("로그아웃 하시겠습니까?") == true) {
		location.href = "/Pigi/logout.al";	
	} 
}
</script>
<body>
	<div class="py-1" style="background-color:#fff; height:150px;">
		<div class="container" style="text-align:right;">
		
			<!-- 로그인을 하지 않았을 경우 -->
			<c:if test="${ empty EMAIL }">
					<a class="text-dark" href="/Pigi/loginForm.al" style="font-size:small;">로그인</a> 
						<span class="text-dark px-2">|</span> 
					<a class="text-dark" href="/Pigi/joinForm.al" style="font-size:small;">회원가입</a>
			</c:if>
	
			<!-- 로그인을 했을 경우 -->
			<c:if test="${! empty EMAIL }">
				<span class="text-dark" style="font-size:small;">
				<%= session.getAttribute("EMAIL") %>님
				</span>
					<span class="text-dark px-2">|</span>
				<a class="text-dark" href="/Pigi/myPage.al" style="font-size:small;">마이페이지</a> 
					<span class="text-dark px-2">|</span> 
				<a class="text-dark" href="/Pigi/basketList.al" style="font-size:small;">장바구니</a>
					<span class="text-dark px-2">|</span> 
				<a class="text-dark" href="javascript:logoutCheck()" style="font-size:small;">로그아웃</a> 
			</c:if>
			
		</div>
		<div class="container" style="text-align:center;">
			<a class="navbar-brand" href="/Pigi/"> 
				<img src="./img/logo.png;" style="width: 130px; height: 130px;">
			</a>
		</div>
	</div>

	<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>
	      
	       
	               
			<div class="collapse navbar-collapse" id="ftco-nav">
				<div align="left">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a href="/Pigi/allList.al" class="nav-link" style="font-size:x-large; color:#ff4a4a;">전체상품</a></li>
					<li class="nav-item active"><a href="/Pigi/aclList.al" class="nav-link" style="font-size:x-large; color:#ff4a4a;">식자재</a></li>
					<li class="nav-item active"><a href="/Pigi/etcList.al" class="nav-link" style="font-size:x-large; color:#ff4a4a;">기타상품</a></li>
				</ul>
				</div>

	        <ul class="navbar-nav ml-auto">
	        
	          <!-- 관리자일 경우 관리 탭 추가 -->
	          <c:if test="${ EMAIL == 'ADMIN' }">
	        	<li class="nav-item"><a href="/Pigi/adminMain.al" class="nav-link" style="font-size:large; color:dark;">관리</a></li>
	          </c:if>
	        
	          <li class="nav-item"><a href="/Pigi/noticeList.al" class="nav-link" style="font-size:large; color:dark;">공지</a></li>
	          <li class="nav-item"><a href="/Pigi/qnaList.al" class="nav-link" style="font-size:large; color:dark;">고객센터</a></li>
			  
	          <li class="nav-item active dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
              	  style="font-size:large; color:dark;">이벤트</a>
	              <div class="dropdown-menu" aria-labelledby="dropdown04">
	              	<a class="dropdown-item" href="/Pigi/point.al">포인트</a>
	              	<a class="dropdown-item" href="/Pigi/coupon.al">쿠폰</a>
	                <a class="dropdown-item" href="/Pigi/roulette.al">룰렛</a>
	              </div>
           	  </li>
			  
	        </ul>
	      </div>
	    </div>
	  </nav>
	 

	 
</body>